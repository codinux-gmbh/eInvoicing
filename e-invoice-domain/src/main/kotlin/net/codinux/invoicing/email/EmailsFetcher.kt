package net.codinux.invoicing.email

import jakarta.mail.*
import jakarta.mail.event.MessageCountAdapter
import jakarta.mail.event.MessageCountEvent
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeUtility
import kotlinx.coroutines.*
import net.codinux.invoicing.email.model.*
import net.codinux.invoicing.filesystem.FileUtil
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import org.eclipse.angus.mail.imap.IMAPFolder
import org.eclipse.angus.mail.imap.IMAPMessage
import java.io.File
import java.time.Instant
import java.util.*
import java.util.concurrent.Executors
import kotlin.math.max

open class EmailsFetcher(
    protected open val eInvoiceReader: EInvoiceReader = EInvoiceReader(),
    protected open val coroutineDispatcher: CoroutineDispatcher = Executors.newFixedThreadPool(max(24, Runtime.getRuntime().availableProcessors() * 4)).asCoroutineDispatcher()
) {

    protected data class MessagePart(
        val mediaType: String,
        val part: Part
    )


    companion object {
        protected val MessageBodyMediaTypes = listOf("text/plain", "text/html")

        protected val FileNotDownloadedOrErrorOccurred = Pair<Invoice?, File?>(null, null)
    }


    protected val log by logger()


    open fun listenForNewEmails(account: EmailAccount, options: ListenForNewMailsOptions) = runBlocking {
        try {
            val status = connect(account, options)

            status.folder.addMessageCountListener(object : MessageCountAdapter() {
                override fun messagesAdded(event: MessageCountEvent) {
                    event.messages.forEach { message ->
                        getEmail(message, status)
                    }
                }
            })

            launch(coroutineDispatcher) {
                keepConnectionOpen(status, options)
            }

            close(status)
        } catch (e: Throwable) {
            log.error(e) { "Listening to new emails of '${account.username}' failed" }
            options.onError?.invoke(FetchEmailError(FetchEmailErrorType.ListenForNewEmails, null, e))
        }
    }

    protected open suspend fun keepConnectionOpen(status: FetchEmailsStatus, options: ListenForNewMailsOptions) {
        val account = status.account
        val folder = status.folder
        log.info { "Listening to new emails of $account" }

        // Use IMAP IDLE to keep the connection alive
        while (options.stopListening.get() == false) {
            if (!folder.isOpen) {
                log.info { "Reopening inbox of $account ..." }
                folder.open(Folder.READ_ONLY)
            }

            folder.idle()

            delay(250)
        }

        log.info { "Stopped listening to new emails of '$account}'" }
    }


    open fun fetchAllEmails(account: EmailAccount, options: FetchEmailsOptions = FetchEmailsOptions()): FetchEmailsResult {
        try {
            val status = connect(account, options)

            val emails = fetchAllEmailsInFolder(status)

            close(status)

            return FetchEmailsResult(emails, null, status.messageSpecificErrors)
        } catch (e: Throwable) {
            log.error(e) { "Could not fetch emails of account $account" }

            return FetchEmailsResult(emptyList(), e)
        }
    }

    protected open fun fetchAllEmailsInFolder(status: FetchEmailsStatus): List<Email> = runBlocking {
        val folder = status.folder
        val messageCount = folder.messageCount
        if (messageCount <= 0) {
            return@runBlocking emptyList()
        }

        val startUid = (status.options.lastRetrievedMessageId ?: 0) + 1 // message numbers start at 1
        val messages = folder.getMessagesByUID(startUid, UIDFolder.MAXUID)

        // for each data type like envelope (from, subject, ...), body structure, if message is unread, ... a network request is
        // executed, making the overall process very slow -> use FetchProfile to prefetch requested data with a single request
        folder.fetch(messages, getFetchProfile(status))

        messages.mapNotNull { message ->
            async(coroutineDispatcher) {
                try {
                    getEmail(message, status)
                } catch (e: Throwable) {
                    log.error(e) { "Could not get email $message" }
                    status.addError(FetchEmailErrorType.GetEmail, folder.getUID(message), e)
                    null
                }
            }
        }
            .awaitAll()
            .filterNotNull()
    }

    private fun getFetchProfile(status: FetchEmailsStatus) = FetchProfile().apply {
        add(UIDFolder.FetchProfileItem.UID) // message UID
        add(FetchProfile.Item.ENVELOPE) // from, subject, to, ...
        add(FetchProfile.Item.CONTENT_INFO) // content type, disposition, ...

        // add(FetchProfile.Item.FLAGS) // message status like unread, deleted, draft, ...
        // add(IMAPFolder.FetchProfileItem.MESSAGE) // the entire message including all attachments, headers, ... there should be rarely a use case for it
    }

    protected open fun getEmail(message: Message, status: FetchEmailsStatus): Email? {
        val date = map(message.sentDate ?: message.receivedDate)
        status.options.minMessageDate?.let { minDate ->
            if (date.isBefore(minDate)) {
                log.debug { "Ignoring message $message with date $date as it is before downloadOnlyMessagesNewerThan date $minDate" }
                return null
            }
        }


        val imapMessage = message as? IMAPMessage
        val messageId = status.folder.getUID(message)

        val parts = getAllMessageParts(message)
        val messageBodyParts = parts.filter { it.part.fileName == null && it.mediaType in MessageBodyMediaTypes }
        val attachmentParts = parts.filter { it !in messageBodyParts }

        val attachments = attachmentParts.mapNotNull { part ->
            getAttachment(part, status, messageId)
        }

        val sender = message.from?.firstOrNull()?.let { map(it) }
        val plainTextBody = getPlainTextBody(messageBodyParts, status, messageId)

        val email = Email(
            messageId,
            sender, message.subject ?: "", date,

            message.getRecipients(Message.RecipientType.TO).orEmpty().map { map(it) }, message.getRecipients(Message.RecipientType.CC).orEmpty().map { map(it) }, message.getRecipients(Message.RecipientType.BCC).orEmpty().map { map(it) },
            (message.replyTo?.firstOrNull() as? InternetAddress)?.let { if (it.address != sender?.address) map(it) else null }, // only set replyTo if it differs from sender

            plainTextBody, getHtmlBody(messageBodyParts, status, messageId, plainTextBody),

            imapMessage?.contentLanguage?.firstOrNull(),
            parts.any { it.mediaType == "application/pgp-encrypted" },

            attachments
        )

        status.options.emailReceived(email)

        return email
    }

    protected open fun map(address: Address): EmailAddress =
        if (address is InternetAddress) { // use MimeUtility to parse e.g. Quoted-printable names that e.g. start with "=?UTF-8?Q?"
            EmailAddress(address.address, address.personal?.let { MimeUtility.decodeText(it) })
        } else {
            EmailAddress(address.toString())
        }

    protected open fun getAttachment(messagePart: MessagePart, status: FetchEmailsStatus, messageId: Long): EmailAttachment? {
        try {
            val part = messagePart.part
            if (part.fileName == null) { // not an attachment
                return null
            }

            val filename = File(part.fileName)
            val extension = filename.extension.lowercase()

            val (invoice, invoiceFile) = tryToReadEInvoice(part, extension, messagePart.mediaType, status)

            if (invoice != null || Part.ATTACHMENT.equals(part.disposition, ignoreCase = true)) {
                val file = invoiceFile ?:
                            if (extension !in status.options.downloadAttachmentsWithExtensions) null
                            else downloadAttachment(part, status)

                return EmailAttachment(part.fileName, extension, part.size.takeIf { it > 0 }, mapDisposition(part), messagePart.mediaType, part.contentType, invoice, file)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${messagePart.part.fileName}' (${messagePart.mediaType}) for eInvoice" }
            status.addError(FetchEmailErrorType.GetAttachment, messageId, e)
        }

        return null
    }

    private fun mapDisposition(part: Part) = when (part.disposition?.lowercase()) {
        "inline" -> ContentDisposition.Inline
        "attachment" -> ContentDisposition.Attachment
        null -> ContentDisposition.Body
        else -> ContentDisposition.Unknown
    }

    protected open fun tryToReadEInvoice(part: Part, extension: String, mediaType: String?, status: FetchEmailsStatus): Pair<Invoice?, File?> =
        if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            val file = downloadAttachment(part, status)
            Pair(eInvoiceReader.extractFromPdfOrNull(part.inputStream), file)
        } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
            val file = downloadAttachment(part, status)
            Pair(eInvoiceReader.extractFromXmlOrNull(part.inputStream), file)
        } else {
            FileNotDownloadedOrErrorOccurred
        }

    private fun downloadAttachment(part: Part, status: FetchEmailsStatus) =
        File(status.userAttachmentsDownloadDirectory, FileUtil.removeIllegalFileCharacters(part.fileName)).also { file ->
            part.inputStream.use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }


    protected open fun getAllMessageParts(part: Part): List<MessagePart> {
        return if (part.isMimeType("multipart/*")) {
            val multipart = part.content as Multipart
            val parts = IntRange(0, multipart.count - 1).map { multipart.getBodyPart(it) }

            parts.flatMap { subPart ->
                getAllMessageParts(subPart)
            }
        } else {
            val mediaType = getMediaType(part)
            if (mediaType == null) {
                log.warn { "Could not determine media type of message part $part" }
                emptyList()
            } else {
                listOf(MessagePart(mediaType, part))
            }
        }
    }

    /**
     * In most cases parameters are added to content-type's media type, e.g.
     * - text/html; charset=utf-8
     * - multipart/related; boundary="boundary-related"; type="text/html"
     *
     * -> This method removes parameters and return media type (first part) only
     */
    protected open fun getMediaType(part: Part): String? = part.contentType?.lowercase()?.let { contentType ->
        val indexOfSeparator = contentType.indexOf(';')

        if (indexOfSeparator > -1) {
            contentType.substring(0, indexOfSeparator)
        } else {
            contentType
        }
    }

    protected open fun getPlainTextBody(parts: Collection<MessagePart>, status: FetchEmailsStatus, messageId: Long) =
        if (status.options.downloadMessageBody) getBodyWithMediaType(parts, "text/plain", status, messageId) else null

    protected open fun getHtmlBody(parts: Collection<MessagePart>, status: FetchEmailsStatus, messageId: Long, plainTextBody: String?) =
        // in case of downloadOnlyPlainTextOrHtmlMessageBody == true, download html body only if there's no plain text body
        if (status.options.downloadMessageBody && (status.options.downloadOnlyPlainTextOrHtmlMessageBody == false || plainTextBody == null)) {
            getBodyWithMediaType(parts, "text/html", status, messageId)
        } else {
            null
        }

    protected open fun getBodyWithMediaType(parts: Collection<MessagePart>, mediaType: String, status: FetchEmailsStatus, messageId: Long): String? = try {
        val partsForMediaType = parts.filter { it.mediaType == mediaType }

        if (partsForMediaType.size == 1) {
            partsForMediaType.first().part.content as? String
        } else if (partsForMediaType.isEmpty()) {
            null
        } else {
            val partsForMediaTypeWithoutFilename = partsForMediaType.filter { it.part.fileName == null }
            if (partsForMediaTypeWithoutFilename.size == 1) {
                partsForMediaTypeWithoutFilename.first().part.content as? String
            } else if (partsForMediaTypeWithoutFilename.isEmpty()) {
                log.warn { "Multiple message parts with media type '$mediaType' found, but all have a filename" }
                null
            } else { // if there are multiple parts without filename, then the second one is in most cases quoted previous message(s)
                partsForMediaTypeWithoutFilename.mapNotNull { it.part.content as? String }.joinToString("\r\n")
            }
        }
    } catch (e: Throwable) {
        log.error(e) { "Could not get message body for media type '$mediaType'" }
        status.addError(FetchEmailErrorType.GetMesssageBody, messageId, e)
        null
    }

    protected open fun map(date: Date): Instant =
        date.toInstant()


    protected open fun connect(account: EmailAccount, options: FetchEmailsOptions): FetchEmailsStatus {
        val properties = mapAccountToJavaMailProperties(account, options)

        val session = Session.getInstance(properties)
        val store = session.getStore("imap")
        store.connect(account.serverAddress, account.username, account.password)

        val folder = store.getFolder(options.emailFolderName) as IMAPFolder
        folder.open(Folder.READ_ONLY)

        return FetchEmailsStatus(account, store, folder, options)
    }

    protected open fun mapAccountToJavaMailProperties(account: EmailAccount, options: FetchEmailsOptions) = Properties().apply {
        // the documentation of all properties can be found here: https://javaee.github.io/javamail/docs/api/com/sun/mail/imap/package-summary.html
        put("mail.store.protocol", "imap")

        put("mail.imap.host", account.serverAddress)
        put("mail.imap.port", account.port?.toString() ?: "993")  // Default IMAP over SSL
        put("mail.imap.ssl.enable", "true")

        val timeout = (options.connectTimeoutSeconds * 1000).toString()
        put("mail.imap.connectiontimeout", timeout)
        put("mail.imap.timeout", timeout)

        // speeds up fetching data tremendously
        put("mail.imap.fetchsize", "819200") // Partial fetch size in bytes. Defaults to 16K.
        put("mail.imap.partialfetch", "false") // Controls whether the IMAP partial-fetch capability should be used. Defaults to true.
    }

    protected open fun close(status: FetchEmailsStatus) {
        try {
            status.folder.close(false)

            status.store.close()
        } catch (e: Exception) {
            log.error(e) { "Could not close folder or store" }
        }
    }

}
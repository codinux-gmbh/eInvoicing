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
        private val MessageBodyMediaTypes = listOf("text/plain", "text/html")
    }


    protected val log by logger()


    open fun listenForNewEmails(account: EmailAccount, options: ListenForNewMailsOptions) = runBlocking {
        try {
            connect(account, options) { store ->
                val folder = store.getFolder(options.emailFolderName) as IMAPFolder
                folder.open(Folder.READ_ONLY)

                val status = FetchEmailsStatus(account, folder, options)

                folder.addMessageCountListener(object : MessageCountAdapter() {
                    override fun messagesAdded(event: MessageCountEvent) {
                        event.messages.forEach { message ->
                            getEmail(message, status)
                        }
                    }
                })

                launch(coroutineDispatcher) {
                    keepConnectionOpen(status, folder, options)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Listening to new emails of '${account.username}' failed" }
            options.onError?.invoke(FetchEmailsError(FetchEmailsErrorType.ListenForNewEmails, null, e))
        }
    }

    protected open suspend fun keepConnectionOpen(status: FetchEmailsStatus, folder: IMAPFolder, options: ListenForNewMailsOptions) {
        val account = status.account
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
            return connect(account, options) { store ->
                val folder = store.getFolder(options.emailFolderName) as IMAPFolder
                folder.open(Folder.READ_ONLY)

                val status = FetchEmailsStatus(account, folder, options)

                val emails = fetchAllEmailsInFolder(status).also {
                    folder.close(false)
                }

                FetchEmailsResult(emails, null, status.messageSpecificErrors)
            }
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
                    status.addError(FetchEmailsErrorType.GetEmail, message, e)
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
        val imapMessage = message as? IMAPMessage
        val parts = getAllMessageParts(message)
        val messageBodyParts = parts.filter { it.part.fileName == null && it.mediaType in MessageBodyMediaTypes }
        val attachmentParts = parts.filter { it !in messageBodyParts }

        val attachments = attachmentParts.mapNotNull { part ->
            getAttachment(part, status)
        }

        val sender = message.from?.firstOrNull()?.let { map(it) }
        val plainTextBody = getPlainTextBody(messageBodyParts, status)

        val email = Email(
            sender, message.subject ?: "", map(message.sentDate ?: message.receivedDate),
            message.getRecipients(Message.RecipientType.TO).orEmpty().map { map(it) }, message.getRecipients(Message.RecipientType.CC).orEmpty().map { map(it) }, message.getRecipients(Message.RecipientType.BCC).orEmpty().map { map(it) },
            (message.replyTo?.firstOrNull() as? InternetAddress)?.let { if (it.address != sender?.address) map(it) else null }, // only set replyTo if it differs from sender
            status.folder.getUID(message),
            parts.any { it.mediaType == "application/pgp-encrypted" },
            imapMessage?.contentLanguage?.firstOrNull(),
            plainTextBody, getHtmlBody(messageBodyParts, status, plainTextBody),
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

    protected open fun getAttachment(messagePart: MessagePart, status: FetchEmailsStatus): EmailAttachment? {
        try {
            val part = messagePart.part
            if (part.fileName == null) { // not an attachment
                return null
            }

            val filename = File(part.fileName)
            val extension = filename.extension

            val invoiceAndFile = tryToReadEInvoice(part, extension, messagePart.mediaType, status)

            if (invoiceAndFile != null || Part.ATTACHMENT.equals(part.disposition, ignoreCase = true)) {
                val file = invoiceAndFile?.second ?:
                            if (extension !in status.options.downloadAttachmentsWithExtensions) null
                            else downloadAttachment(part, status)

                return EmailAttachment(part.fileName, extension, part.size.takeIf { it > 0 }, mapDisposition(part), messagePart.mediaType, part.contentType, invoiceAndFile?.first, file)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${messagePart.part.fileName}' (${messagePart.mediaType}) for eInvoice" }
            status.addError(FetchEmailsErrorType.GetAttachment, messagePart.part, e)
        }

        return null
    }

    private fun mapDisposition(part: Part) = when (part.disposition?.lowercase()) {
        "inline" -> ContentDisposition.Inline
        "attachment" -> ContentDisposition.Attachment
        null -> ContentDisposition.Body
        else -> ContentDisposition.Unknown
    }

    protected open fun tryToReadEInvoice(part: Part, extension: String, mediaType: String?, status: FetchEmailsStatus): Pair<Invoice, File>? = try {
        if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            val file = downloadAttachment(part, status)
            Pair(eInvoiceReader.extractFromPdf(part.inputStream), file)
        } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
            val file = downloadAttachment(part, status)
            Pair(eInvoiceReader.extractFromXml(part.inputStream), file)
        } else {
            null
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${part.fileName}" }
        status.addError(FetchEmailsErrorType.ExtractInvoice, part, e)
        null
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

    protected open fun getPlainTextBody(parts: Collection<MessagePart>, status: FetchEmailsStatus) =
        if (status.options.downloadMessageBody) getBodyWithMediaType(parts, "text/plain", status) else null

    protected open fun getHtmlBody(parts: Collection<MessagePart>, status: FetchEmailsStatus, plainTextBody: String?) =
        // in case of downloadOnlyPlainTextOrHtmlMessageBody == true, download html body only if there's no plain text body
        if (status.options.downloadMessageBody && (status.options.downloadOnlyPlainTextOrHtmlMessageBody == false || plainTextBody == null)) {
            getBodyWithMediaType(parts, "text/html", status)
        } else {
            null
        }

    protected open fun getBodyWithMediaType(parts: Collection<MessagePart>, mediaType: String, status: FetchEmailsStatus): String? = try {
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
        status.addError(FetchEmailsErrorType.GetMesssageBody, parts.map { it.part }, e)
        null
    }

    protected open fun map(date: Date): Instant =
        date.toInstant()


    protected open fun <T> connect(account: EmailAccount, options: FetchEmailsOptions, connected: (Store) -> T): T {
        val properties = mapAccountToJavaMailProperties(account, options)

        val session = Session.getInstance(properties)
        session.getStore("imap").use { store ->
            store.connect(account.serverAddress, account.username, account.password)

            return connected(store)
        }
    }

    protected open fun mapAccountToJavaMailProperties(account: EmailAccount, options: FetchEmailsOptions) = Properties().apply {
        put("mail.store.protocol", "imap")

        put("mail.imap.host", account.serverAddress)
        put("mail.imap.port", account.port?.toString() ?: "993")  // Default IMAP over SSL
        put("mail.imap.ssl.enable", "true")

        val timeout = (options.connectTimeoutSeconds * 1000).toString()
        put("mail.imap.connectiontimeout", timeout)
        put("mail.imap.timeout", timeout)
    }

}
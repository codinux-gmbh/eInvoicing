package net.codinux.invoicing.email

import jakarta.mail.Folder
import jakarta.mail.Message
import jakarta.mail.Multipart
import jakarta.mail.Part
import jakarta.mail.Session
import jakarta.mail.Store
import jakarta.mail.event.MessageCountAdapter
import jakarta.mail.event.MessageCountEvent
import kotlinx.coroutines.*
import net.codinux.invoicing.email.model.EmailAccount
import net.codinux.invoicing.email.model.EmailAttachment
import net.codinux.invoicing.email.model.Email
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import org.eclipse.angus.mail.imap.IMAPFolder
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
            connect(account) { store ->
                val folder = store.getFolder(options.emailFolderName) as IMAPFolder
                folder.open(Folder.READ_ONLY)

                val status = FetchEmailsStatus(account, options)

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
            return connect(account) { store ->
                val inbox = store.getFolder(options.emailFolderName)
                inbox.open(Folder.READ_ONLY)

                val status = FetchEmailsStatus(account, options)

                val emails = fetchAllEmailsInFolder(inbox, status).also {
                    inbox.close(false)
                }

                FetchEmailsResult(emails, null, status.messageSpecificErrors)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not fetch emails of account $account" }

            return FetchEmailsResult(emptyList(), e)
        }
    }

    protected open fun fetchAllEmailsInFolder(folder: Folder, status: FetchEmailsStatus): List<Email> = runBlocking {
        val messageCount = folder.messageCount
        if (messageCount <= 0) {
            return@runBlocking emptyList()
        }

        IntRange(1, messageCount).mapNotNull { messageNumber -> // message numbers start at 1
            async(coroutineDispatcher) {
                try {
                    getEmail(folder.getMessage(messageNumber), status)
                } catch (e: Throwable) {
                    log.error(e) { "Could not get email with messageNumber $messageNumber" }
                    status.addError(FetchEmailsErrorType.GetEmail, messageNumber, e)
                    null
                }
            }
        }
            .awaitAll()
            .filterNotNull()
    }

    protected open fun getEmail(message: Message, status: FetchEmailsStatus): Email? {
        val parts = getAllMessageParts(message)
        val messageBodyParts = parts.filter { it.part.fileName == null && it.mediaType in MessageBodyMediaTypes }
        val attachmentParts = parts.filter { it !in messageBodyParts }

        if (attachmentParts.any { it.mediaType in MessageBodyMediaTypes }) {
            log.info { "Ups, that does not seem to be a message part" }
        }

        val attachments = attachmentParts.mapNotNull { part ->
            findAttachment(part, status)
        }

        val email = Email(
            message.from?.joinToString(), message.subject ?: "",
            message.sentDate?.let { map(it) }, map(message.receivedDate), message.messageNumber,
            parts.any { it.mediaType == "application/pgp-encrypted" },
            getPlainTextBody(messageBodyParts, status), getHtmlBody(messageBodyParts, status),
            attachments
        )

        status.options.emailReceived(email)

        return email
    }

    protected open fun findAttachment(messagePart: MessagePart, status: FetchEmailsStatus): EmailAttachment? {
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

                return EmailAttachment(part.fileName, messagePart.mediaType, invoiceAndFile?.first, file)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${messagePart.part.fileName}' (${messagePart.mediaType}) for eInvoice" }
            status.addError(FetchEmailsErrorType.GetAttachment, messagePart.part, e)
        }

        return null
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
        File(status.userAttachmentsDownloadDirectory, part.fileName).also { file ->
            part.inputStream.use { it.copyTo(file.outputStream()) }
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

    protected open fun getHtmlBody(parts: Collection<MessagePart>, status: FetchEmailsStatus) =
        if (status.options.downloadMessageBody) getBodyWithMediaType(parts, "text/html", status) else null

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


    protected open fun <T> connect(account: EmailAccount, connected: (Store) -> T): T {
        val properties = mapAccountToJavaMailProperties(account)

        val session = Session.getInstance(properties)
        session.getStore("imap").use { store ->
            store.connect(account.serverAddress, account.username, account.password)

            return connected(store)
        }
    }

    protected open fun mapAccountToJavaMailProperties(account: EmailAccount) = Properties().apply {
        put("mail.store.protocol", "imap")

        put("mail.imap.host", account.serverAddress)
        put("mail.imap.port", account.port?.toString() ?: "993")  // Default IMAP over SSL
        put("mail.imap.ssl.enable", "true")

        put("mail.imap.connectiontimeout", "10000")
        put("mail.imap.timeout", "10000")
    }

}
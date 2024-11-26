package net.codinux.invoicing.mail

import jakarta.mail.Folder
import jakarta.mail.Message
import jakarta.mail.Multipart
import jakarta.mail.Part
import jakarta.mail.Session
import jakarta.mail.Store
import jakarta.mail.event.MessageCountAdapter
import jakarta.mail.event.MessageCountEvent
import kotlinx.coroutines.*
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
    protected open val eInvoiceReader: EInvoiceReader = EInvoiceReader()
) {

    protected data class MessagePart(
        val mediaType: String,
        val part: Part
    )


    protected open val mailDispatcher = Executors.newFixedThreadPool(max(24, Runtime.getRuntime().availableProcessors() * 4)).asCoroutineDispatcher()

    protected val log by logger()


    open fun listenForNewReceivedEInvoices(account: EmailAccount, downloadMessageBody: Boolean = false, emailFolderName: String = "INBOX",
                                           error: ((FetchEmailsError) -> Unit)? = null, eInvoiceReceived: (EmailWithInvoice) -> Unit) = runBlocking {
        try {
            connect(account) { store ->
                val folder = store.getFolder(emailFolderName)
                folder.open(Folder.READ_ONLY)

                val status = FetchEmailsStatus(FetchEmailsOptions(downloadMessageBody))

                folder.addMessageCountListener(object : MessageCountAdapter() {
                    override fun messagesAdded(event: MessageCountEvent) {
                        event.messages.forEach { message ->
                            findEInvoice(message, status)?.let {
                                eInvoiceReceived(it)
                            }
                        }
                    }
                })

                launch(mailDispatcher) {
                    keepConnectionOpen(account, folder)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Listening to new received eInvoices of '${account.username}' failed" }
            error?.invoke(FetchEmailsError(FetchEmailsErrorType.ListenForNewEmails, null, e))
        }

        log.info { "Stopped listening to new received eInvoices of '${account.username}'" }
    }

    protected open suspend fun keepConnectionOpen(account: EmailAccount, folder: Folder) {
        log.info { "Listening to new mails of ${account.username}" }

        // Use IMAP IDLE to keep the connection alive
        while (true) {
            if (!folder.isOpen) {
                log.info { "Reopening inbox of ${account.username} ..." }
                folder.open(Folder.READ_ONLY)
            }

            (folder as IMAPFolder).idle()

            delay(250)
        }
    }


    open fun listAllMessagesWithEInvoice(account: EmailAccount, downloadMessageBody: Boolean = false, emailFolderName: String = "INBOX"): FetchEmailsResult {
        try {
            return connect(account) { store ->
                val inbox = store.getFolder(emailFolderName)
                inbox.open(Folder.READ_ONLY)

                val status = FetchEmailsStatus(FetchEmailsOptions(downloadMessageBody))

                val mails = listAllMessagesWithEInvoiceInFolder(inbox, status).also {
                    inbox.close(false)
                }

                FetchEmailsResult(mails, null, status.messageSpecificErrors)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read mails of account $account" }

            return FetchEmailsResult(emptyList(), e)
        }
    }

    protected open fun listAllMessagesWithEInvoiceInFolder(folder: Folder, status: FetchEmailsStatus): List<EmailWithInvoice> = runBlocking {
        val messageCount = folder.messageCount
        if (messageCount <= 0) {
            return@runBlocking emptyList()
        }

        IntRange(1, messageCount).mapNotNull { messageNumber -> // message numbers start at 1
            async(mailDispatcher) {
                try {
                    findEInvoice(folder.getMessage(messageNumber), status)
                } catch (e: Throwable) {
                    log.error(e) { "Could not get message with messageNumber $messageNumber" }
                    status.addError(FetchEmailsErrorType.GetEmail, messageNumber, e)
                    null
                }
            }
        }
            .awaitAll()
            .filterNotNull()
    }

    protected open fun findEInvoice(message: Message, status: FetchEmailsStatus): EmailWithInvoice? {
        val parts = getAllMessageParts(message)

        val attachmentsWithEInvoice = parts.mapNotNull { part ->
            findEInvoice(part, status)
        }

        if (attachmentsWithEInvoice.isNotEmpty()) {
            return EmailWithInvoice(
                message.from?.joinToString(), message.subject ?: "",
                message.sentDate?.let { map(it) }, map(message.receivedDate), message.messageNumber,
                parts.any { it.mediaType == "application/pgp-encrypted" },
                getPlainTextBody(parts, status), getHtmlBody(parts, status),
                attachmentsWithEInvoice
            )
        }

        return null
    }

    protected open fun findEInvoice(messagePart: MessagePart, status: FetchEmailsStatus): EmailAttachmentWithEInvoice? {
        try {
            val part = messagePart.part
            val invoice = tryToReadEInvoice(part, messagePart.mediaType, status)

            if (invoice != null) {
                val filename = File(part.fileName)
                val file = File.createTempFile(filename.nameWithoutExtension, "." + filename.extension).also { file ->
                    part.inputStream.use { it.copyTo(file.outputStream()) }
                    file.deleteOnExit()
                }

                return EmailAttachmentWithEInvoice(part.fileName, messagePart.mediaType, invoice, file)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${messagePart.part.fileName}' (${messagePart.mediaType}) for eInvoice" }
            status.addError(FetchEmailsErrorType.GetAttachment, messagePart.part, e)
        }

        return null
    }

    protected open fun tryToReadEInvoice(part: Part, mediaType: String?, status: FetchEmailsStatus): Invoice? = try {
        val filename = part.fileName?.lowercase() ?: ""

        if (filename.endsWith(".pdf") || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            eInvoiceReader.extractFromPdf(part.inputStream)
        } else if (filename.endsWith(".xml") || mediaType == "application/xml" || mediaType == "text/xml") {
            eInvoiceReader.extractFromXml(part.inputStream)
        } else {
            null
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${part.fileName}" }
        status.addError(FetchEmailsErrorType.ExtractInvoice, part, e)
        null
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
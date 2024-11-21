package net.codinux.invoicing.mail

import jakarta.mail.Folder
import jakarta.mail.Message
import jakarta.mail.Multipart
import jakarta.mail.Part
import jakarta.mail.Session
import jakarta.mail.Store
import jakarta.mail.event.MessageCountAdapter
import jakarta.mail.event.MessageCountEvent
import jakarta.mail.internet.MimeMultipart
import kotlinx.coroutines.*
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import org.eclipse.angus.mail.imap.IMAPFolder
import java.io.File
import java.time.Instant
import java.util.*
import java.util.concurrent.Executors

class MailReader(
    private val eInvoiceReader: EInvoiceReader = EInvoiceReader()
) {

    private data class MessagePart(
        val mediaType: String,
        val part: Part
    )


    private val mailDispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()

    private val log by logger()


    fun listenForNewReceivedEInvoices(account: MailAccount, emailFolderName: String = "INBOX", eInvoiceReceived: (MailWithInvoice) -> Unit) = runBlocking {
        try {
            connect(account) { store ->
                val folder = store.getFolder(emailFolderName)
                folder.open(Folder.READ_ONLY)

                folder.addMessageCountListener(object : MessageCountAdapter() {
                    override fun messagesAdded(event: MessageCountEvent) {
                        event.messages.forEach { message ->
                            findEInvoice(message)?.let {
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
        }

        log.info { "Stopped listening to new received eInvoices of '${account.username}'" }
    }

    private suspend fun keepConnectionOpen(account: MailAccount, folder: Folder) {
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


    fun listAllMessagesWithEInvoice(account: MailAccount, emailFolderName: String = "INBOX"): List<MailWithInvoice> {
        try {
            connect(account) { store ->
                val inbox = store.getFolder(emailFolderName)
                inbox.open(Folder.READ_ONLY)

                listAllMessagesWithEInvoiceInFolder(inbox).also {
                    inbox.close(false)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read mails of account $account" }
        }

        return emptyList()
    }

    // tried to parallelize reading messages by reading them on multiple thread but that had no effect on process duration (don't know why)
    private fun listAllMessagesWithEInvoiceInFolder(folder: Folder): List<MailWithInvoice> = folder.messages.mapNotNull { message ->
        findEInvoice(message)
    }

    private fun findEInvoice(message: Message): MailWithInvoice? {
        try {
            val parts = getAllMessageParts(message)

            val attachmentsWithEInvoice = parts.mapNotNull { part ->
                findEInvoice(part)
            }

            if (attachmentsWithEInvoice.isNotEmpty()) {
                return MailWithInvoice(
                    message.from?.joinToString(), message.subject ?: "",
                    message.sentDate?.let { map(it) }, map(message.receivedDate), message.messageNumber,
                    getPlainTextBody(parts), getHtmlBody(parts),
                    attachmentsWithEInvoice
                )
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read message $message" }
        }

        return null
    }

    private fun getAllMessageParts(part: Part): List<MessagePart> {
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

    private fun findEInvoice(messagePart: MessagePart): MailAttachmentWithEInvoice? {
        try {
            val part = messagePart.part
            val invoice = tryToReadEInvoice(part, messagePart.mediaType)

            if (invoice != null) {
                val filename = File(part.fileName)
                val file = File.createTempFile(filename.nameWithoutExtension, "." + filename.extension).also { file ->
                    part.inputStream.use { it.copyTo(file.outputStream()) }
                    file.deleteOnExit()
                }

                return MailAttachmentWithEInvoice(part.fileName, messagePart.mediaType, invoice, file)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${messagePart.part.fileName}' (${messagePart.mediaType}) for eInvoice" }
        }

        return null
    }

    private fun tryToReadEInvoice(part: Part, mediaType: String?): Invoice? = try {
        val filename = part.fileName.lowercase()

        if (filename.endsWith(".pdf") || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            eInvoiceReader.extractFromPdf(part.inputStream)
        } else if (filename.endsWith(".xml") || mediaType == "application/xml" || mediaType == "text/xml") {
            eInvoiceReader.extractFromXml(part.inputStream)
        } else {
            null
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${part.fileName}" }
        null
    }

    /**
     * In most cases parameters are added to content-type's media type, e.g.
     * - text/html; charset=utf-8
     * - multipart/related; boundary="boundary-related"; type="text/html"
     *
     * -> This method removes parameters and return media type (first part) only
     */
    private fun getMediaType(part: Part): String? = part.contentType?.let { contentType ->
        val indexOfSeparator = contentType.indexOf(';')

        if (indexOfSeparator > -1) {
            contentType.substring(0, indexOfSeparator)
        } else {
            contentType
        }
    }

    private fun getPlainTextBody(parts: Collection<MessagePart>) = getBodyWithMediaType(parts, "text/plain")

    private fun getHtmlBody(parts: Collection<MessagePart>) = getBodyWithMediaType(parts, "text/html")

    private fun getBodyWithMediaType(parts: Collection<MessagePart>, mediaType: String): String? = try {
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
            } else {
                log.warn { "Multiple message parts with media type '$mediaType' found, but more than one does not have a filename" }
                partsForMediaTypeWithoutFilename.first().part.content as? String
            }
        }
    } catch (e: Throwable) {
        log.error(e) { "Could not get message body for media type '$mediaType'" }
        null
    }

    private fun map(date: Date): Instant =
        date.toInstant()


    private fun <T> connect(account: MailAccount, connected: (Store) -> T?): T? {
        val properties = mapAccountToJavaMailProperties(account)

        val session = Session.getInstance(properties)
        session.getStore("imap").use { store ->
            store.connect(account.serverAddress, account.username, account.password)

            return connected(store)
        }
    }

    private fun mapAccountToJavaMailProperties(account: MailAccount) = Properties().apply {
        put("mail.store.protocol", "imap")

        put("mail.imap.host", account.serverAddress)
        put("mail.imap.port", account.port?.toString() ?: "993")  // Default IMAP over SSL
        put("mail.imap.ssl.enable", "true")

        put("mail.imap.connectiontimeout", "5000")
        put("mail.imap.timeout", "5000")
    }

}
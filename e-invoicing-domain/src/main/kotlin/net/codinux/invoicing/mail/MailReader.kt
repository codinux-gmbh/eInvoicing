package net.codinux.invoicing.mail

import jakarta.mail.BodyPart
import jakarta.mail.Folder
import jakarta.mail.Message
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
        try {
            if (message.isMimeType("multipart/*")) {
                val multipart = message.content as MimeMultipart
                val parts = IntRange(0, multipart.count - 1).map { multipart.getBodyPart(it) }

                val attachmentsWithEInvoice = parts.mapNotNull { part ->
                    findEInvoice(part)
                }

                if (attachmentsWithEInvoice.isNotEmpty()) {
                    return@mapNotNull MailWithInvoice(
                        message.from.joinToString(), message.subject,
                        message.sentDate?.let { map(it) }, map(message.receivedDate), message.messageNumber,
                        attachmentsWithEInvoice
                    )
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read mail $message" }
        }

        null
    }

    private fun findEInvoice(part: BodyPart): MailAttachmentWithEInvoice? {
        try {
            if (Part.ATTACHMENT.equals(part.disposition, true)) { // TODO: what about Part.INLINE?
                val mediaType = getMediaType(part)?.lowercase()
                val invoice = tryToReadEInvoice(part, mediaType)

                if (invoice != null) {
                    val filename = File(part.fileName)
                    val file = File.createTempFile(filename.nameWithoutExtension, "." + filename.extension).also { file ->
                        part.inputStream.use { it.copyTo(file.outputStream()) }
                        file.deleteOnExit()
                    }

                    return MailAttachmentWithEInvoice(part.fileName, mediaType, invoice, file)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${part.fileName}' for eInvoice" }
        }

        return null
    }

    private fun tryToReadEInvoice(part: BodyPart, mediaType: String?): Invoice? = try {
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
    private fun getMediaType(part: BodyPart): String? = part.contentType?.let { contentType ->
        val indexOfSeparator = contentType.indexOf(';')

        if (indexOfSeparator > -1) {
            contentType.substring(0, indexOfSeparator)
        } else {
            contentType
        }
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
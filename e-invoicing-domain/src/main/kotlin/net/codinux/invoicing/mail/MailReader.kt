package net.codinux.invoicing.mail

import jakarta.mail.BodyPart
import jakarta.mail.Folder
import jakarta.mail.Part
import jakarta.mail.Session
import jakarta.mail.internet.MimeMultipart
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import java.io.File
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class MailReader(
    private val eInvoiceReader: EInvoiceReader = EInvoiceReader()
) {

    private val log by logger()


    fun listAllMessagesWithEInvoice(account: MailAccount): List<MailWithInvoice> {
        val properties = mapAccountToJavaMailProperties(account)

        try {
            val session = Session.getInstance(properties)
            session.getStore("imap").use { store ->
                store.connect(account.serverAddress, account.username, account.password)

                val inbox = store.getFolder("INBOX")
                inbox.open(Folder.READ_ONLY)

                return listAllMessagesWithEInvoiceInFolder(inbox).also {
                    inbox.close(false)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read mails of account $account" }
        }

        return emptyList()
    }

    private fun mapAccountToJavaMailProperties(account: MailAccount) = Properties().apply {
        put("mail.store.protocol", "imap")

        put("mail.imap.host", account.serverAddress)
        put("mail.imap.port", account.port?.toString() ?: "993")  // Default IMAP over SSL
        put("mail.imap.ssl.enable", "true")

        put("mail.imap.connectiontimeout", "5000")
        put("mail.imap.timeout", "5000")
    }


    private fun listAllMessagesWithEInvoiceInFolder(folder: Folder): List<MailWithInvoice> = folder.messages.mapNotNull { message ->
        try {
            if (message.isMimeType("multipart/*")) {
                val multipart = message.content as MimeMultipart
                val parts = IntRange(0, multipart.count - 1).map { multipart.getBodyPart(it) }

                val attachmentsWithEInvoice = parts.mapNotNull { part ->
                    findEInvoice(part)
                }

                if (attachmentsWithEInvoice.isNotEmpty()) {
                    return@mapNotNull MailWithInvoice(message.from.joinToString(), message.subject, map(message.sentDate), attachmentsWithEInvoice)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read mail $message" }
        }

        null
    }

    private fun findEInvoice(part: BodyPart): MailAttachmentWithEInvoice? {
        try {
            if (part.disposition == Part.ATTACHMENT) {
                val invoice = tryToReadEInvoice(part)
                if (invoice != null) {
                    var contentType = part.contentType
                    val indexOfSeparator = contentType.indexOf(';')
                    if (indexOfSeparator > -1) {
                        contentType = contentType.substring(0, indexOfSeparator)
                    }

                    val filename = File(part.fileName)
                    val file = File.createTempFile(filename.nameWithoutExtension, filename.extension).also { file ->
                        part.inputStream.use { it.copyTo(file.outputStream()) }
                    }

                    return MailAttachmentWithEInvoice(part.fileName, contentType, invoice, file)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not check attachment '${part.fileName}' for eInvoice" }
        }

        return null
    }

    private fun tryToReadEInvoice(part: BodyPart): Invoice? = try {
        val filename = part.fileName.lowercase()
        val contentType = part.contentType.lowercase()

        if (filename.endsWith(".pdf") || contentType.startsWith("application/pdf") || contentType.startsWith("application/octet-stream")) {
            eInvoiceReader.extractFromPdf(part.inputStream)
        } else if (filename.endsWith(".xml") || contentType.startsWith("application/xml") || contentType.startsWith("text/xml")) {
            eInvoiceReader.readFromXml(part.inputStream)
        } else {
            null
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${part.fileName}" }
        null
    }

    // TODO: same code as in MustangMapper
    private fun map(date: Date): LocalDate =
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

}
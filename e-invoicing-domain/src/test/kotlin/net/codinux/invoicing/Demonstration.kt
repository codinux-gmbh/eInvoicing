package net.codinux.invoicing

import net.codinux.invoicing.mail.MailAccount
import net.codinux.invoicing.mail.MailReader
import net.codinux.invoicing.reader.EInvoiceReader
import java.io.File

class Demonstration {

    fun read() {
        val reader = EInvoiceReader()

        // read a ZUGFeRD or Factor-X PDF that contains eInvoice XML as attachment
        val invoiceFromPDF = reader.extractFromPdf(File("ZUGFeRD.pdf"))

        // read a eInvoice XML file like XRechnung:
        val invoiceFromXml = reader.readFromXml(File("XRechnung.xml"))
    }

    fun fromMail() {
        val mailReader = MailReader()

        val mailsWithEInvoices = mailReader.listAllMessagesWithEInvoice(MailAccount(
            username = "", // your mail account username
            password = "", // your mail account username
            serverAddress = "", // IMAP server address
            port = null // IMAP server port, leave null if default port 993
        ))
    }
}
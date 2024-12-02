package net.codinux.invoicing

import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.email.model.EmailAccount
import net.codinux.invoicing.email.EmailsFetcher
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.InvoiceItem
import net.codinux.invoicing.model.Party
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import java.io.File
import java.math.BigDecimal
import java.time.LocalDate

class Demonstration {

    fun read() {
        val reader = EInvoiceReader()

        // extract invoice data from a ZUGFeRD or Factor-X PDF that contains eInvoice XML as attachment
        val invoiceFromPDF = reader.extractFromPdf(File("ZUGFeRD.pdf"))

        // extract eInvoice data from a XML file like XRechnung:
        val invoiceFromXml = reader.extractFromXml(File("XRechnung.xml"))
    }

    fun fromEmail() {
        val emailsFetcher = EmailsFetcher()

        val fetchResult = emailsFetcher.fetchAllEmails(EmailAccount(
            username = "", // your email account username
            password = "", // your email account username
            serverAddress = "", // IMAP server address
            port = null // IMAP server port, can be left null for default port 993
        ))

        fetchResult.emails.forEach { email ->
            println("${email.sender}: ${email.attachments.firstNotNullOfOrNull { it.invoice }?.totalAmounts?.duePayableAmount}")
        }
    }

    fun validate() {
        val validator = EInvoiceValidator()
        val invoiceFile = File("ZUGFeRD.pdf") // or XRechnung,xml, ...

        val result = validator.validate(invoiceFile)

        println("Is valid? ${result.isValid}")
        println(result.reportAsXml)
    }


    fun create() {
        val invoice = createInvoice()
        val pdfResultFile = File.createTempFile("Zugferd", ".pdf")

        val creator = EInvoiceCreator()

        // create a PDF that also contains the eInvoice as XML attachment
        creator.createPdfWithAttachedXml(invoice, pdfResultFile)

        // create only the XML file
        val xml = creator.createFacturXXml(invoice)

        // create a XRechnung
        val xRechnung = creator.createXRechnungXml(invoice)
    }

    fun attachInvoiceXmlToPdf() {
        val invoice: Invoice = createInvoice()
        val existingPdf = File("Invoice.pdf")
        val output = File("Zugferd.pdf")

        val creator = EInvoiceCreator()

        creator.attachInvoiceXmlToPdf(invoice, existingPdf, output)

        // or if you already have the invoice XML:
        val invoiceXml = creator.createXRechnungXml(invoice) // or creator.createZugferdXml(invoice), ...

        creator.attachInvoiceXmlToPdf(invoiceXml, EInvoiceXmlFormat.XRechnung, existingPdf, output)
    }


    private fun createInvoice() = Invoice(
        invoiceNumber = "RE-00001",
        invoiceDate = LocalDate.now(),
        supplier = Party("codinux GmbH & Co. KG", "Fun Street 1", null, "12345", "Glückstadt"),
        customer = Party("Abzock GmbH", "Ausbeutstr.", null, "12345", "Abzockhausen"),
        items = listOf(InvoiceItem("Erbrachte Dienstleistungen", BigDecimal(170), "HUR", BigDecimal(1_000_000), BigDecimal(19))) // HUR = EN code for hour
    )
}
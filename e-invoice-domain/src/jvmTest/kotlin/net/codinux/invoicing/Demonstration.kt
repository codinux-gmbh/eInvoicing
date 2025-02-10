package net.codinux.invoicing

import net.codinux.invoicing.creation.EInvoiceXmlCreator
import net.codinux.invoicing.creation.EInvoiceXmlToPdfAttacher
import net.codinux.invoicing.creation.EInvoicePdfCreator
import net.codinux.invoicing.email.model.EmailAccount
import net.codinux.invoicing.email.EmailsFetcher
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.UnitOfMeasure
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import java.io.File

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
            println("${email.sender}: ${email.attachments.firstNotNullOfOrNull { it.invoice }?.totals?.duePayableAmount}")
        }
    }

    fun validate() {
        val validator = EInvoiceValidator()
        val invoiceFile = File("ZUGFeRD.pdf") // or XRechnung,xml, ...

        val result = validator.validateEInvoicePdfJvm(invoiceFile.readBytes())

        result.value?.let {
            println("Is valid? ${it.isValid}")
            println(it.reportAsXml)
        }
    }


    fun create() {
        val invoice = createInvoice()
        val pdfResultFile = File.createTempFile("Zugferd", ".pdf")

        // create a PDF that also contains the eInvoice as XML attachment
        val pdfCreator = EInvoicePdfCreator()
        pdfCreator.createPdfWithAttachedXml(invoice, pdfResultFile)

        val xmlCreator = EInvoiceXmlCreator()

        // create only the XML file
        val xml = xmlCreator.createFacturXXmlJvm(invoice)

        // create a XRechnung
        val xRechnung = xmlCreator.createXRechnungXmlJvm(invoice)
    }

    fun attachInvoiceXmlToPdf() {
        val invoice: Invoice = createInvoice()
        val existingPdf = File("Invoice.pdf")
        val output = File("Zugferd.pdf")

        val attacher = EInvoiceXmlToPdfAttacher()
        val pdf = attacher.attachInvoiceXmlToPdfJvm(invoice, existingPdf.readBytes())

        // or if you already have the invoice XML:
        val xmlCreator = EInvoiceXmlCreator()
        val createInvoiceResult = xmlCreator.createXRechnungXmlJvm(invoice) // or creator.createZugferdXml(invoice), ...

        createInvoiceResult.value?.let { invoiceXml ->
            val result = attacher.attachInvoiceXmlToPdf(invoiceXml, EInvoiceXmlFormat.XRechnung, existingPdf.inputStream())
            result.value?.let { createdPdf ->
                output.writeBytes(createdPdf.bytes)
            }
        }
    }


    private fun createInvoice() = Invoice(
        details = InvoiceDetails("RE-00001", LocalDate(2024, 6, 15)),
        supplier = Party("codinux GmbH & Co. KG", "Fun Street 1", null, "12345", "Glückstadt"),
        customer = Party("Abzock GmbH", "Ausbeutstr.", null, "12345", "Abzockhausen"),
        items = listOf(InvoiceItem("Erbrachte Dienstleistungen", BigDecimal(170), UnitOfMeasure.HUR, BigDecimal(1_000_000), BigDecimal(19))) // HUR = EN code for hour
    )
}
package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.creation.EInvoiceXmlCreator
import net.codinux.invoicing.creation.EInvoiceXmlToPdfAttacher
import net.codinux.invoicing.creation.JvmEInvoicePdfCreator
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import java.io.File
import java.nio.file.Path

@Singleton
class InvoicingService {

    private val xmlCreator = EInvoiceXmlCreator()

    private val pdfCreator = JvmEInvoicePdfCreator()

    private val attacher = EInvoiceXmlToPdfAttacher()

    private val reader = EInvoiceReader()

    private val validator = EInvoiceValidator()


    fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat): String =
        xmlCreator.createInvoiceXml(invoice, format)

    fun createXRechnung(invoice: Invoice): String =
        xmlCreator.createXRechnungXml(invoice)

    fun createFacturXXml(invoice: Invoice): String =
        xmlCreator.createFacturXXml(invoice)

    fun createFacturXPdf(invoice: Invoice): Path {
        val resultFile = createTempPdfFile()

        pdfCreator.createPdfWithAttachedXml(invoice, resultFile.toFile())

        return resultFile
    }

    fun attachInvoiceXmlToPdf(invoice: Invoice, pdf: Path): Path {
        val resultFile = createTempPdfFile()

        attacher.attachInvoiceXmlToPdf(invoice, pdf.toFile(), resultFile.toFile())

        return resultFile
    }


    fun extractInvoiceDataFromPdf(invoiceFile: Path) =
        reader.extractFromPdf(invoiceFile.toFile())

    fun extractInvoiceDataFromXml(invoiceXml: String) =
        reader.extractFromXml(invoiceXml)


    fun validateInvoice(invoiceFile: Path) =
        validator.validate(invoiceFile.toFile())


    private fun createTempPdfFile(): Path =
        File.createTempFile("factur-x", ".pdf")
            .also { it.deleteOnExit() }
            .toPath()

}
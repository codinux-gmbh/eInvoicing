package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import java.io.File
import java.nio.file.Path

@Singleton
class InvoicingService {

    private val creator = EInvoiceCreator()

    private val reader = EInvoiceReader()

    private val validator = EInvoiceValidator()


    fun createXRechnung(invoice: Invoice): String =
        creator.createXRechnungXml(invoice)

    fun createFacturXXml(invoice: Invoice): String =
        creator.createFacturXXml(invoice)

    fun createFacturXPdf(invoice: Invoice): Path {
        val resultFile = createTempPdfFile()

        creator.createFacturXPdf(invoice, resultFile.toFile())

        return resultFile
    }

    fun attachInvoiceXmlToPdf(invoice: Invoice, pdf: Path): Path {
        val resultFile = createTempPdfFile()

        creator.attachInvoiceXmlToPdf(invoice, pdf.toFile(), resultFile.toFile())

        return resultFile
    }


    fun extractInvoiceDataFromPdf(invoiceFile: Path) =
        reader.extractFromPdf(invoiceFile.toFile())

    fun extractInvoiceDataFromXml(invoiceFile: Path) =
        reader.extractFromXml(invoiceFile.toFile())


    fun validateInvoice(invoiceFile: Path) =
        validator.validate(invoiceFile.toFile())


    private fun createTempPdfFile(): Path =
        File.createTempFile("factur-x", ".pdf")
            .also { it.deleteOnExit() }
            .toPath()

}
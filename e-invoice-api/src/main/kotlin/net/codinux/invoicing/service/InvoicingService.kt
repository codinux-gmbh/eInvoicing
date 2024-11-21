package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import java.io.File
import java.nio.file.Path
import kotlin.io.path.extension

@Singleton
class InvoicingService {

    private val creator = EInvoiceCreator()

    private val reader = EInvoiceReader()

    private val validator = EInvoiceValidator()


    fun createXRechnung(invoice: Invoice): String =
        creator.createXRechnungXml(invoice)

    fun createFacturXXml(invoice: Invoice): String =
        creator.createZugferdXml(invoice)

    fun createFacturXPdf(invoice: Invoice): Path {
        val resultFile = createTempPdfFile()

        creator.createZugferdPdf(invoice, resultFile.toFile())

        return resultFile
    }

    fun attachInvoiceXmlToPdf(invoice: Invoice, pdf: Path): Path {
        val resultFile = createTempPdfFile()

        creator.combinePdfAndInvoiceXml(invoice, pdf.toFile(), resultFile.toFile())

        return resultFile
    }


    fun extractInvoiceData(invoiceFile: Path) = when (invoiceFile.extension.lowercase()) {
        "xml" -> reader.readFromXml(invoiceFile.toFile())
        "pdf" -> reader.extractFromPdf(invoiceFile.toFile())
        else -> throw IllegalArgumentException("We can only extract eInvoice data from .xml and .pdf files")
    }


    fun validateInvoice(invoice: Path) =
        validator.validate(invoice.toFile())


    private fun createTempPdfFile(): Path =
        File.createTempFile("factur-x", ".pdf")
            .also { it.deleteOnExit() }
            .toPath()

}
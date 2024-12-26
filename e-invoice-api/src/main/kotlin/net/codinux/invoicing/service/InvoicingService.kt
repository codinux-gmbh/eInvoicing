package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.creation.EInvoiceXmlCreator
import net.codinux.invoicing.creation.EInvoiceXmlToPdfAttacher
import net.codinux.invoicing.creation.JvmEInvoicePdfCreator
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.PdfEInvoiceExtractionResult
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import net.codinux.invoicing.validation.InvoiceValidationResult
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*

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

    fun createFacturXPdf(invoice: Invoice, format: EInvoiceXmlFormat): Path {
        val resultFile = createTempPdfFile()

        pdfCreator.createPdfWithAttachedXml(invoice, resultFile.toFile(), format)

        return resultFile
    }

    fun attachInvoiceXmlToPdf(invoice: Invoice, pdf: Path, format: EInvoiceXmlFormat): Path {
        val resultFile = createTempPdfFile()

        attacher.attachInvoiceXmlToPdf(invoice, pdf.toFile(), resultFile.toFile(), format)

        return resultFile
    }

    fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat) =
        attachInvoiceXmlToPdf(createInvoiceXml(invoice, format), pdfFile, format)

    fun attachInvoiceXmlToPdf(invoiceXml: String, pdfFile: ByteArray, format: EInvoiceXmlFormat): Path {
        val resultFile = createTempPdfFile()

        attacher.attachInvoiceXmlToPdf(invoiceXml, format, pdfFile, resultFile.outputStream())

        return resultFile
    }


    fun extractInvoiceDataFromPdf(invoiceFile: Path, ignoreCalculationErrors: Boolean = false): PdfEInvoiceExtractionResult? =
        reader.mapPdfEInvoiceExtractionResult(reader.extractFromPdf(invoiceFile.toFile(), ignoreCalculationErrors))

    fun extractInvoiceDataFromXml(invoiceXml: String, ignoreCalculationErrors: Boolean = false) =
        reader.extractFromXmlJvm(invoiceXml, ignoreCalculationErrors)

    fun extractXmlFromPdf(pdfFile: Path) =
        reader.extractXmlFromPdf(pdfFile.toFile())


    fun validateInvoice(invoiceFile: Path, disableNotices: Boolean = false, invoiceFilename: String? = null): InvoiceValidationResult {
        var file = invoiceFile

        // Mustang writes the filename to validation report, so set a nice filename for the report
        if (invoiceFilename != null) {
            val destination = invoiceFile.parent.resolve(invoiceFile.name + "_folder").resolve(invoiceFilename)
            Files.createDirectories(destination.parent)
            file = invoiceFile.moveTo(destination, true)
        }

        return validator.validate(file.toFile(), disableNotices).also {
            // clean moved file
            file.deleteExisting()
            if (invoiceFilename != null) { // delete created folder
                file.parent.deleteExisting()
            }
        }
    }


    private fun createTempPdfFile(): Path =
        File.createTempFile("factur-x", ".pdf")
            .also { it.deleteOnExit() }
            .toPath()

}
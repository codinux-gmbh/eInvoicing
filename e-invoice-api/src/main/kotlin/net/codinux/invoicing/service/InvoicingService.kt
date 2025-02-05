package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.calculator.InvoiceItemPrice
import net.codinux.invoicing.config.DIJava
import net.codinux.invoicing.creation.EInvoicePdfCreator
import net.codinux.invoicing.creation.EInvoiceXmlCreator
import net.codinux.invoicing.creation.EInvoiceXmlToPdfAttacher
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoiceValidator
import net.codinux.invoicing.validation.InvoiceValidationResult
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*

@Singleton
class InvoicingService {

    private val xmlCreator = EInvoiceXmlCreator()

    private val pdfCreator = EInvoicePdfCreator()

    private val attacher = EInvoiceXmlToPdfAttacher()

    private val reader = EInvoiceReader()

    private val validator = EInvoiceValidator()

    private val amountsCalculator = AmountsCalculator()

    private val filesystem = DIJava.Filesystem


    fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, format)

    fun createXRechnung(invoice: Invoice): Result<String> =
        xmlCreator.createXRechnungXmlJvm(invoice)

    fun createFacturXXml(invoice: Invoice): Result<String> =
        xmlCreator.createFacturXXmlJvm(invoice)

    fun createFacturXPdf(invoice: Invoice, format: EInvoiceXmlFormat): Path {
        val resultFile = createTempPdfFile()

        pdfCreator.createPdfWithAttachedXml(invoice, format, resultFile)

        return resultFile
    }

    fun createFacturXPdf(invoiceXml: String, format: EInvoiceXmlFormat): Path {
        val resultFile = createTempPdfFile()

        pdfCreator.createPdfWithAttachedXml(invoiceXml, format, resultFile.toFile())

        return resultFile
    }


    fun attachInvoiceXmlToPdf(invoice: Invoice, pdf: Path, format: EInvoiceXmlFormat): Result<ByteArray> =
        attacher.attachInvoiceXmlToPdfJvm(invoice, pdf.readBytes(), format) // TODO: also return Result<Pdf>

    fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat) =
        createInvoiceXml(invoice, format).ifSuccessful { xml ->
            attachInvoiceXmlToPdf(xml, pdfFile, format)
        }

    fun attachInvoiceXmlToPdf(invoiceXml: String, pdfFile: ByteArray, format: EInvoiceXmlFormat): Result<Pdf> =
        attacher.attachInvoiceXmlToPdf(invoiceXml, format, pdfFile)


    fun extractInvoiceDataFromPdf(invoiceFile: Path) =
        reader.extractFromPdf(invoiceFile.toFile())

    fun extractInvoiceDataFromXml(invoiceXml: String) =
        reader.extractFromXmlJvm(invoiceXml)

    fun extractXmlFromPdf(pdfFile: Path) =
        reader.extractXmlFromPdf(pdfFile.toFile())


    fun validateInvoice(invoiceFile: Path, disableNotices: Boolean = false, invoiceFilename: String? = null): Result<InvoiceValidationResult> {
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


    fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>) =
        amountsCalculator.calculateTotalAmounts(itemPrices)


    private fun createTempPdfFile(): Path =
        filesystem.createTempPdfFile().toPath()

}
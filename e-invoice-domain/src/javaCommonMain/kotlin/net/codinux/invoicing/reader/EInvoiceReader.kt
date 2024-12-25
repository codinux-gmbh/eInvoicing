package net.codinux.invoicing.reader

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.PdfEInvoiceExtractionResultJvm
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.log.logger
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString
import kotlin.io.path.extension

actual open class EInvoiceReader(
    protected open val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader,
    protected open val mapper: MustangMapper = MustangMapper()
) {

    private val log by logger()


    open fun extractFromXmlOrNull(xmlFile: File, ignoreCalculationErrors: Boolean = false) = orNull { extractFromXml(xmlFile) }

    open fun extractFromXml(xmlFile: File, ignoreCalculationErrors: Boolean = false) = xmlFile.inputStream().use { extractFromXml(it) }

    open fun extractFromXmlOrNull(stream: InputStream, ignoreCalculationErrors: Boolean = false) = orNull { extractFromXml(stream) }

    open fun extractFromXml(stream: InputStream, ignoreCalculationErrors: Boolean = false) = extractFromXmlJvm(stream.reader().readText())

    open fun extractFromXmlOrNull(xml: String, ignoreCalculationErrors: Boolean = false) = orNull { extractFromXmlJvm(xml) }

    /**
     * Currently data extraction fails if calculated total amount is wrong. Is can be omitted by setting
     * [ignoreCalculationErrors] to true.
     */
    // TODO: find a better name
    open fun extractFromXmlJvm(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResultJvm {
        return try {
            val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to an Invoice object
            if (ignoreCalculationErrors) {
                importer.doIgnoreCalculationErrors()
            }

            try {
                importer.fromXML(xml)
            } catch (e: Throwable) {
                log.error(e) { "Invoice XML seems not to be a valid XML:\n$xml" }
                return ReadEInvoiceXmlResultJvm(ReadEInvoiceXmlResultType.InvalidXml, null, e)
            }

            ReadEInvoiceXmlResultJvm(ReadEInvoiceXmlResultType.Success, extractInvoice(importer), null)
        } catch (e: Throwable) {
            log.error(e) { "Could not extract invoice from XML:\n$xml" }
            return ReadEInvoiceXmlResultJvm(ReadEInvoiceXmlResultType.InvalidInvoiceData, null, e)
        }
    }

    actual open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean): ReadEInvoiceXmlResult? =
        extractFromXmlJvm(xml, ignoreCalculationErrors).let {
            ReadEInvoiceXmlResult(it.type, it.invoice)
        }


    open fun extractFromPdfOrNull(pdfFile: File, ignoreCalculationErrors: Boolean = false) = orNull { extractFromPdf(pdfFile) }

    open fun extractFromPdf(pdfFile: File, ignoreCalculationErrors: Boolean = false) = pdfFile.inputStream().use { extractFromPdf(it) }

    open fun extractFromPdfOrNull(stream: InputStream, ignoreCalculationErrors: Boolean = false) = orNull { extractFromPdf(stream) }

    /**
     * Currently data extraction fails if calculated total amount is wrong. Is can be omitted by setting
     * [ignoreCalculationErrors] to true.
     */
    open fun extractFromPdf(stream: InputStream, ignoreCalculationErrors: Boolean = false) =
        extractFromPdfInternal(stream.readAllBytes(), ignoreCalculationErrors)

    actual open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean): PdfEInvoiceExtractionResult? =
        mapPdfEInvoiceExtractionResult(extractFromPdfInternal(pdfFile, ignoreCalculationErrors))

    protected open fun extractFromPdfInternal(pdfFile: ByteArray, ignoreCalculationErrors: Boolean = false): PdfEInvoiceExtractionResultJvm {
        val attachmentsResult = extractXmlFromPdfJvm(pdfFile)
        val invoiceXml = attachmentsResult.invoiceXml
        if (attachmentsResult.type != PdfAttachmentExtractionResultType.HasXmlAttachments || invoiceXml.isNullOrBlank()) {
            return PdfEInvoiceExtractionResultJvm(null, attachmentsResult)
        }

        return PdfEInvoiceExtractionResultJvm(extractFromXmlJvm(invoiceXml, ignoreCalculationErrors), attachmentsResult)
    }

    open fun mapPdfEInvoiceExtractionResult(result: PdfEInvoiceExtractionResultJvm) =
        PdfEInvoiceExtractionResult(mapPdfExtractionResultType(result), result.invoice)

    protected open fun mapPdfExtractionResultType(result: PdfEInvoiceExtractionResultJvm): PdfExtractionResultType =
        if (result.attachmentExtractionResult.type != PdfAttachmentExtractionResultType.HasXmlAttachments || result.readEInvoiceXmlResult == null) {
            PdfExtractionResultType.valueOf(result.attachmentExtractionResult.type.name)
        } else {
            PdfExtractionResultType.valueOf(result.readEInvoiceXmlResult.type.name)
        }


    open fun extractXmlFromPdfOrNull(pdfFile: File) = orNull { extractXmlFromPdf(pdfFile) }

    open fun extractXmlFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractXmlFromPdf(it) }

    open fun extractXmlFromPdfOrNull(stream: InputStream) = orNull { extractXmlFromPdf(stream) }

    open fun extractXmlFromPdf(stream: InputStream): PdfAttachmentExtractionResult {
        return pdfAttachmentReader.getFileAttachments(stream)
    }

    // TODO: find a better name
    open fun extractXmlFromPdfJvm(pdfFile: ByteArray): PdfAttachmentExtractionResult {
        return pdfAttachmentReader.getFileAttachments(pdfFile)
    }

    actual open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult? =
        extractXmlFromPdfJvm(pdfFile)


    open fun extractFromFile(inputStream: InputStream, filename: String, directory: String? = null, mediaType: String? = null): FileEInvoiceExtractionResult = try {
        val extension = Path(filename).extension.lowercase()
        val path = if (directory != null) Path(directory).resolve(filename).absolutePathString() else filename

        if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            inputStream.use {
                FileEInvoiceExtractionResult(filename, directory, path, extractFromPdf(inputStream), null)
            }
        } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
            inputStream.use {
                FileEInvoiceExtractionResult(filename, directory, path, null, extractFromXml(inputStream))
            }
        } else {
            FileEInvoiceExtractionResult(filename, directory, path, null, null)
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${directory?.let { "$it/" } ?: ""}$filename" }

        val path = if (directory != null) Path(directory).resolve(filename).absolutePathString() else filename // duplicate code
        FileEInvoiceExtractionResult(filename, directory, path, null, null)
    }


    protected open fun extractInvoice(importer: ZUGFeRDInvoiceImporter): Invoice {
        val invoice = importer.extractInvoice()

        // TODO: the values LineTotalAmount, ChargeTotalAmount, AllowanceTotalAmount, TaxBasisTotalAmount, TaxTotalAmount,
        //  GrandTotalAmount, TotalPrepaidAmount adn DuePayableAmount are not extracted from XML document
        // we could use TransactionCalculator to manually calculate these values - Importer also does this and asserts
        // that its calculated value matches XML doc's GrandTotalAmount value. But then we would have to make some
        // methods of TransactionCalculator public
        // Another option would be to manually extract these values from XML document.

        return mapper.mapToInvoice(invoice)
    }


    protected open fun <T> orNull(action: () -> T): T? =
        try {
            action()
        } catch (e: Throwable) {
            log.debug(e) { "Action caused an exception, but orNull() was called" }
            null
        }

}
package net.codinux.invoicing.reader

import net.codinux.invoicing.extension.readAllBytesAndClose
import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.log.logger
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream
import kotlin.io.path.Path
import kotlin.io.path.extension

actual open class EInvoiceReader(
    protected open val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader,
    protected open val mapper: MustangMapper = MustangMapper()
) {

    actual constructor() : this(JavaPlatform.pdfAttachmentReader, MustangMapper())


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
    open fun extractFromXmlJvm(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResult =
        try {
            val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to an Invoice object
            if (ignoreCalculationErrors) {
                importer.doIgnoreCalculationErrors()
            }

            try {
                importer.fromXML(xml)

                val mapInvoiceResult = extractInvoice(importer)
                val resultType = if (mapInvoiceResult.invoiceDataErrors.isNotEmpty()) ReadEInvoiceXmlResultType.InvalidInvoiceData else ReadEInvoiceXmlResultType.Success
                ReadEInvoiceXmlResult(resultType, mapInvoiceResult)
            } catch (e: Throwable) {
                log.error(e) { "Invoice XML seems not to be a valid XML:\n$xml" }
                ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, e)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not extract invoice from XML:\n$xml" }
            ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidInvoiceData, e)
        }

    actual open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean): ReadEInvoiceXmlResult? =
        extractFromXmlJvm(xml, ignoreCalculationErrors)


    open fun extractFromPdfOrNull(pdfFile: File, ignoreCalculationErrors: Boolean = false) = orNull { extractFromPdf(pdfFile) }

    open fun extractFromPdf(pdfFile: File, ignoreCalculationErrors: Boolean = false) = pdfFile.inputStream().use { extractFromPdf(it) }

    open fun extractFromPdfOrNull(stream: InputStream, ignoreCalculationErrors: Boolean = false) = orNull { extractFromPdf(stream) }

    /**
     * Currently data extraction fails if calculated total amount is wrong. Is can be omitted by setting
     * [ignoreCalculationErrors] to true.
     */
    open fun extractFromPdf(stream: InputStream, ignoreCalculationErrors: Boolean = false) =
        extractFromPdfInternal(stream.readAllBytesAndClose(), ignoreCalculationErrors)

    actual open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean): ReadEInvoicePdfResult? =
        extractFromPdfInternal(pdfFile, ignoreCalculationErrors)

    protected open fun extractFromPdfInternal(pdfFile: ByteArray, ignoreCalculationErrors: Boolean = false): ReadEInvoicePdfResult {
        val attachmentsResult = extractXmlFromPdfJvm(pdfFile)
        val invoiceXml = attachmentsResult.invoiceXml
        if (attachmentsResult.type != PdfAttachmentExtractionResultType.HasXmlAttachments || invoiceXml.isNullOrBlank()) {
            return ReadEInvoicePdfResult(ReadEInvoicePdfResultType.valueOf(attachmentsResult.type.name), attachmentsResult)
        }

        val readXmlResult = extractFromXmlJvm(invoiceXml, ignoreCalculationErrors)
        return ReadEInvoicePdfResult(attachmentsResult, readXmlResult)
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

        if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            inputStream.use {
                FileEInvoiceExtractionResult(filename, directory, extractFromPdf(inputStream))
            }
        } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
            inputStream.use {
                FileEInvoiceExtractionResult(filename, directory, null, extractFromXml(inputStream))
            }
        } else {
            FileEInvoiceExtractionResult(filename, directory)
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${directory?.let { "$it/" } ?: ""}$filename" }

        FileEInvoiceExtractionResult(filename, directory, readError = SerializableException(e))
    }


    protected open fun extractInvoice(importer: ZUGFeRDInvoiceImporter): MapInvoiceResult {
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
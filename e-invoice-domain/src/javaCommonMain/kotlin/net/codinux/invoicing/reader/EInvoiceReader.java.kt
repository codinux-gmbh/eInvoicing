package net.codinux.invoicing.reader

import net.codinux.invoicing.extension.readAllBytesAndClose
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.getFileAttachments
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.log.logger
import java.io.File
import java.io.InputStream
import kotlin.io.path.Path
import kotlin.io.path.extension

actual open class EInvoiceReader(
    protected open val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader,
    protected open val xmlReader: EInvoiceXmlReader = EInvoiceXmlReader()
) {

    actual constructor() : this(JavaPlatform.pdfAttachmentReader)


    private val log by logger()


    open fun extractFromXmlOrNull(xmlFile: File) = orNull { extractFromXml(xmlFile) }

    open fun extractFromXml(xmlFile: File) = xmlFile.inputStream().use { extractFromXml(it) }

    open fun extractFromXmlOrNull(stream: InputStream) = orNull { extractFromXml(stream) }

    open fun extractFromXml(stream: InputStream) = extractFromXmlJvm(stream.reader().readText())

    open fun extractFromXmlOrNull(xml: String) = orNull { extractFromXmlJvm(xml) }

    // TODO: find a better name
    open fun extractFromXmlJvm(xml: String): ReadEInvoiceXmlResult =
        xmlReader.parseInvoiceXml(xml)

    actual open suspend fun extractFromXml(xml: String): ReadEInvoiceXmlResult =
        extractFromXmlJvm(xml)


    open fun extractFromPdfOrNull(pdfFile: File) = orNull { extractFromPdf(pdfFile) }

    open fun extractFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractFromPdf(it) }

    open fun extractFromPdfOrNull(stream: InputStream) = orNull { extractFromPdf(stream) }

    open fun extractFromPdf(stream: InputStream) =
        extractFromPdfInternal(stream.readAllBytesAndClose())

    actual open suspend fun extractFromPdf(pdfFile: ByteArray): ReadEInvoicePdfResult =
        extractFromPdfInternal(pdfFile)

    protected open fun extractFromPdfInternal(pdfFile: ByteArray): ReadEInvoicePdfResult {
        val attachmentsResult = extractXmlFromPdfJvm(pdfFile)
        val invoiceXml = attachmentsResult.invoiceXml
        if (attachmentsResult.type != PdfAttachmentExtractionResultType.HasXmlAttachments || invoiceXml.isNullOrBlank()) {
            return ReadEInvoicePdfResult(ReadEInvoicePdfResultType.from(attachmentsResult.type), attachmentsResult)
        }

        val readXmlResult = extractFromXmlJvm(invoiceXml)
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

    actual open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult =
        extractXmlFromPdfJvm(pdfFile)


    open fun extractFromFile(inputStream: InputStream, filename: String, directory: String? = null, mediaType: String? = null): ReadEInvoiceFileResult = try {
        val extension = Path(filename).extension.lowercase()

        if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            inputStream.use {
                ReadEInvoiceFileResult(filename, directory, extractFromPdf(inputStream))
            }
        } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
            inputStream.use {
                ReadEInvoiceFileResult(filename, directory, null, extractFromXml(inputStream))
            }
        } else {
            ReadEInvoiceFileResult(filename, directory)
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${directory?.let { "$it/" } ?: ""}$filename" }

        ReadEInvoiceFileResult(filename, directory, readError = SerializableException(e))
    }


    protected open fun <T> orNull(action: () -> T): T? =
        try {
            action()
        } catch (e: Throwable) {
            log.debug(e) { "Action caused an exception, but orNull() was called" }
            null
        }

}
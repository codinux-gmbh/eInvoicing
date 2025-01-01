package net.codinux.invoicing.reader

import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.log.Log

expect open class EInvoiceReader constructor() {

    open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResult?

    open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean = false): ReadEInvoicePdfResult?

    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult?

}


suspend fun EInvoiceReader.extractFromFile(fileContent: ByteArray, filename: String, directory: String? = null, mediaType: String? = null): FileEInvoiceExtractionResult = try {
    val extension = filename.substringAfterLast('.').lowercase()

    if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
        FileEInvoiceExtractionResult(filename, directory, extractFromPdf(fileContent), null)
    } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
        FileEInvoiceExtractionResult(filename, directory, null, extractFromXml(fileContent.decodeToString()))
    } else {
        FileEInvoiceExtractionResult(filename, directory, null, null)
    }
} catch (e: Throwable) {
    Log.debug(e) { "Could not extract invoices from ${directory?.let { "$it/" } ?: ""}$filename" }

    FileEInvoiceExtractionResult(filename, directory, null, null)
}
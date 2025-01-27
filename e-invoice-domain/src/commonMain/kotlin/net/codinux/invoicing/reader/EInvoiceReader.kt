package net.codinux.invoicing.reader

import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.kotlin.extensions.substringAfterLastOrNull
import net.codinux.log.Log

expect open class EInvoiceReader constructor() {

    open suspend fun extractFromXml(xml: String): ReadEInvoiceXmlResult?

    open suspend fun extractFromPdf(pdfFile: ByteArray): ReadEInvoicePdfResult?

    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult?

}


suspend fun EInvoiceReader.extractFromFile(fileContent: ByteArray, filename: String, directory: String? = null, mediaType: String? = null): ReadEInvoiceFileResult = try {
    val extension = filename.substringAfterLastOrNull('.')?.lowercase()

    if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
        ReadEInvoiceFileResult(filename, directory, extractFromPdf(fileContent), null)
    } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
        ReadEInvoiceFileResult(filename, directory, null, extractFromXml(fileContent.decodeToString()))
    } else {
        ReadEInvoiceFileResult(filename, directory, null, null)
    }
} catch (e: Throwable) {
    Log.debug(e) { "Could not extract invoices from ${directory?.let { "$it/" } ?: ""}$filename" }

    ReadEInvoiceFileResult(filename, directory, null, null)
}
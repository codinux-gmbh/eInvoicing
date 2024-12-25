package net.codinux.invoicing.reader

expect open class EInvoiceReader {

    open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResult?

    open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean = false): PdfEInvoiceExtractionResult?

//    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult

}
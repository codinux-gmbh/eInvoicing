package net.codinux.invoicing.reader

import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult

expect open class EInvoiceReader constructor() {

    open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResult?

    open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean = false): PdfEInvoiceExtractionResult?

    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult?

}
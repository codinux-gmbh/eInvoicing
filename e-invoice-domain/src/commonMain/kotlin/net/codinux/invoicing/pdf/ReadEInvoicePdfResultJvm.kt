package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.ReadEInvoiceXmlResult

data class ReadEInvoicePdfResultJvm(
    val readEInvoiceXmlResult: ReadEInvoiceXmlResult?,
    val attachmentExtractionResult: PdfAttachmentExtractionResult
) {
    val invoice: Invoice? = readEInvoiceXmlResult?.invoice

    override fun toString() = readEInvoiceXmlResult?.toString() ?: attachmentExtractionResult.toString()
}

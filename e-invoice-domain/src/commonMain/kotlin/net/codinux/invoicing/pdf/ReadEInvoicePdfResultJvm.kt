package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultJvm

data class ReadEInvoicePdfResultJvm(
    val readEInvoiceXmlResult: ReadEInvoiceXmlResultJvm?,
    val attachmentExtractionResult: PdfAttachmentExtractionResult
) {
    val invoice: Invoice? = readEInvoiceXmlResult?.invoice

    override fun toString() = readEInvoiceXmlResult?.toString() ?: attachmentExtractionResult.toString()
}

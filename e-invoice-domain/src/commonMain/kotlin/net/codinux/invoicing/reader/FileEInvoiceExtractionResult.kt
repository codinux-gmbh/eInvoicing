package net.codinux.invoicing.reader

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfEInvoiceExtractionResultJvm

data class FileEInvoiceExtractionResult(
    val filename: String,
    val directory: String?,
    val path: String,
    val pdf: PdfEInvoiceExtractionResultJvm?,
    val xml: ReadEInvoiceXmlResultJvm?
) {
    val invoice: Invoice? = pdf?.invoice ?: xml?.invoice

    override fun toString() = "$filename ${pdf ?: xml}"
}
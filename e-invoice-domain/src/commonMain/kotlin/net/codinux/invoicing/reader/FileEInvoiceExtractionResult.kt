package net.codinux.invoicing.reader

import net.codinux.invoicing.model.Invoice

data class FileEInvoiceExtractionResult(
    val filename: String,
    val directory: String?,
    val pdf: PdfEInvoiceExtractionResult?,
    val xml: ReadEInvoiceXmlResult?
) {
    val invoice: Invoice? = pdf?.invoice ?: xml?.invoice

    override fun toString() = "$filename ${pdf ?: xml}"
}
package net.codinux.invoicing.reader

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfEInvoiceExtractionResult
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

data class FileEInvoiceExtractionResult(
    val filename: String,
    val directory: String?,
    val pdf: PdfEInvoiceExtractionResult?,
    val xml: ReadEInvoiceXmlResult?
) {
    val invoice: Invoice? = pdf?.invoice ?: xml?.invoice

    val path: String by lazy { if (directory != null) Path(directory).resolve(filename).absolutePathString() else filename }

    override fun toString() = "$filename ${pdf ?: xml}"
}
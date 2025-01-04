package net.codinux.invoicing.email.model

import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.pdf.PdfInvoiceData

class EmailAttachment(
    val filename: String,
    val extension: String,
    val size: Int?,
    val disposition: ContentDisposition,
    /**
     * Attachment's media type like "application/xml", "application/pdf", ...
     *
     * Should always be non-null, but can theoretically be null.
     */
    val mediaType: String?,
    val contentType: String?,
    val invoice: MapInvoiceResult? = null,
    val pdfInvoiceData: PdfInvoiceData? = null,
    val downloadedFilePath: String? = null
) {
    val isPdfFile: Boolean by lazy { extension == "pdf" || mediaType == "application/pdf" }

    val containsEInvoice: Boolean by lazy { invoice != null }

    val couldExtractPdfInvoiceData: Boolean by lazy { pdfInvoiceData != null }

    override fun toString() = "$filename: $invoice"
}
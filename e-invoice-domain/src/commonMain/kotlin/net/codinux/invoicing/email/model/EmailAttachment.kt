package net.codinux.invoicing.email.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.pdf.PdfInvoiceData
import net.codinux.kotlin.annotation.JsonIgnore

@Serializable
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
    val mapInvoiceResult: MapInvoiceResult? = null,
    val pdfInvoiceData: PdfInvoiceData? = null,
    val downloadedFilePath: String? = null
) {
    @get:JsonIgnore // not that obvious, but Jackson affords @get:JsonIgnore instead of @delegate:JsonIgnore on delegates in order to work
    val isPdfFile: Boolean by lazy { extension == "pdf" || mediaType == "application/pdf" }

    @get:JsonIgnore
    val containsEInvoice: Boolean by lazy { mapInvoiceResult != null }

    @get:JsonIgnore
    val invoice: Invoice? by lazy { mapInvoiceResult?.invoice }

    @get:JsonIgnore
    val couldExtractPdfInvoiceData: Boolean by lazy { pdfInvoiceData != null }

    override fun toString() = "$filename: $mapInvoiceResult"
}
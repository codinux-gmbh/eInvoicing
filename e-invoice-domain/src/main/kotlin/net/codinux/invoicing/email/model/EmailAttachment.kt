package net.codinux.invoicing.email.model

import net.codinux.invoicing.model.Invoice
import java.io.File

class EmailAttachment(
    val filename: String?,
    val extension: String?,
    val size: Int?,
    val disposition: ContentDisposition,
    /**
     * Attachment's media type like "application/xml", "application/pdf", ...
     *
     * Should always be non-null, but can theoretically be null.
     */
    val mediaType: String?,
    val contentType: String?,
    val invoice: Invoice? = null,
    val file: File? = null
) {
    override fun toString() = "$filename: $invoice"
}
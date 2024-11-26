package net.codinux.invoicing.email

import net.codinux.invoicing.model.Invoice
import java.io.File

class EmailAttachmentWithEInvoice(
    val filename: String,
    /**
     * Attachment's media type like "application/xml", "application/pdf", ...
     *
     * Should always be non-null, but can theoretically be null.
     */
    val mediaType: String?,
    val invoice: Invoice,
    val file: File
) {
    override fun toString() = "$filename: $invoice"
}
package net.codinux.invoicing.mail

import net.codinux.invoicing.model.Invoice
import java.io.File

class MailAttachmentWithEInvoice(
    val filename: String,
    val contentType: String,
    val invoice: Invoice,
    val file: File
) {
    override fun toString() = "$filename: $invoice"
}
package net.codinux.invoicing.mail

import java.time.LocalDate

class MailWithInvoice(
    val sender: String,
    val subject: String,
    val date: LocalDate,
    val attachmentsWithEInvoice: List<MailAttachmentWithEInvoice>
) {
    override fun toString() = "$date $sender: $subject, ${attachmentsWithEInvoice.size} invoices"
}
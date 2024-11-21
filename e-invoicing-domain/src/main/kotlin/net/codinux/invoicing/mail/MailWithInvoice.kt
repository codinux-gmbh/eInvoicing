package net.codinux.invoicing.mail

import java.time.Instant
import java.time.ZoneId

class MailWithInvoice(
    val sender: String,
    val subject: String,
    val sent: Instant?,
    val received: Instant,
    val messageNumber: Int,
    val attachmentsWithEInvoice: List<MailAttachmentWithEInvoice>
) {
    override fun toString() = "${(sent ?: received).atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachmentsWithEInvoice.size} invoice(s)"
}
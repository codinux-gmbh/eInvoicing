package net.codinux.invoicing.mail

import java.time.Instant
import java.time.ZoneId

class MailWithInvoice(
    val sender: String,
    val subject: String,
    val sent: Instant?,
    val received: Instant,
    val messageNumber: Int,
    val plainTextBody: String?,
    val htmlBody: String?,
    val attachmentsWithEInvoice: List<MailAttachmentWithEInvoice>
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    override fun toString() = "${(sent ?: received).atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachmentsWithEInvoice.size} invoice(s)"
}
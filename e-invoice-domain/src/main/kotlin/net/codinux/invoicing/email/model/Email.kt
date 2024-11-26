package net.codinux.invoicing.email.model

import java.time.Instant
import java.time.ZoneId

class Email(
    val sender: String?,
    val subject: String,
    val sent: Instant?,
    val received: Instant,
    val messageId: Long,
    val isEncrypted: Boolean = false,
    val plainTextBody: String?,
    val htmlBody: String?,
    val attachments: List<EmailAttachment>
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    override fun toString() = "${(sent ?: received).atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachments.size} attachment(s)"
}
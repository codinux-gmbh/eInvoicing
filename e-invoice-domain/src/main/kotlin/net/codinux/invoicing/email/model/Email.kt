package net.codinux.invoicing.email.model

import java.time.Instant
import java.time.ZoneId

class Email(
    val sender: EmailAddress?,
    val subject: String,
    val to: List<EmailAddress>,
    val cc: List<EmailAddress>,
    val replayTo: EmailAddress?,
    val date: Instant,
    val messageId: Long,
    val isEncrypted: Boolean = false,
    val plainTextBody: String?,
    val htmlBody: String?,
    val attachments: List<EmailAttachment>
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    override fun toString() = "${date.atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachments.size} attachment(s)"
}
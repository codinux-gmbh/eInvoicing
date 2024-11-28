package net.codinux.invoicing.email.model

import java.time.Instant
import java.time.ZoneId

class Email(
    val sender: EmailAddress?,
    val subject: String,
    val date: Instant,
    val to: List<EmailAddress>,
    val cc: List<EmailAddress> = emptyList(),
    val bcc: List<EmailAddress> = emptyList(),
    val replayTo: EmailAddress? = null,
    val messageId: Long,
    val isEncrypted: Boolean = false,
    val contentLanguage: String? = null,
    val plainTextBody: String? = null,
    val htmlBody: String? = null,
    val attachments: List<EmailAttachment> = emptyList()
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    override fun toString() = "${date.atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachments.size} attachment(s)"
}
package net.codinux.invoicing.email.model

import java.time.Instant
import java.time.ZoneId

class Email(
    /**
     * Unique identifier of the message, used as identifier to retrieve further data of this message or as identifier
     * of the last downloaded message (to continue fetching emails at messages newer than the message with this id).
     *
     * Actually the IMAP UID, but the user should not care which ID this value refers to.
     * (messageUID: Unique identifier of a message in an email account. Survives e.g. expunging and moving the message to a different folder. This value.
     * messageNumber: Non stable number. Message numbers e.g. change on expunge.
     * messageId: Long string that is reference in inReplyTo field to identify to which message this message is a response of. Not of interest for us.
     */
    val messageId: Long,

    val sender: EmailAddress?,
    val subject: String,
    val date: Instant,

    val to: List<EmailAddress>,
    val cc: List<EmailAddress> = emptyList(),
    val bcc: List<EmailAddress> = emptyList(),
    val replayTo: EmailAddress? = null,

    val isEncrypted: Boolean = false,
    val contentLanguage: String? = null,
    val plainTextBody: String? = null,
    val htmlBody: String? = null,

    val attachments: List<EmailAttachment> = emptyList()
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    override fun toString() = "${date.atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachments.size} attachment(s)"
}
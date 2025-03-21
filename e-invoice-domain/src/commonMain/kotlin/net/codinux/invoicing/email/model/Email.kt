package net.codinux.invoicing.email.model

import net.codinux.invoicing.model.Instant

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

    val plainTextBody: String? = null,
    val htmlBody: String? = null,

    val contentLanguage: String? = null,
    val isEncrypted: Boolean = false,

    val attachments: List<EmailAttachment> = emptyList()
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    val hasAttachments: Boolean by lazy { attachments.isNotEmpty() }

    val hasPdfAttachment: Boolean by lazy { attachments.any { it.isPdfFile } }

    val hasEInvoiceAttachment: Boolean by lazy { attachments.any { it.containsEInvoice } }

    val hasAttachmentsWithExtractedInvoiceData: Boolean by lazy { attachments.any { it.couldExtractPdfInvoiceData } }


    override fun toString() = "${date.toLocalDateAtSystemDefaultZone()} $sender: $subject, ${attachments.size} attachment(s)"
}
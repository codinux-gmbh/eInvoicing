package net.codinux.invoicing.email.model

import java.time.Instant
import java.time.ZoneId

class Email(
    val sender: String?,
    val subject: String,
    val sent: Instant?,
    val received: Instant,
    /**
     * From documentation of underlying mail library:
     * "Since message numbers can change within a session if the folder is expunged, clients are advised not to use
     * message numbers as references to messages."
     *
     * -> use with care. Message numbers are not valid / the same anymore after expunging.
     */
    val messageNumber: Int,
    val isEncrypted: Boolean = false,
    val plainTextBody: String?,
    val htmlBody: String?,
    val attachments: List<EmailAttachment>
) {
    val plainTextOrHtmlBody: String? by lazy { plainTextBody ?: htmlBody }

    override fun toString() = "${(sent ?: received).atZone(ZoneId.systemDefault()).toLocalDate()} $sender: $subject, ${attachments.size} attachment(s)"
}
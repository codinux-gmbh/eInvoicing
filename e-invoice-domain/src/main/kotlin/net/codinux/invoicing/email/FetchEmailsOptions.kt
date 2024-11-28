package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import java.io.File

open class FetchEmailsOptions(
    /**
     * The ID of the last retrieved message. If set, only messages newer than this ID will be fetched.
     */
    val lastRetrievedMessageId: Long? = null,

    val downloadMessageBody: Boolean = false,
    /**
     * If set to true and message contains a plain text message body, then only the plain text message body is downloaded
     * and the HTML message body ignored / not downloaded. Reduces process time about 50 % (if no attachments get downloaded).
     */
    val downloadOnlyPlainTextOrHtmlMessageBody: Boolean = false,

    /**
     * Set the extension (without the dot) of files that should be downloaded.
     */
    val downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    val attachmentsDownloadDirectory: File = DefaultAttachmentsDownloadDirectory,

    val emailFolderName: String = "INBOX",
    val connectTimeoutSeconds: Int = 5,

    val onError: ((FetchEmailError) -> Unit)? = null,
    val onEmailReceived: ((Email) -> Unit)? = null
) {
    companion object {
        val DefaultDownloadedAttachmentsWithExtensions = emptyList<String>()

        val DefaultAttachmentsDownloadDirectory: File = File(System.getProperty("java.io.tmpdir"), "eInvoices").also { it.mkdirs() }
    }

    fun emailReceived(email: Email) {
        onEmailReceived?.invoke(email)
    }
}
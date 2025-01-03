package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import net.codinux.invoicing.email.model.FetchEmailError
import net.codinux.invoicing.model.LocalDate
import java.io.File
import java.time.Instant

open class FetchEmailsOptions(
    /**
     * The ID of the last retrieved message. If set, only messages newer than this ID will be fetched.
     */
    val lastRetrievedMessageId: Long? = null,

    val downloadMessageBody: Boolean = true,
    /**
     * If set to true and message contains a plain text message body, then only the plain text message body is downloaded
     * and the HTML message body ignored / not downloaded. Reduces process time about 50 % (if no attachments get downloaded).
     */
    val downloadOnlyPlainTextOrHtmlMessageBody: Boolean = true,
    val downloadOnlyMessagesNewerThan: LocalDate? = null,

    /**
     * Set the extension (without the dot) of files that should be downloaded.
     */
    val downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    val attachmentsDownloadDirectory: File = DefaultAttachmentsDownloadDirectory,

    val emailFolderName: String = "INBOX",
    val connectTimeoutSeconds: Int = 5,

    val showDebugOutputOnConsole: Boolean = false,

    val onError: ((FetchEmailError) -> Unit)? = null,
    val onEmailReceived: ((Email) -> Unit)? = null
) {
    companion object {
        val DefaultDownloadedAttachmentsWithExtensions = emptyList<String>()

        val DefaultAttachmentsDownloadDirectory: File = File(System.getProperty("java.io.tmpdir"), "eInvoices").also { it.mkdirs() }
    }


    val minMessageDate: Instant? by lazy { downloadOnlyMessagesNewerThan?.toJvmInstantAtSystemDefaultZone() }

    fun emailReceived(email: Email) {
        onEmailReceived?.invoke(email)
    }
}
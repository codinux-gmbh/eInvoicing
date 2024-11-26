package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import java.io.File

open class FetchEmailsOptions(
    val downloadMessageBody: Boolean = false,
    /**
     * Set the extension (without the dot) of files that should be downloaded.
     */
    val downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    val attachmentsDownloadDirectory: File = DefaultAttachmentsDownloadDirectory,

    val emailFolderName: String = "INBOX",
    val connectTimeoutSeconds: Int = 5,

    val onError: ((FetchEmailsError) -> Unit)? = null,
    val onEmailReceived: ((Email) -> Unit)? = null
) {
    companion object {
        val DefaultDownloadedAttachmentsWithExtensions = listOf("pdf", "xml")

        val DefaultAttachmentsDownloadDirectory: File = File(System.getProperty("java.io.tmpdir"), "eInvoices").also { it.mkdirs() }

        val IllegalFileCharacters = listOf('\\', '/', ':', '*', '?', '"', '<', '>', '|', '\u0000') // TODO: make generic for attachment filename
    }

    fun emailReceived(email: Email) {
        onEmailReceived?.invoke(email)
    }
}
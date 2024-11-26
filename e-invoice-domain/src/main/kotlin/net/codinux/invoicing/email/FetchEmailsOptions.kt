package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import java.io.File
import java.nio.file.Files

open class FetchEmailsOptions(
    val downloadMessageBody: Boolean = false,
    /**
     * Set the extension (without the dot) of files that should be downloaded.
     */
    val downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    val attachmentsDownloadDirectory: File = DefaultAttachmentsDownloadDirectory,
    val emailFolderName: String = "INBOX",

    val onError: ((FetchEmailsError) -> Unit)? = null,
    val onEmailReceived: ((Email) -> Unit)? = null
) {
    companion object {
        val DefaultDownloadedAttachmentsWithExtensions = listOf("pdf", "xml")

        val DefaultAttachmentsDownloadDirectory: File = Files.createTempDirectory("eInvoices").toFile()
    }

    fun emailReceived(email: Email) {
        onEmailReceived?.invoke(email)
    }
}
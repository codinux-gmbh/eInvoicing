package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email

open class FetchEmailsOptions(
    val downloadMessageBody: Boolean = false,
    /**
     * Set the extension (without the dot) of files that should be downloaded.
     */
    val downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    val emailFolderName: String = "INBOX",

    val onError: ((FetchEmailsError) -> Unit)? = null,
    val onEmailReceived: ((Email) -> Unit)? = null
) {
    companion object {
        val DefaultDownloadedAttachmentsWithExtensions = listOf("pdf", "xml")
    }

    fun emailReceived(email: Email) {
        onEmailReceived?.invoke(email)
    }
}
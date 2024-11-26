package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email

open class FetchEmailsOptions(
    val downloadMessageBody: Boolean = false,
    val emailFolderName: String = "INBOX",

    val onError: ((FetchEmailsError) -> Unit)? = null,
    val onEmailReceived: ((Email) -> Unit)? = null
) {
    fun emailReceived(email: Email) {
        onEmailReceived?.invoke(email)
    }
}
package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.EmailWithInvoice

open class FetchEmailsOptions(
    val downloadMessageBody: Boolean = false,
    val emailFolderName: String = "INBOX",

    val onError: ((FetchEmailsError) -> Unit)? = null,
    val onEmailReceived: ((EmailWithInvoice) -> Unit)? = null
) {
    fun emailReceived(email: EmailWithInvoice) {
        onEmailReceived?.invoke(email)
    }
}
package net.codinux.invoicing.email

data class FetchEmailsOptions(
    val downloadMessageBody: Boolean = false,
    val emailFolderName: String = "INBOX",

    val onError: ((FetchEmailsError) -> Unit)? = null,
    val onEmailReceived: ((EmailWithInvoice) -> Unit)? = null
) {
    fun emailReceived(email: EmailWithInvoice) {
        onEmailReceived?.invoke(email)
    }
}
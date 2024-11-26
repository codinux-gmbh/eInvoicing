package net.codinux.invoicing.email

open class ListenForNewMailsOptions(
    downloadMessageBody: Boolean = false,
    emailFolderName: String = "INBOX",

    onError: ((FetchEmailsError) -> Unit)? = null,
    onEmailReceived: (EmailWithInvoice) -> Unit
) : FetchEmailsOptions(downloadMessageBody, emailFolderName, onError, onEmailReceived) {
}
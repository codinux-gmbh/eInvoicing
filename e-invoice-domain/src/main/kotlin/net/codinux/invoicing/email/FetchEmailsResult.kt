package net.codinux.invoicing.email

data class FetchEmailsResult(
    val emails: List<EmailWithInvoice>,
    val overallError: Throwable?,
    val messageSpecificErrors: List<FetchEmailsError> = emptyList()
)
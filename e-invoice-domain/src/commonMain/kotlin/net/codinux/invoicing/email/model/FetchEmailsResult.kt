package net.codinux.invoicing.email.model

data class FetchEmailsResult(
    val emails: List<Email>,
    val overallError: Throwable?,
    val messageSpecificErrors: List<FetchEmailError> = emptyList()
)
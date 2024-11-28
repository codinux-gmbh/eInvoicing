package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email

data class FetchEmailsResult(
    val emails: List<Email>,
    val overallError: Throwable?,
    val messageSpecificErrors: List<FetchEmailError> = emptyList()
)
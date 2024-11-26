package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.EmailWithInvoice

data class FetchEmailsResult(
    val emails: List<EmailWithInvoice>,
    val overallError: Throwable?,
    val messageSpecificErrors: List<FetchEmailsError> = emptyList()
)
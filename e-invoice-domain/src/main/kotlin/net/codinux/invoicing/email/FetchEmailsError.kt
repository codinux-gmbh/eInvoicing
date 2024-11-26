package net.codinux.invoicing.email

data class FetchEmailsError(
    val type: FetchEmailsErrorType,
    val messageNumber: Int?,
    val error: Throwable
)
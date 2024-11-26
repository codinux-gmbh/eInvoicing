package net.codinux.invoicing.mail

data class FetchEmailsError(
    val type: FetchEmailsErrorType,
    val messageNumber: Int?,
    val error: Throwable
)
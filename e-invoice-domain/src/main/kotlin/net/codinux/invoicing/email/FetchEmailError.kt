package net.codinux.invoicing.email

data class FetchEmailError(
    val type: FetchEmailErrorType,
    val messageId: Long?,
    val error: Throwable
)
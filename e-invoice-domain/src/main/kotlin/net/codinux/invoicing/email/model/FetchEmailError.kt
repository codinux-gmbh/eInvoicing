package net.codinux.invoicing.email.model

data class FetchEmailError(
    val type: FetchEmailErrorType,
    val messageId: Long?,
    val error: Throwable
)
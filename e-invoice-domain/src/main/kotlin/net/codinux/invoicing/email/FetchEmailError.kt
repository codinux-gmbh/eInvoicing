package net.codinux.invoicing.email

data class FetchEmailError(
    val type: FetchEmailErrorType,
    val messageNumber: Int?,
    val error: Throwable
)
package net.codinux.invoicing.mail

data class ReadMailError(
    val type: ReadMailsErrorType,
    val messageNumber: Int?,
    val error: Throwable
)
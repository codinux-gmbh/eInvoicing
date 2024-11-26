package net.codinux.invoicing.mail

data class ReadMailsResult(
    val emails: List<MailWithInvoice>,
    val overallError: Throwable?,
    val messageSpecificErrors: List<ReadMailError> = emptyList()
)
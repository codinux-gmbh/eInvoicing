package net.codinux.invoicing.email

enum class FetchEmailsErrorType {
    GetEmail,

    GetMesssageBody,

    GetAttachment,

    ExtractInvoice,

    ListenForNewEmails
}
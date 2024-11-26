package net.codinux.invoicing.mail

enum class FetchEmailsErrorType {
    GetEmail,

    GetMesssageBody,

    GetAttachment,

    ExtractInvoice,

    ListenForNewEmails
}
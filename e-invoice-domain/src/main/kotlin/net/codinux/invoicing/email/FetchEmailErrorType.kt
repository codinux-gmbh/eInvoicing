package net.codinux.invoicing.email

enum class FetchEmailErrorType {
    GetEmail,

    GetMesssageBody,

    GetAttachment,

    ExtractInvoice,

    ListenForNewEmails
}
package net.codinux.invoicing.mail

enum class ReadMailsErrorType {
    GetEmail,

    GetMesssageBody,

    GetAttachment,

    ExtractInvoice,

    ListenForNewEmails
}
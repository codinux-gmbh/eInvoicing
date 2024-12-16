package net.codinux.invoicing.email.model

enum class FetchEmailErrorType {
    GetEmail,

    GetMesssageBody,

    GetAttachment,

    ExtractInvoice, // TODO: due to orNull() these errors aren't caught anymore

    ListenForNewEmails
}
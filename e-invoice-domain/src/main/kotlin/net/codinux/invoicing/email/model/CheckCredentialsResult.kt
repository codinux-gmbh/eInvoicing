package net.codinux.invoicing.email.model


enum class CheckCredentialsResult {

    Ok,

    WrongUsername,

    WrongPassword,

    InvalidImapServerAddress,

    InvalidImapServerPort,

    UnknownError

}
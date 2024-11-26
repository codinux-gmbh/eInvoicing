package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import java.util.concurrent.atomic.AtomicBoolean

open class ListenForNewMailsOptions(
    val stopListening: AtomicBoolean = AtomicBoolean(false),

    downloadMessageBody: Boolean = false,
    emailFolderName: String = "INBOX",

    onError: ((FetchEmailsError) -> Unit)? = null,
    onEmailReceived: (Email) -> Unit
) : FetchEmailsOptions(downloadMessageBody, emailFolderName, onError, onEmailReceived)
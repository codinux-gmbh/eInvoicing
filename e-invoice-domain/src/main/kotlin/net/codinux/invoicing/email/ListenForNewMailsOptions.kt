package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.EmailWithInvoice
import java.util.concurrent.atomic.AtomicBoolean

open class ListenForNewMailsOptions(
    val stopListening: AtomicBoolean = AtomicBoolean(false),

    downloadMessageBody: Boolean = false,
    emailFolderName: String = "INBOX",

    onError: ((FetchEmailsError) -> Unit)? = null,
    onEmailReceived: (EmailWithInvoice) -> Unit
) : FetchEmailsOptions(downloadMessageBody, emailFolderName, onError, onEmailReceived)
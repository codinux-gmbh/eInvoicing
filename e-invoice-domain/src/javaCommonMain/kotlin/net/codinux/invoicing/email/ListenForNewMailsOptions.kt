package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import net.codinux.invoicing.email.model.FetchEmailError
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean

open class ListenForNewMailsOptions(
    val stopListening: AtomicBoolean = AtomicBoolean(false),

    downloadMessageBody: Boolean = true,
    downloadOnlyPlainTextOrHtmlMessageBody: Boolean = true,

    downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    attachmentsDownloadDirectory: File = DefaultAttachmentsDownloadDirectory,

    emailFolderName: String = "INBOX",
    connectTimeoutSeconds: Int = 5,

    showDebugOutputOnConsole: Boolean = false,

    onError: ((FetchEmailError) -> Unit)? = null,
    onEmailReceived: (Email) -> Unit
) : FetchEmailsOptions(
    null,
    downloadMessageBody, downloadOnlyPlainTextOrHtmlMessageBody, null,
    downloadAttachmentsWithExtensions, attachmentsDownloadDirectory,
    emailFolderName, connectTimeoutSeconds, showDebugOutputOnConsole, onError, onEmailReceived
)
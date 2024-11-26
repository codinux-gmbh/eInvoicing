package net.codinux.invoicing.email

import net.codinux.invoicing.email.model.Email
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean

open class ListenForNewMailsOptions(
    val stopListening: AtomicBoolean = AtomicBoolean(false),

    downloadMessageBody: Boolean = false,
    downloadAttachmentsWithExtensions: List<String> = DefaultDownloadedAttachmentsWithExtensions,
    attachmentsDownloadDirectory: File = DefaultAttachmentsDownloadDirectory,

    emailFolderName: String = "INBOX",
    connectTimeoutSeconds: Int = 5,

    onError: ((FetchEmailsError) -> Unit)? = null,
    onEmailReceived: (Email) -> Unit
) : FetchEmailsOptions(downloadMessageBody, downloadAttachmentsWithExtensions, attachmentsDownloadDirectory, emailFolderName, connectTimeoutSeconds, onError, onEmailReceived)
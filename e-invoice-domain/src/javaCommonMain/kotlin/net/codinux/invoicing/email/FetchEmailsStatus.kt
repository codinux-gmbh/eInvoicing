package net.codinux.invoicing.email

import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Part
import jakarta.mail.Store
import net.codinux.invoicing.config.DIJava
import net.codinux.invoicing.email.model.EmailAccount
import net.codinux.invoicing.email.model.FetchEmailError
import net.codinux.invoicing.email.model.FetchEmailErrorType
import net.codinux.invoicing.filesystem.FilesystemService
import org.eclipse.angus.mail.imap.IMAPFolder
import java.io.File

data class FetchEmailsStatus(
    val account: EmailAccount,
    val store: Store,
    val folder: IMAPFolder,
    val options: FetchEmailsOptions,
    val messageSpecificErrors: MutableList<FetchEmailError> = mutableListOf(),
    val filesystem: FilesystemService = DIJava.Filesystem
) {

    val userAttachmentsDownloadDirectory: File by lazy {
        val userDirName = filesystem.removeIllegalFileCharacters(account.username)

        File(options.attachmentsDownloadDirectory, userDirName).also { it.mkdirs() }
    }


    fun addError(type: FetchEmailErrorType, messageId: Long?, error: Throwable) =
        addError(FetchEmailError(type, messageId, error))

    fun addError(error: FetchEmailError) {
        messageSpecificErrors.add(error)

        options.onError?.invoke(error)
    }

    private fun getMessage(part: Part): Message? {
        if (part is Message) {
            return part
        }

        (part as? BodyPart)?.parent?.parent?.let { parent ->
            return getMessage(parent)
        }

        return null
    }
}
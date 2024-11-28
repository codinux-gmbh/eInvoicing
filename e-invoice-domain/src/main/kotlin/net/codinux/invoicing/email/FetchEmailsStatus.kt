package net.codinux.invoicing.email

import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Part
import net.codinux.invoicing.email.model.EmailAccount
import net.codinux.invoicing.filesystem.FileUtil
import org.eclipse.angus.mail.imap.IMAPFolder
import java.io.File

data class FetchEmailsStatus(
    val account: EmailAccount,
    val folder: IMAPFolder,
    val options: FetchEmailsOptions,
    val messageSpecificErrors: MutableList<FetchEmailError> = mutableListOf()
) {

    val userAttachmentsDownloadDirectory: File by lazy {
        val userDirName = FileUtil.removeIllegalFileCharacters(account.username)

        File(options.attachmentsDownloadDirectory, userDirName).also { it.mkdirs() }
    }


    fun addError(type: FetchEmailErrorType, parts: Collection<Part>, error: Throwable) =
        addError(FetchEmailError(type, parts.firstNotNullOfOrNull { getMessage(it) }?.messageNumber, error))

    fun addError(type: FetchEmailErrorType, part: Part, error: Throwable) =
        addError(FetchEmailError(type, getMessage(part)?.messageNumber, error))

    fun addError(type: FetchEmailErrorType, messageNumber: Int?, error: Throwable) =
        addError(FetchEmailError(type, messageNumber, error))

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
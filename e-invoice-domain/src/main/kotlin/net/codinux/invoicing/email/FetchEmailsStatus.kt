package net.codinux.invoicing.email

import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Part
import net.codinux.invoicing.email.model.EmailAccount
import java.io.File

data class FetchEmailsStatus(
    val account: EmailAccount,
    val options: FetchEmailsOptions,
    val messageSpecificErrors: MutableList<FetchEmailsError> = mutableListOf()
) {

    val userAttachmentsDownloadDirectory: File by lazy {
        val userDirName = account.username.map { if (it in FetchEmailsOptions.IllegalFileCharacters || it.code < 32) '_' else it }.joinToString("")

        File(options.attachmentsDownloadDirectory, userDirName).also { it.mkdirs() }
    }


    fun addError(type: FetchEmailsErrorType, parts: Collection<Part>, error: Throwable) =
        addError(FetchEmailsError(type, parts.firstNotNullOfOrNull { getMessage(it) }?.messageNumber, error))

    fun addError(type: FetchEmailsErrorType, part: Part, error: Throwable) =
        addError(FetchEmailsError(type, getMessage(part)?.messageNumber, error))

    fun addError(type: FetchEmailsErrorType, messageNumber: Int?, error: Throwable) =
        addError(FetchEmailsError(type, messageNumber, error))

    fun addError(error: FetchEmailsError) {
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
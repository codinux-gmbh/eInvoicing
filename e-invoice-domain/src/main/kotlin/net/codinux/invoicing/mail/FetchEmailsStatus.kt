package net.codinux.invoicing.mail

import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Part

data class FetchEmailsStatus(
    val options: FetchEmailsOptions,
    val messageSpecificErrors: MutableList<FetchEmailsError> = mutableListOf(),
    val onError: ((FetchEmailsError) -> Unit)? = null
) {

    fun addError(type: FetchEmailsErrorType, parts: Collection<Part>, error: Throwable) =
        addError(FetchEmailsError(type, parts.firstNotNullOfOrNull { getMessage(it) }?.messageNumber, error))

    fun addError(type: FetchEmailsErrorType, part: Part, error: Throwable) =
        addError(FetchEmailsError(type, getMessage(part)?.messageNumber, error))

    fun addError(type: FetchEmailsErrorType, messageNumber: Int?, error: Throwable) =
        addError(FetchEmailsError(type, messageNumber, error))

    fun addError(error: FetchEmailsError) {
        messageSpecificErrors.add(error)

        onError?.invoke(error)
    }

    private fun getMessage(part: Part): Message? {
        if (part is Message) {
            return part
        }

        (part as? BodyPart)?.parent.let { parent ->
            return getMessage(part)
        }

        return null
    }
}
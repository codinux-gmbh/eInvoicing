package net.codinux.invoicing.mail

import jakarta.mail.BodyPart
import jakarta.mail.Message
import jakarta.mail.Part

data class ReadMailsStatus(
    val options: ReadMailsOptions,
    val mailSpecificErrors: MutableList<ReadMailError> = mutableListOf(),
    val error: ((ReadMailError) -> Unit)? = null
) {

    fun addError(type: ReadMailsErrorType, parts: Collection<Part>, error: Throwable) =
        addError(ReadMailError(type, parts.firstNotNullOfOrNull { getMessage(it) }?.messageNumber, error))

    fun addError(type: ReadMailsErrorType, part: Part, error: Throwable) =
        addError(ReadMailError(type, getMessage(part)?.messageNumber, error))

    fun addError(type: ReadMailsErrorType, messageNumber: Int?, error: Throwable) =
        addError(ReadMailError(type, messageNumber, error))

    fun addError(mailError: ReadMailError) {
        mailSpecificErrors.add(mailError)

        error?.invoke(mailError)
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
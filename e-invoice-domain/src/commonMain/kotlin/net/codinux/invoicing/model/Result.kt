package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
open class Result<T>(
    open val error: SerializableException?,
    open val value: T?
) {
    companion object {
        fun <T> error(error: SerializableException): Result<T> = ErrorResult(error)

        fun <T> error(error: Throwable): Result<T> = error(SerializableException(error))

        fun <T> success(result: T): Result<T> = Success(result)

        fun <T> of(error: Throwable?, value: T?) = Result(error?.let { SerializableException(it) }, value)
    }

    fun <K> ifSuccessful(action: (T) -> Result<K>): Result<K> =
        value?.let { action(it) } ?: Result(error, null)
}

class ErrorResult<T>(
    override val error: SerializableException
) : Result<T>(error, null) {
    override fun toString() = "Error: $error"
}

class Success<T>(
    override val value: T
) : Result<T>(null, value) {
    override fun toString() = "Success: $value"
}
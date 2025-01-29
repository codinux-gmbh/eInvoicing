package net.codinux.invoicing.model

open class Result<T>(
    open val error: Throwable?,
    open val value: T?
) {
    companion object {
        fun <T> error(error: Throwable): Result<T> = ErrorResult(error)

        fun <T> success(result: T): Result<T> = Success(result)
    }

    fun <K> ifSuccessful(action: (T) -> Result<K>): Result<K> =
        value?.let { action(it) } ?: Result(error, null)
}

class ErrorResult<T>(
    override val error: Throwable
) : Result<T>(error, null)

class Success<T>(
    override val value: T
) : Result<T>(null, value)
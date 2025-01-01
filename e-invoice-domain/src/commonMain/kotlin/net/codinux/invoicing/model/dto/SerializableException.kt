package net.codinux.invoicing.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class SerializableException(
    val type: String,
    val message: String? = null,
    val stackTrace: String? = null,
    val cause: SerializableException? = null
) {
    constructor(throwable: Throwable) : this(throwable::class.simpleName ?: "<unknown type>", // JavaScript throws an exception on .qualifiedName
        throwable.message, throwable.stackTraceToString(), throwable.cause?.let { SerializableException(it) })

    override fun toString() = "$type: $message"
}

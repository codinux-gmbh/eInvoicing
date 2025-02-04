package net.codinux.invoicing.model.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class SerializableException(
    val type: String,
    val message: String? = null,
    val stackTrace: String? = null,
    val cause: SerializableException? = null,
    @Transient
    val originalException: Throwable? = null
) {
    constructor(throwable: Throwable) : this(throwable::class.simpleName ?: "<unknown type>", // JavaScript throws an exception on .qualifiedName
        throwable.message, throwable.stackTraceToString(), throwable.cause?.let { SerializableException(it) }, throwable)

    override fun toString() = "$type: $message"
}

package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class ValidationError(
    val message: String,
    val location: String?,
    val test: String?
) {
    override fun toString() = "$location: $message"
}
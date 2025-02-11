package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class ValidationResultItem(
    val message: String,
    val location: String?,
    val test: String?
) {
    override fun toString() = "$location: $message"
}
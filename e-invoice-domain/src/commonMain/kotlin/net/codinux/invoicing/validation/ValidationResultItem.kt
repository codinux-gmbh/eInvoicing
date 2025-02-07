package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class ValidationResultItem(
    val severity: ValidationResultSeverity,
    val message: String,
    val location: String?,
    val criterion: String?
) {
    override fun toString() = "$severity: $message"
}
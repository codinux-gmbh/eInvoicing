package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class ValidationResultItem(
    val severity: ValidationResultSeverity,
    val message: String,
    val location: String?,
    val section: Int?,
    val criterion: String?,
    val stacktrace: String? = null
) {
    override fun toString() = "$severity: $message"
}
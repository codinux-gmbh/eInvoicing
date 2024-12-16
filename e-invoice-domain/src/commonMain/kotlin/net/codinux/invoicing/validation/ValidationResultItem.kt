package net.codinux.invoicing.validation

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
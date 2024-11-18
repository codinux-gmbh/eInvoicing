package net.codinux.invoicing.validation

class InvoiceValidationResult(
    val isValid: Boolean,
    val report: String
) {
    override fun toString() = when (isValid) {
        true -> "Valid: $report"
        false -> "Invalid: $report"
    }
}
package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class InvoiceXmlValidationResult(
    val isValid: Boolean,
    val errors: List<ValidationError>
) {
    override fun toString() = "isValid? $isValid, ${errors.joinToString()}"
}
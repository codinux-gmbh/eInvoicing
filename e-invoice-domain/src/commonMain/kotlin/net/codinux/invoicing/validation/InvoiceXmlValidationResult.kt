package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class InvoiceXmlValidationResult(
    val isValid: Boolean,
    val xmlValidationResults: List<ValidationResultItem>
) {
    override fun toString() = "isValid? $isValid, ${xmlValidationResults.joinToString()}"
}
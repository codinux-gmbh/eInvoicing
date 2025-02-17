package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class InvoiceXmlValidationResult(
    val isValid: Boolean,
    val businessRulesValidationResult: List<ValidationResultItem>
) {
    override fun toString() = "isValid? $isValid, ${businessRulesValidationResult.joinToString()}"
}
package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class InvoiceXmlValidationResult(
    val isValid: Boolean,
    val resultItems: List<ValidationResultItem>
) {
    override fun toString() = "isValid? $isValid, ${resultItems.joinToString()}"
}
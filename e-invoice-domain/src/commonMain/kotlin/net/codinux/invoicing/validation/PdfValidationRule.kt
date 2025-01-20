package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
data class PdfValidationRule(
    val specification: String,
    val clause: String,
    val testNumber: Int,
) {
    override fun toString(): String = "$specification $clause $testNumber"
}
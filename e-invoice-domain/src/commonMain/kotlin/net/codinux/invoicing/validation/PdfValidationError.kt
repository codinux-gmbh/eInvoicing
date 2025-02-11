package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
data class PdfValidationError(
    val englishMessage: String,
    val test: String,
    val category: String,
    val rule: PdfValidationRule,
    val references: List<PdfReference> = emptyList(),
    val errorArguments: List<String> = emptyList(),
    val locationInPdf: String? = null
) {
    override fun toString() = englishMessage
}
package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
data class PdfValidationResult(
    val isValid: Boolean,
    val isPdfA: Boolean,
    val isPdfA3: Boolean,
    val pdfAFlavor: PdfAFlavour,
    val countExecutedTests: Int,
    val validationErrors: List<PdfValidationError>
) {
    override fun toString() = "isValid? $isValid: ${validationErrors.joinToString()}"
}
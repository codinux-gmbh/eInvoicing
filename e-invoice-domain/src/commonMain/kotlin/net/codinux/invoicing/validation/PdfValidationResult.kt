package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
data class PdfValidationResult(
    val isValid: Boolean,
    val isPdfA: Boolean? = null,
    val isPdfA3: Boolean? = null,
    val pdfAFlavor: PdfAFlavour = PdfAFlavour.Unknown,
    val countExecutedTests: Int = 0,
    val validationErrors: List<PdfValidationError> = emptyList()
) {
    override fun toString() = "isValid? $isValid: ${validationErrors.joinToString()}"
}
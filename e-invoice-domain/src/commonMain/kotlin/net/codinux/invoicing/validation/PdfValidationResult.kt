package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Result

@Serializable
data class PdfValidationResult(
    val isValid: Boolean,
    val isPdfA: Boolean,
    val isPdfA3: Boolean,
    val pdfAFlavor: PdfAFlavour,
    val countExecutedTests: Int,
    val validationErrors: List<PdfValidationError>,
    val xmlValidationResult: Result<InvoiceXmlValidationResult>
) {
    override fun toString() = "isValid? $isValid: ${validationErrors.joinToString()}"
}
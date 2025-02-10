package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class InvoiceXmlValidationResult(
    val isValid: Boolean,
    val xmlValidationResults: List<ValidationResultItem>
) {
    val countXmlNotices: Int by lazy { xmlValidationResults.count { it.severity == ValidationResultSeverity.Notice } }

    val countXmlErrors: Int by lazy { xmlValidationResults.count { it.severity == ValidationResultSeverity.Error } }

    val countXmlFatalOrException: Int by lazy { xmlValidationResults.count { it.severity == ValidationResultSeverity.Fatal || it.severity == ValidationResultSeverity.Exception } }

    override fun toString() = "isValid? $isValid, ${xmlValidationResults.joinToString()}"
}
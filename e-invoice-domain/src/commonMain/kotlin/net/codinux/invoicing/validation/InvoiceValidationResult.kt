package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
class InvoiceValidationResult(
    /**
     * If XML and, if supplied, PDF is valid.
     */
    val isValid: Boolean,
    /**
     * If eInvoice XML is valid. If PDF is invalid, then [isValid] is false, but [isXmlValid] still can be true.
     */
    val isXmlValid: Boolean,
    /**
     *
     */
    val xmlValidationResults: List<ValidationResultItem>,
    /**
     * The validation report as a custom XML.
     */
    val reportAsXml: String
) {
    val countXmlNotices: Int by lazy { xmlValidationResults.count { it.severity == ValidationResultSeverity.Notice } }

    val countXmlErrors: Int by lazy { xmlValidationResults.count { it.severity == ValidationResultSeverity.Error } }

    val countXmlFatalOrException: Int by lazy { xmlValidationResults.count { it.severity == ValidationResultSeverity.Fatal || it.severity == ValidationResultSeverity.Exception } }

    override fun toString() = when (isValid) {
        true -> "Valid: $reportAsXml"
        false -> "Invalid: $reportAsXml"
    }
}
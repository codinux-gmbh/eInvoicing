package net.codinux.invoicing.format

data class EInvoiceFormatDetectionResult(
    val standard: EInvoicingStandard,
    val format: EInvoiceFormat? = null,
    val formatVersion: String? = null,
    val profile: FacturXProfile? = null,
) {
    override fun toString() = "$format${(formatVersion ?: profile)?.let { " ($it)" } ?: ""}"
}
package net.codinux.invoicing.format

data class EInvoiceFormatDetectionResult(
    val standard: EInvoicingStandard,
    val format: EInvoiceFormat? = null,
    val formatVersion: String? = null,
    val profile: FacturXProfile? = null,
) {
    companion object {
        val EInvoiceFormatDetectionResult?.isMinimumProfile: Boolean
            get() = this?.profile == FacturXProfile.Minimum

        val EInvoiceFormatDetectionResult?.isMinimumOrBasicWLProfile: Boolean
            get() = this.isMinimumProfile || this?.profile == FacturXProfile.BasicWL

        val EInvoiceFormatDetectionResult?.isNotMinimumOrBasicWLProfile: Boolean
            get() = this.isMinimumOrBasicWLProfile == false
    }

    override fun toString() = "$format${(formatVersion ?: profile)?.let { " ($it)" } ?: ""}"
}
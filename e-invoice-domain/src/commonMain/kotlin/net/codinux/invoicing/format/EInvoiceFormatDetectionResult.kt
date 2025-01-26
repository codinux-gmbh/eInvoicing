package net.codinux.invoicing.format

import net.codinux.invoicing.format.FacturXProfile.Companion.isMinimumOrBasicWL
import net.codinux.invoicing.format.FacturXProfile.Companion.isNotMinimumOrBasicWL

data class EInvoiceFormatDetectionResult(
    val standard: EInvoicingStandard,
    val format: EInvoiceFormat? = null,
    val formatVersion: String? = null,
    val profile: FacturXProfile? = null,
) {
    companion object {
        val EInvoiceFormatDetectionResult?.isMinimumOrBasicWLProfile: Boolean
            get() = this?.profile.isMinimumOrBasicWL

        val EInvoiceFormatDetectionResult?.isNotMinimumOrBasicWLProfile: Boolean
            get() = this?.profile.isNotMinimumOrBasicWL
    }

    override fun toString() = "$format${(formatVersion ?: profile)?.let { " ($it)" } ?: ""}"
}
package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Image
import net.codinux.invoicing.model.InvoiceLanguage

@Serializable
data class InvoicePdfTemplateSettings(
    val language: InvoiceLanguage? = null, // TODO: specify which values are allowed to be used per template
    val logo: Image? = null,
//    val fontFamily: String? = null,
//    val fontSize: Double? = null,
) {
    override fun toString() = "${language ?: "<user's default language>"}, logo = $logo"
}

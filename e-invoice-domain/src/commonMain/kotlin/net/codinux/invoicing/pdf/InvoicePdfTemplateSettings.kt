package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.InvoiceLanguage

@Serializable
data class InvoicePdfTemplateSettings(
    val language: InvoiceLanguage? = null, // TODO: specify which values are allowed to be used per template
    val logoUrl: String? = null, // TODO: support both, logoUrl and logoBytes
    val logoBytes: ByteArray? = null,
//    val fontFamily: String? = null,
//    val fontSize: Double? = null,
) {
    override fun toString() = "${language ?: "<user's default language>"}, logo = ${logoUrl ?: logoBytes?.let { "${it.size} bytes" } }"
}

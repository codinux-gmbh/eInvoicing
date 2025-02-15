package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.InvoiceLanguage

class InvoicePdfConfig(
    val xmlFormat: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX, // TODO: rename to xmlFormat?
//    val template: String, // TODO: add enum with available templates
    val language: InvoiceLanguage? = null, // TODO: specify which values are allowed to be used per template
    val logoUrl: String? = null, // TODO: support both, logoUrl and logoBytes
    val logoBytes: ByteArray? = null,
//    val fontFamily: String? = null,
//    val fontSize: Double? = null,
) {
    override fun toString() = "$xmlFormat ${language ?: "<user's default language>"}, logo = $logoUrl"
}
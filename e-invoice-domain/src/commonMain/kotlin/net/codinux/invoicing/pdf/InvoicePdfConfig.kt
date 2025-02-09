package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.InvoiceLanguage

class InvoicePdfConfig(
    val format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX,
    val language: InvoiceLanguage? = null, // TODO: add enum InvoiceLanguage
    val logoUrl: String? = null,
) {
    override fun toString() = "$format ${language ?: "<user's default language>"}, logo = $logoUrl"
}
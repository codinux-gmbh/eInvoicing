package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.EInvoiceXmlFormat

@Serializable
class InvoicePdfSettings(
    val xmlFormat: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX, // TODO: rename to xmlFormat?
//    val template: String, // TODO: add enum with available templates
    val templateSettings: InvoicePdfTemplateSettings? = null,
) {
    override fun toString() = "$xmlFormat $templateSettings"
}
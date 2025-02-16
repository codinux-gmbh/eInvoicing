package net.codinux.invoicing.model.dto

import kotlinx.serialization.Serializable
import net.codinux.invoicing.pdf.InvoicePdfSettings

@Serializable
data class CreatePdfFromInvoiceXmlDto(
    val invoiceXml: String,
    val settings: InvoicePdfSettings = InvoicePdfSettings()
) {
    override fun toString() = "$settings ${invoiceXml.substring(0, 250)}"
}
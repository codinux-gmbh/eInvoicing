package net.codinux.invoicing.model.dto

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.InvoicePdfSettings

@Serializable
data class CreatePdfDto(
    val invoice: Invoice,
    val settings: InvoicePdfSettings = InvoicePdfSettings()
) {
    override fun toString() = "$settings $invoice"
}
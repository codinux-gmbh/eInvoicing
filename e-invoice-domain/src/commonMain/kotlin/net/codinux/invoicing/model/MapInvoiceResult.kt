package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
data class MapInvoiceResult(
    val invoice: Invoice,
    val invoiceDataErrors: List<InvoiceDataError> = emptyList(),
) {
    override fun toString() = "${invoiceDataErrors.size} errors, $invoice"
}
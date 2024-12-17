package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.codes.Currency

@Serializable
class InvoiceDetails(
    val invoiceNumber: String,
    val invoiceDate: LocalDate,

    val currency: Currency = Currency.Euro,

//    val orderNumber: String? = null,
//    val orderDate: LocalDate? = null,

    val dueDate: LocalDate? = null,
    val paymentDescription: String? = null,
) {
    override fun toString() = "$invoiceDate $invoiceNumber"
}
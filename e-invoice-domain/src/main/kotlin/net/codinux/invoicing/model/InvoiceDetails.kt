package net.codinux.invoicing.model

import net.codinux.invoicing.model.codes.Currency
import java.time.LocalDate

class InvoiceDetails(
    val invoiceNumber: String,
    val invoiceDate: LocalDate,

    val currency: Currency = Currency.EUR,

    val dueDate: LocalDate? = null,
    val paymentDescription: String? = null,
) {
    override fun toString() = "$invoiceDate $invoiceNumber"
}
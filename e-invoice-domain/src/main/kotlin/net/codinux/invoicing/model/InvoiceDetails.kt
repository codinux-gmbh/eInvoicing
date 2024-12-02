package net.codinux.invoicing.model

import java.time.LocalDate

class InvoiceDetails(
    val invoiceNumber: String,
    val invoiceDate: LocalDate,

    val dueDate: LocalDate? = null,
    val paymentDescription: String? = null,
) {
    override fun toString() = "$invoiceDate $invoiceNumber"
}
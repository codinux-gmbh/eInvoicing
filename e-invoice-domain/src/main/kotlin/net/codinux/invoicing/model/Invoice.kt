package net.codinux.invoicing.model

import java.time.LocalDate

class Invoice(
    val invoiceNumber: String,
    val invoicingDate: LocalDate,
    val sender: Party,
    val recipient: Party,
    val items: List<LineItem>,

    val dueDate: LocalDate? = null,
    val paymentDescription: String? = null,

    /**
     * Unique reference number of the buyer, e.g. the Leitweg-ID required by German authorities (Behörden)
     */
    val buyerReference: String? = null
) {
    override fun toString() = "$invoicingDate $invoiceNumber to $recipient"
}
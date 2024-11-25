package net.codinux.invoicing.model

import java.time.LocalDate

class Invoice(
    val invoiceNumber: String,
    val invoicingDate: LocalDate,
    val sender: Party,
    val recipient: Party,
    val items: List<InvoiceItem>,

    val dueDate: LocalDate? = null,
    val paymentDescription: String? = null,

    /**
     * Unique reference number of the buyer, e.g. the Leitweg-ID required by German authorities (Behörden)
     */
    val buyerReference: String? = null,

    val amountAdjustments: AmountAdjustments? = null,

    /**
     * The total amounts of the invoice.
     *
     * For outgoing invoices: You don't have to calculate them, we do this for you. This ensures that all total amounts
     * are in accordance to other data of the invoice like the invoice item amounts and amount adjustments.
     */
    var totalAmounts: TotalAmounts? = null
) {
    override fun toString() = "$invoicingDate $invoiceNumber to $recipient ${totalAmounts?.duePayableAmount?.let { " (${it.toPlainString()})" } ?: ""}"
}
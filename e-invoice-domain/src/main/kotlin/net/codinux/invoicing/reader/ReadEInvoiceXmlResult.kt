package net.codinux.invoicing.reader

import net.codinux.invoicing.model.Invoice

data class ReadEInvoiceXmlResult(
    val type: ReadEInvoiceXmlResultType,
    val invoice: Invoice?,
    val readError: Throwable?
) {
    override fun toString() = if (invoice != null) "Success: $invoice"
                              else "Error: $readError"
}

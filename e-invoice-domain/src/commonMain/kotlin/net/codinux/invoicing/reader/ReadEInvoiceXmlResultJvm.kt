package net.codinux.invoicing.reader

import net.codinux.invoicing.model.Invoice

open class ReadEInvoiceXmlResultJvm(
    type: ReadEInvoiceXmlResultType,
    invoice: Invoice?,
    open val readError: Throwable? = null
) : ReadEInvoiceXmlResult(type, invoice) {
    override fun toString() = if (invoice != null) "Success: $invoice"
                              else "Error: $readError"
}
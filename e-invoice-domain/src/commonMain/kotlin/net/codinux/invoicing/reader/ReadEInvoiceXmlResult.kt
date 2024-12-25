package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice

@Serializable
open class ReadEInvoiceXmlResult(
    open val type: ReadEInvoiceXmlResultType,
    open val invoice: Invoice?,
) {
    override fun toString() = if (invoice != null) "Success: $invoice"
    else "Error: $type"
}
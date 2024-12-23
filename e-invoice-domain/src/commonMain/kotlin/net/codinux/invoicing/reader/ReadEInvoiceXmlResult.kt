package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.codinux.invoicing.model.Invoice

@Serializable
data class ReadEInvoiceXmlResult(
    val type: ReadEInvoiceXmlResultType,
    val invoice: Invoice?,
    @Transient
    val readError: Throwable? = null
) {
    override fun toString() = if (invoice != null) "Success: $invoice"
                              else "Error: $readError"
}

package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.codinux.invoicing.model.Invoice

@Serializable
open class ReadEInvoiceXmlResult(
    open val type: ReadEInvoiceXmlResultType,
    open val invoice: Invoice? = null,
    @Transient
    @kotlin.jvm.Transient
    open val readError: Throwable? = null
) {
    override fun toString() = if (invoice != null) "Success: $invoice"
                                else "Error: $type $readError"
}
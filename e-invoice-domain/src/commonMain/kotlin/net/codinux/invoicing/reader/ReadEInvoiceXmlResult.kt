package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
open class ReadEInvoiceXmlResult(
    open val type: ReadEInvoiceXmlResultType,
    open val invoice: Invoice? = null,
    open val readError: SerializableException? = null
) {
    constructor(type: ReadEInvoiceXmlResultType, readError: Throwable?) : this(type, null, readError?.let { SerializableException(it) })

    override fun toString() = if (invoice != null) "Success: $invoice"
                                else "Error: $type $readError"
}
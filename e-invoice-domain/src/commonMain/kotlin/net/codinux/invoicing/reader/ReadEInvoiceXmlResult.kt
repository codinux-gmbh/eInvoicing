package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
open class ReadEInvoiceXmlResult(
    open val type: ReadEInvoiceXmlResultType,
    open val invoice: MapInvoiceResult? = null,
    open val format: EInvoiceFormatDetectionResult? = null,
    open val readError: SerializableException? = null
) {
    constructor(type: ReadEInvoiceXmlResultType, readError: Throwable?) : this(type, null, null, readError?.let { SerializableException(it) })

    override fun toString() = if (invoice != null) "Success: $invoice"
                                else "Error: $type $readError"
}
package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.kotlin.annotation.JsonIgnore

@Serializable
data class ReadEInvoiceFileResult(
    val filename: String,
    val directory: String? = null,
    val readPdfResult: ReadEInvoicePdfResult? = null,
    val readXmlResult: ReadEInvoiceXmlResult? = null,
    val readError: SerializableException? = null
) {

    @get:JsonIgnore // not that obvious, but Jackson affords @get:JsonIgnore instead of @delegate:JsonIgnore on delegates in order to work
    val mapInvoiceResult: MapInvoiceResult?
        get() = readPdfResult?.invoice ?: readXmlResult?.invoice

    @get:JsonIgnore
    val invoice: Invoice?
        get() = mapInvoiceResult?.invoice

    override fun toString() = "$filename ${readPdfResult ?: readXmlResult ?: readError}"
}
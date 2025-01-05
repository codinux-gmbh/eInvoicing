package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
data class ReadEInvoiceFileResult(
    val filename: String,
    val directory: String? = null,
    val readPdfResult: ReadEInvoicePdfResult? = null,
    val readXmlResult: ReadEInvoiceXmlResult? = null,
    val readError: SerializableException? = null
) {

    // TODO: avoid that Jackson serializes these fields
    val mapInvoiceResult: MapInvoiceResult?
        get() = readPdfResult?.invoice ?: readXmlResult?.invoice

    val invoice: Invoice?
        get() = mapInvoiceResult?.invoice

    override fun toString() = "$filename ${readPdfResult ?: readXmlResult ?: readError}"
}
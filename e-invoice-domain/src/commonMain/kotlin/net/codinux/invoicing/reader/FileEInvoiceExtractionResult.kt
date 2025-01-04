package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
data class FileEInvoiceExtractionResult(
    val filename: String,
    val directory: String? = null,
    val pdf: ReadEInvoicePdfResult? = null,
    val xml: ReadEInvoiceXmlResult? = null,
    val readError: SerializableException? = null
) {
    val mapInvoiceResult: MapInvoiceResult? = pdf?.invoice ?: xml?.invoice

    val invoice: Invoice? = mapInvoiceResult?.invoice

    override fun toString() = "$filename ${pdf ?: xml ?: readError}"
}
package net.codinux.invoicing.model.dto

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType

@Serializable
data class ExtractInvoiceDataFromXmlDto(
    val type: ReadEInvoiceXmlResultType,
    val invoice: Invoice?,
)
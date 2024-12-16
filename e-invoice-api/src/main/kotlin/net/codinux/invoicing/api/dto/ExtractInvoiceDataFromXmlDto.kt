package net.codinux.invoicing.api.dto

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType

data class ExtractInvoiceDataFromXmlDto(
    val type: ReadEInvoiceXmlResultType,
    val invoice: Invoice?,
)
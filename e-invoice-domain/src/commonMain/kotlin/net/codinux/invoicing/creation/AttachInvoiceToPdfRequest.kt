package net.codinux.invoicing.creation

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice

@Serializable
data class AttachInvoiceToPdfRequest(
    val invoice: Invoice,
    val pdfFile: ByteArray,
    val format: EInvoiceXmlFormat
)
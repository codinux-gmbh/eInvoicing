package net.codinux.invoicing.creation

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.EInvoiceXmlFormat

@Serializable
data class AttachInvoiceXmlToPdfRequest(
    val invoiceXml: String,
    val pdfFile: ByteArray,
    val format: EInvoiceXmlFormat
)
package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable

@Serializable
data class PdfAttachment(
    val filename: String,
    val isXmlFile: Boolean,
    val isProbablyEN16931InvoiceXml: Boolean,
    val xml: String?
) {
    override fun toString() = "$filename (isInvoiceXml? $isProbablyEN16931InvoiceXml)"
}
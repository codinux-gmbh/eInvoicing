package net.codinux.invoicing.pdf

data class PdfAttachment(
    val filename: String,
    val isXmlFile: Boolean,
    val isProbablyEN16931InvoiceXml: Boolean,
    val xml: String?
) {
    override fun toString() = "$filename (isInvoiceXml? $isProbablyEN16931InvoiceXml)"
}
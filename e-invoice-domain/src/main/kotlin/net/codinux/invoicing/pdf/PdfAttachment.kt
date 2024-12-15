package net.codinux.invoicing.pdf

data class PdfAttachment(
    val filename: String,
    val isXmlFile: Boolean,
    val xml: String?
) {
    override fun toString() = "$filename (isXml? $isXmlFile)"
}
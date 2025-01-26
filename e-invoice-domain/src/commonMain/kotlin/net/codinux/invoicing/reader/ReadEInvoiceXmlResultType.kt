package net.codinux.invoicing.reader

enum class ReadEInvoiceXmlResultType {
    Success,
    UnsupportedInvoiceFormat,
    InvalidXml,
    InvalidInvoiceData
}
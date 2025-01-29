package net.codinux.invoicing.reader

enum class ReadEInvoiceXmlResultType {
    Success,
    TechnicalError,
    UnsupportedInvoiceFormat,
    InvalidXml,
    InvalidInvoiceData
}
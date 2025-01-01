package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice

@Serializable
data class ReadEInvoicePdfResult(
    val type: ReadEInvoicePdfResultType,
    val invoice: Invoice?,
)

/**
 * A combination of [net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType]
 * and [net.codinux.invoicing.reader.ReadEInvoiceXmlResultType]
 */
enum class ReadEInvoicePdfResultType {
    // PdfAttachmentExtractionResultType without HasXmlAttachments
    NotAPdf,
    NoAttachments,
    NoXmlAttachments,

    // ReadEInvoiceXmlResultType
    Success,
    InvalidXml,
    InvalidInvoiceData
}
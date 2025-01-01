package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult

@Serializable
data class ReadEInvoicePdfResult(
    val type: ReadEInvoicePdfResultType,
    val attachmentExtractionResult: PdfAttachmentExtractionResult,
    val invoice: Invoice? = null,
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
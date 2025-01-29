package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult

@Serializable
data class ReadEInvoicePdfResult(
    val type: ReadEInvoicePdfResultType,
    val attachmentExtractionResult: PdfAttachmentExtractionResult,
    val invoice: MapInvoiceResult? = null,
    val readError: SerializableException? = null
) {
    constructor(attachmentsResult: PdfAttachmentExtractionResult, readXmlResult: ReadEInvoiceXmlResult) :
            this(ReadEInvoicePdfResultType.valueOf(readXmlResult.type.name), attachmentsResult, readXmlResult.invoice, readXmlResult.readError)

    override fun toString() = "$type ${invoice ?: readError ?: attachmentExtractionResult}"
}

/**
 * A combination of [net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType]
 * and [net.codinux.invoicing.reader.ReadEInvoiceXmlResultType]
 */
enum class ReadEInvoicePdfResultType {
    // PdfAttachmentExtractionResultType without HasXmlAttachments
    TechnicalError,
    NotAPdf,
    NoAttachments,
    NoXmlAttachments,

    // ReadEInvoiceXmlResultType
    Success,
    InvalidXml,
    InvalidInvoiceData
}
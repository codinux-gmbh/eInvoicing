package net.codinux.invoicing.reader

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType

@Serializable
data class ReadEInvoicePdfResult(
    val type: ReadEInvoicePdfResultType,
    val attachmentExtractionResult: PdfAttachmentExtractionResult,
    val invoice: MapInvoiceResult? = null,
    val readError: SerializableException? = null
) {
    constructor(attachmentsResult: PdfAttachmentExtractionResult, readXmlResult: ReadEInvoiceXmlResult) :
            this(ReadEInvoicePdfResultType.from(readXmlResult.type), attachmentsResult, readXmlResult.invoice, readXmlResult.readError)

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
    UnsupportedInvoiceFormat,
    InvalidXml,
    InvalidInvoiceData
    ;


    companion object {
        fun from(attachmentsResultType: PdfAttachmentExtractionResultType) =
            ReadEInvoicePdfResultType.valueOf(attachmentsResultType.name)

        fun from(xmlResultType: ReadEInvoiceXmlResultType) =
            ReadEInvoicePdfResultType.valueOf(xmlResultType.name)
    }
}
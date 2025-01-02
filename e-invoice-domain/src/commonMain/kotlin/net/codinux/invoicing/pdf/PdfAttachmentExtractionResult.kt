package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.dto.SerializableException

@Serializable
data class PdfAttachmentExtractionResult(
    val type: PdfAttachmentExtractionResultType,
    val attachments: List<PdfAttachment>,
    val readError: SerializableException? = null
) {
    companion object {
        fun NoAttachments() =
            PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NoAttachments, emptyList())
    }


    constructor(type: PdfAttachmentExtractionResultType, readError: Throwable) : this(type, emptyList(), SerializableException(readError))


    // TODO: fix that Jackson should not serialize invoiceXml
    val invoiceXml: String? by lazy { attachments.firstOrNull { it.isProbablyEN16931InvoiceXml && it.xml != null }?.xml }

    override fun toString() = "$type: ${attachments.joinToString()}"
}
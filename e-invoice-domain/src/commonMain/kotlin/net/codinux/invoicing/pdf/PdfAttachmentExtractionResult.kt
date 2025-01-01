package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable

@Serializable
data class PdfAttachmentExtractionResult(
    val type: PdfAttachmentExtractionResultType,
    val attachments: List<PdfAttachment>
) {
    companion object {
        fun NoAttachments() =
            PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NoAttachments, emptyList())
    }


    // TODO: fix that Jackson should not serialize invoiceXml
    val invoiceXml: String? by lazy { attachments.firstOrNull { it.isProbablyEN16931InvoiceXml && it.xml != null }?.xml }

    override fun toString() = "$type: ${attachments.joinToString()}"
}
package net.codinux.invoicing.pdf

data class PdfAttachmentExtractionResult(
    val type: PdfAttachmentExtractionResultType,
    val attachments: List<PdfAttachment>
) {
    companion object {
        fun NoAttachments() =
            PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NoAttachments, emptyList())
    }

    override fun toString() = "$type: ${attachments.joinToString()}"
}
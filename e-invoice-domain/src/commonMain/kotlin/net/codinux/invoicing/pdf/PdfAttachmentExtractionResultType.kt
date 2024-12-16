package net.codinux.invoicing.pdf

enum class PdfAttachmentExtractionResultType {
    NotAPdf,
    NoAttachments,
    NoXmlAttachments,
    HasXmlAttachments
}
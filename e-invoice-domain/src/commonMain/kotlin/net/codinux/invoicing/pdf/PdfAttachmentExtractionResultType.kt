package net.codinux.invoicing.pdf

enum class PdfAttachmentExtractionResultType {
    TechnicalError,
    NotAPdf,
    NoAttachments,
    NoXmlAttachments,
    HasXmlAttachments
}
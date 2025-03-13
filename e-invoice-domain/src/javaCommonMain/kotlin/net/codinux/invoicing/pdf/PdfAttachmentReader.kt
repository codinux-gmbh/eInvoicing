package net.codinux.invoicing.pdf

interface PdfAttachmentReader {

    fun getFileAttachments(pdfFile: ByteArray): PdfAttachmentExtractionResult


    fun readPdfMetadata(pdfFile: ByteArray): Pair<PdfDocumentMetadata?, PdfDocumentMetadata?>

}
package net.codinux.invoicing.pdf

import java.io.InputStream

interface PdfAttachmentReader {

    fun getFileAttachments(pdfInputStream: InputStream): PdfAttachmentExtractionResult

}
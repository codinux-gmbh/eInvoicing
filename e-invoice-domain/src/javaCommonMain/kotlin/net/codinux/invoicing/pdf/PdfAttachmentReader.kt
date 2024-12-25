package net.codinux.invoicing.pdf

import java.io.InputStream

interface PdfAttachmentReader {

    fun getFileAttachments(pdfInputStream: InputStream) = getFileAttachments(pdfInputStream.readAllBytes())

    fun getFileAttachments(pdfFile: ByteArray): PdfAttachmentExtractionResult

}
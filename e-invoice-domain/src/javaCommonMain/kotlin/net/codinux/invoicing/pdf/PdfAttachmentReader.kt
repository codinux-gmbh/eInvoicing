package net.codinux.invoicing.pdf

import net.codinux.invoicing.extension.readAllBytesAndClose
import java.io.InputStream

interface PdfAttachmentReader {

    fun getFileAttachments(pdfInputStream: InputStream) = getFileAttachments(pdfInputStream.readAllBytesAndClose())

    fun getFileAttachments(pdfFile: ByteArray): PdfAttachmentExtractionResult


    fun readPdfMetadata(pdfFile: ByteArray): Pair<PdfDocumentMetadata?, PdfDocumentMetadata?>

}
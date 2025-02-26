package net.codinux.invoicing.pdf

import kotlinx.coroutines.await
import net.codinux.invoicing.pdf.declarations.PdfEmbeddedFile
import net.codinux.invoicing.pdf.declarations.PdfMetadataModule
import org.khronos.webgl.Uint8Array

class PdfReader {

    private val pdfReader = PdfMetadataModule.createPdfReader()

    suspend fun readEmbeddedFiles(pdfBytes: ByteArray): List<PdfEmbeddedFile> {
        val embeddedFiles = pdfReader.extractEmbeddedFiles(Uint8Array(pdfBytes.toTypedArray())).await()

        return embeddedFiles.toList()
    }

}
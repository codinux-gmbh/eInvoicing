package net.codinux.invoicing.pdf

import kotlinx.coroutines.await
import net.codinux.invoicing.pdf.declarations.PdfEmbeddedFile
import net.codinux.invoicing.pdf.declarations.PdfMetadataModule
import net.codinux.log.logger
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.set

class PdfReader {

    private val pdfReader = PdfMetadataModule.createPdfReader()

    private val log by logger()


    suspend fun readEmbeddedFiles(pdfBytes: ByteArray): List<PdfEmbeddedFile> {
        try {
            val embeddedFilesJs = pdfReader.extractEmbeddedFiles(pdfBytes.toUInt8Array()).await() as JsArray<PdfEmbeddedFile>

            val embeddedFiles = mutableListOf<PdfEmbeddedFile>()
            IntRange(0, embeddedFilesJs.length).forEach { index ->
                val embeddedFile = embeddedFilesJs[index]

                if (embeddedFile != null) {
                    embeddedFiles.add(embeddedFile)
                }
            }

            return embeddedFiles
        } catch (e: Throwable) {
            log.error(e) { "Could not read PDF embedded files" }
        }

        return emptyList()
    }

}


fun ByteArray.toUInt8Array(): Uint8Array {
    val uint8Array = Uint8Array(this.size)
    this.indices.forEach { i ->
        uint8Array[i] = this[i]
    }

    return uint8Array
}
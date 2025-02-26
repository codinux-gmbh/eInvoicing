package net.codinux.invoicing.pdf.declarations

import org.khronos.webgl.Uint8Array
import kotlin.js.Date
import kotlin.js.Promise


@JsModule("pdfmetadatareaderwriter")
@JsNonModule
external object PdfMetadataModule {
    fun createPdfReader(): PdfReader
}

@JsModule("pdfmetadatareaderwriter")
@JsNonModule
external class PdfReader {

    fun extractEmbeddedFiles(pdfBytes: Uint8Array): Promise<Array<PdfEmbeddedFile>>

}


@JsModule("pdfmetadatareaderwriter")
@JsNonModule
external class PdfEmbeddedFile {

    val filename: String

    val data: ByteArray

    val description: String?

    val relationship: String?

    val mimeType: String?

    val size: Int?

    val creationDate: Date?

    val modificationDate: Date?

}
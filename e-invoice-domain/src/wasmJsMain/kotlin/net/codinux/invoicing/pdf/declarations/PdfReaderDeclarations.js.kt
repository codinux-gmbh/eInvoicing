package net.codinux.invoicing.pdf.declarations

import net.codinux.invoicing.platform.JsDate
import org.khronos.webgl.Uint8Array
import kotlin.js.Promise

@JsModule("pdfmetadatareaderwriter")
external object PdfMetadataModule {
    fun createPdfReader(): PdfReader
}

@JsModule("pdfmetadatareaderwriter")
external class PdfReader {

    fun extractEmbeddedFiles(pdfBytes: Uint8Array): Promise<JsArray<PdfEmbeddedFile>>

}

@JsModule("pdfmetadatareaderwriter")
external class PdfEmbeddedFile : JsAny {

    val filename: String

    val data: Uint8Array

    val description: String?

    val relationship: String?

    val mimeType: String?

    val size: Int?

    val creationDate: JsDate?

    val modificationDate: JsDate?

}
package net.codinux.invoicing.pdf

import java.io.InputStream
import java.io.OutputStream

interface PdfAttachmentWriter {

    fun addFileAttachment(pdfFileInputStream: InputStream, attachmentName: String, xml: String, output: OutputStream) =
        addFileAttachment(pdfFileInputStream.readBytes(), attachmentName, xml, output)

    fun addFileAttachment(pdfFile: ByteArray, attachmentName: String, xml: String, output: OutputStream)

}
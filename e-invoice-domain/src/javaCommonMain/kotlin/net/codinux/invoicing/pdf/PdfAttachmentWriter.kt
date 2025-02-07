package net.codinux.invoicing.pdf

import net.codinux.invoicing.format.EInvoiceFormat
import java.io.InputStream
import java.io.OutputStream

interface PdfAttachmentWriter {

    fun addFileAttachment(pdfFileInputStream: InputStream, format: EInvoiceFormat, xml: String, output: OutputStream) =
        addFileAttachment(pdfFileInputStream.readBytes(), format, xml, output)

    fun addFileAttachment(pdfFile: ByteArray, format: EInvoiceFormat, xml: String, output: OutputStream)

}
package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.EInvoiceXmlFormat
import java.io.InputStream
import java.io.OutputStream

interface PdfAttachmentWriter {

    fun addFileAttachment(pdfFileInputStream: InputStream, format: EInvoiceXmlFormat, xml: String, output: OutputStream) =
        addFileAttachment(pdfFileInputStream.readBytes(), format, xml, output)

    fun addFileAttachment(pdfFile: ByteArray, format: EInvoiceXmlFormat, xml: String, output: OutputStream)

}
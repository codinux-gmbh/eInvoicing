package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.config.DIJava
import net.codinux.invoicing.extension.readAllBytesAndClose
import net.codinux.invoicing.filesystem.FilesystemService
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.PdfAttachmentWriter
import net.codinux.invoicing.platform.JavaPlatform
import java.io.File
import java.io.InputStream
import java.io.OutputStream

actual open class EInvoiceXmlToPdfAttacher(
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator(),
    protected open val attachmentWriter: PdfAttachmentWriter = JavaPlatform.pdfAttachmentWriter,
    protected open val filesystem: FilesystemService = DIJava.Filesystem
) {

    actual constructor() : this(EInvoiceXmlCreator(), JavaPlatform.pdfAttachmentWriter, DIJava.Filesystem)


    actual open suspend fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat): ByteArray? {
        val outputFile = filesystem.createTempPdfFile()

        attachInvoiceXmlToPdf(invoice, pdfFile, outputFile.outputStream(), format)

        return outputFile.readBytes()
    }


    @JvmOverloads
    open fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: File, outputFile: File, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX) =
        attachInvoiceXmlToPdf(invoice, pdfFile.readBytes(), outputFile.outputStream(), format)

    open fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, outputFile: OutputStream, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Unit {
        createXml(invoice, format).value?.let { invoiceXml ->
            attachInvoiceXmlToPdf(invoiceXml, format, pdfFile, outputFile)
        }
    }

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: File, outputFile: File) {
        pdfFile.inputStream().use { inputStream ->
            attachInvoiceXmlToPdf(invoiceXml, format, inputStream, outputFile.outputStream())
        }
    }

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: InputStream, outputFile: OutputStream) =
        attachInvoiceXmlToPdf(invoiceXml, format, pdfFile.readAllBytesAndClose(), outputFile)

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: ByteArray, outputFile: OutputStream) =
        attachmentWriter.addFileAttachment(pdfFile, format, invoiceXml, outputFile).also {
            outputFile.close()
        }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, format)

    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        Constants.getProfileNameForFormat(format)

}
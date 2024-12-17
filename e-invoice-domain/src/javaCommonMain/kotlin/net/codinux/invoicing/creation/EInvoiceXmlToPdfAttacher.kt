package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfAttachmentWriter
import net.codinux.invoicing.platform.JavaPlatform
import java.io.File
import java.io.InputStream
import java.io.OutputStream

open class EInvoiceXmlToPdfAttacher(
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator(),
    protected open val attachmentWriter: PdfAttachmentWriter = JavaPlatform.pdfAttachmentWriter
) {

    @JvmOverloads
    open fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: File, outputFile: File, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX) =
        attachInvoiceXmlToPdf(createXml(invoice, format), format, pdfFile, outputFile)

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: File, outputFile: File) {
        pdfFile.inputStream().use { inputStream ->
            outputFile.outputStream().use { outputStream ->
                attachInvoiceXmlToPdf(invoiceXml, format, inputStream, outputStream)
            }
        }
    }

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: InputStream, outputFile: OutputStream) =
        attachInvoiceXmlToPdf(invoiceXml, format, pdfFile.readAllBytes(), outputFile)

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: ByteArray, outputFile: OutputStream) =
        attachmentWriter.addFileAttachment(pdfFile, format, invoiceXml, outputFile)


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): String =
        xmlCreator.createInvoiceXml(invoice, format)

    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        Constants.getProfileNameForFormat(format)

}
package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.extension.readAllBytesAndClose
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.PdfAttachmentWriter
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.log.logger
import java.io.ByteArrayOutputStream
import java.io.InputStream

actual open class EInvoiceXmlToPdfAttacher(
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator(),
    protected open val attachmentWriter: PdfAttachmentWriter = JavaPlatform.pdfAttachmentWriter
) {

    actual constructor() : this(EInvoiceXmlCreator(), JavaPlatform.pdfAttachmentWriter)


    private val log by logger()


    actual open suspend fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat): Result<ByteArray> =
        try {
            createXml(invoice, format).value?.let { invoiceXml ->
                val result = attachInvoiceXmlToPdf(invoiceXml, format, pdfFile)
                result.ifSuccessful { Result.success(it.bytes) }
            }
                ?: Result(null, null)
        } catch (e: Throwable) {
            log.error(e) { "Could not attach invoice to PDF" }
            Result.error(e)
        }


    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: InputStream) =
        attachInvoiceXmlToPdf(invoiceXml, format, pdfFile.readAllBytesAndClose())

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: ByteArray): Result<Pdf> =
        ByteArrayOutputStream().use { outputStream ->
            attachmentWriter.addFileAttachment(pdfFile, format, invoiceXml, outputStream)

            Result.success(Pdf(outputStream.toByteArray()))
        }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, format)

    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        Constants.getProfileNameForFormat(format)

}
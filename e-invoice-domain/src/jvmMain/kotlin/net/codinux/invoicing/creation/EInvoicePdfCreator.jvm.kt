package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DIJava
import net.codinux.invoicing.filesystem.FilesystemService
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.*
import net.codinux.invoicing.reader.EInvoiceXmlReader
import net.codinux.log.logger
import java.io.File
import java.io.OutputStream
import java.nio.file.Path
import kotlin.io.path.outputStream

actual open class EInvoicePdfCreator(
    protected val templateService: TemplateService = HandlebarsTemplateService(),
    protected open val htmlToPdfConverter: HtmlToPdfConverter = OpenHtmlToPdfHtmlToPdfConverter(),
    protected open val attacher: EInvoiceXmlToPdfAttacher = EInvoiceXmlToPdfAttacher(),
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator(),
    protected open val xmlReader: EInvoiceXmlReader = EInvoiceXmlReader(), // TODO: make smarter
    protected open val filesystem: FilesystemService = DIJava.Filesystem
) {

    actual constructor() : this(HandlebarsTemplateService(), OpenHtmlToPdfHtmlToPdfConverter(), EInvoiceXmlToPdfAttacher(), EInvoiceXmlCreator())


    protected val invoiceHtmlTemplate by lazy { ResourceUtil.getResourceAsText("templates/invoice/Invoice.handlebars.html") }

    private val log by logger()


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoice: Invoice, format: EInvoiceXmlFormat): Result<ByteArray> {
        val destinationFile = filesystem.createTempPdfFile()

        createPdfWithAttachedXml(invoice, format, destinationFile)

        return Result.success(destinationFile.readBytes())
    }

    /**
     * Creates a hybrid PDF that also contains provided Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoiceXml: String, format: EInvoiceXmlFormat): Result<ByteArray> {
        val destinationFile = filesystem.createTempPdfFile()

        createPdfWithAttachedXml(invoiceXml, format, destinationFile)

        return Result.success(destinationFile.readBytes())
    }


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, outputFile: File) =
        createPdfWithAttachedXml(invoice, EInvoiceXmlFormat.FacturX, outputFile)

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, format: EInvoiceXmlFormat, outputFile: File): Result<Unit> =
        createXml(invoice, format).ifSuccessful { xml ->
            createPdfWithAttachedXml(xml, format, outputFile)
        }

    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: File) =
        createPdfWithAttachedXml(invoiceXml, format, outputFile.outputStream())

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, outputFile: Path) =
        createPdfWithAttachedXml(invoice, EInvoiceXmlFormat.FacturX, outputFile)

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, format: EInvoiceXmlFormat, outputFile: Path): Result<Unit> =
        createXml(invoice, format).ifSuccessful { xml ->
            createPdfWithAttachedXml(xml, format, outputFile)
        }

    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: Path) =
        createPdfWithAttachedXml(invoiceXml, format, outputFile.outputStream())

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     *
     * Closes the OutputStream of parameter [outputFile].
     */
    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: OutputStream): Result<Unit> =
        try {
            val readXmlResult = xmlReader.parseInvoiceXml(invoiceXml) // TODO: make smarter
            val invoice = readXmlResult.invoice?.invoice

            if (invoice == null) {
                readXmlResult.readError?.originalException?.let { Result.error(it) } ?: Result(null, null)
            } else {
                val html = templateService.renderTemplate(invoiceHtmlTemplate, invoice)
                val pdf = htmlToPdfConverter.createPdf(html)

                val result = attacher.attachInvoiceXmlToPdf(invoiceXml, format, pdf.bytes)
                result.ifSuccessful {
                    outputFile.write(it.bytes)
                    Result.success(Unit)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not create PDF with attached xml: $invoiceXml" }
            Result.error(e)
        }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, EInvoiceFormat.valueOf(format.name))

}
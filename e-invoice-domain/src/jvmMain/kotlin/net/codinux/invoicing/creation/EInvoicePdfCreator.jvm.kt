package net.codinux.invoicing.creation

import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.*
import net.codinux.invoicing.reader.EInvoiceXmlReader
import net.codinux.log.logger

actual open class EInvoicePdfCreator(
    protected val templateService: TemplateService = HandlebarsTemplateService(),
    protected open val htmlToPdfConverter: HtmlToPdfConverter = OpenHtmlToPdfHtmlToPdfConverter(),
    protected open val attacher: EInvoiceXmlToPdfAttacher = EInvoiceXmlToPdfAttacher(),
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator(),
    protected open val xmlReader: EInvoiceXmlReader = EInvoiceXmlReader(), // TODO: make smarter
) {

    actual constructor() : this(HandlebarsTemplateService(), OpenHtmlToPdfHtmlToPdfConverter(), EInvoiceXmlToPdfAttacher(), EInvoiceXmlCreator())


    protected val invoiceHtmlTemplate by lazy { ResourceUtil.getResourceAsText("templates/invoice/Invoice.handlebars.html") }

    private val log by logger()


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoice: Invoice, config: InvoicePdfConfig): Result<Pdf> =
        createPdfWithAttachedXml(invoice, config.xmlFormat)

    /**
     * Creates a hybrid PDF that also contains provided Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoiceXml: String, config: InvoicePdfConfig): Result<Pdf> =
        createPdfWithAttachedXml(invoiceXml, config.xmlFormat)


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice) =
        createPdfWithAttachedXml(invoice, EInvoiceXmlFormat.FacturX)

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<Pdf> =
        createXml(invoice, format).ifSuccessful { xml ->
            createPdfWithAttachedXml(xml, format)
        }

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat): Result<Pdf> =
        try {
            val readXmlResult = xmlReader.parseInvoiceXml(invoiceXml) // TODO: make smarter
            val invoice = readXmlResult.invoice?.invoice

            if (invoice == null) {
                readXmlResult.readError?.originalException?.let { Result.error(it) } ?: Result(null, null)
            } else {
                val html = templateService.renderTemplate(invoiceHtmlTemplate, invoice)
                val pdf = htmlToPdfConverter.createPdf(html)

                attacher.attachInvoiceXmlToPdf(invoiceXml, format, pdf.bytes)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not create PDF with attached xml: $invoiceXml" }
            Result.error(e)
        }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, EInvoiceFormat.valueOf(format.name))

}
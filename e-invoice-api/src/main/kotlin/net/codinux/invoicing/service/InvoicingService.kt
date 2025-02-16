package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.calculator.InvoiceItemPrice
import net.codinux.invoicing.creation.EInvoicePdfCreator
import net.codinux.invoicing.creation.EInvoiceXmlCreator
import net.codinux.invoicing.creation.EInvoiceXmlToPdfAttacher
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.InvoicePdfSettings
import net.codinux.invoicing.pdf.OpenHtmlToPdfHtmlToPdfConverter
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.validation.EInvoicePdfValidator
import net.codinux.invoicing.validation.EInvoiceXmlValidator
import net.codinux.invoicing.validation.InvoiceXmlValidationResult
import net.codinux.invoicing.validation.PdfValidationResult

@Singleton
class InvoicingService {

    private val xmlCreator = EInvoiceXmlCreator()

    private val pdfCreator = EInvoicePdfCreator()

    private val attacher = EInvoiceXmlToPdfAttacher()

    private val reader = EInvoiceReader()

    private val xmlValidator = EInvoiceXmlValidator()

    private val pdfValidator = EInvoicePdfValidator()

    private val amountsCalculator = AmountsCalculator()

    private val htmlToPdfConverter = OpenHtmlToPdfHtmlToPdfConverter()


    fun createInvoiceXml(invoice: Invoice, format: EInvoiceFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, format)

    fun createXRechnung(invoice: Invoice): Result<String> =
        xmlCreator.createXRechnungXmlJvm(invoice)

    fun createFacturXXml(invoice: Invoice): Result<String> =
        xmlCreator.createFacturXXmlJvm(invoice)

    suspend fun createFacturXPdf(invoice: Invoice, settings: InvoicePdfSettings): Result<Pdf> =
        pdfCreator.createFacturXPdf(invoice, settings)

    suspend fun createFacturXPdf(invoiceXml: String, settings: InvoicePdfSettings): Result<Pdf> =
        pdfCreator.createFacturXPdf(invoiceXml, settings)


    fun createPdfFromHtml(html: String): Result<Pdf> =
        try {
            Result.success(htmlToPdfConverter.createPdf(html))
        } catch (e: Throwable) {
            Result.error(e)
        }


    fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat) =
        createInvoiceXml(invoice, EInvoiceFormat.valueOf(format.name)).ifSuccessful { xml ->
            attachInvoiceXmlToPdf(xml, pdfFile, format)
        }

    fun attachInvoiceXmlToPdf(invoiceXml: String, pdfFile: ByteArray, format: EInvoiceXmlFormat): Result<Pdf> =
        attacher.attachInvoiceXmlToPdf(invoiceXml, format, pdfFile)


    suspend fun extractInvoiceDataFromPdf(pdfBytes: ByteArray) =
        reader.extractFromPdf(pdfBytes)

    fun extractInvoiceDataFromXml(invoiceXml: String) =
        reader.extractFromXmlJvm(invoiceXml)

    suspend fun extractXmlFromPdf(pdfBytes: ByteArray) =
        reader.extractXmlFromPdf(pdfBytes)


    fun validateInvoiceXml(invoiceXml: String): Result<InvoiceXmlValidationResult> =
        xmlValidator.validateEInvoiceXmlJvm(invoiceXml)

    fun validateInvoicePdf(pdfBytes: ByteArray): Result<PdfValidationResult> =
        pdfValidator.validate(pdfBytes)


    fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>) =
        amountsCalculator.calculateTotalAmounts(itemPrices)

}
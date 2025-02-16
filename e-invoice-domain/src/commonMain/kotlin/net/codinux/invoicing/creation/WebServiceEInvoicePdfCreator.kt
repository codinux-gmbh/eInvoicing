package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.model.dto.CreatePdfDto
import net.codinux.invoicing.model.dto.CreatePdfFromInvoiceXmlDto
import net.codinux.invoicing.pdf.InvoicePdfSettings
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoicePdfCreator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun createFacturXPdf(invoice: Invoice, settings: InvoicePdfSettings = InvoicePdfSettings()): Result<Pdf> =
        createRequest(CreatePdfDto(invoice, settings), "create/pdf")

    open suspend fun createFacturXPdf(invoiceXml: String, settings: InvoicePdfSettings = InvoicePdfSettings()): Result<Pdf> =
        createRequest(CreatePdfFromInvoiceXmlDto(invoiceXml, settings), "create/pdf/fromXml")


    protected open suspend fun <T> createRequest(body: T, urlPath: String): Result<Pdf> {
        val response = webClient.postAsync(RequestParameters(urlPath, ByteArray::class, body, ContentTypes.JSON, ContentTypes.PDF))

        return Result.of(response.error, response.body?.let { Pdf(it) })
    }

}
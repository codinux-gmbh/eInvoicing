package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.InvoicePdfConfig
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoicePdfCreator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun createFacturXPdf(invoice: Invoice, config: InvoicePdfConfig = InvoicePdfConfig()): Result<Pdf> =
        createRequest(invoice, ContentTypes.JSON, config)

    open suspend fun createFacturXPdf(invoiceXml: String, config: InvoicePdfConfig = InvoicePdfConfig()): Result<Pdf> =
        createRequest(invoiceXml, ContentTypes.XML, config)


    protected open suspend fun <T> createRequest(body: T, contentType: String, config: InvoicePdfConfig): Result<Pdf> {
        val queryParameters = buildMap<String, Any> {
            put("format", config.format.name)
        }
        val response = webClient.postAsync(RequestParameters("create/facturx/pdf", ByteArray::class, body, contentType, ContentTypes.OCTET_STREAM, queryParameters = queryParameters))

        return Result.of(response.error, response.body?.let { Pdf(it) })
    }

}
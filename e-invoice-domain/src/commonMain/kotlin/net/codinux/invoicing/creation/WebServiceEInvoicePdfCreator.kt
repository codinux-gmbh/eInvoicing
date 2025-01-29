package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoicePdfCreator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun createFacturXPdf(invoice: Invoice, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Result<ByteArray> =
        createRequest(invoice, ContentTypes.JSON, format)

    open suspend fun createFacturXPdf(invoiceXml: String, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Result<ByteArray> =
        createRequest(invoiceXml, ContentTypes.XML, format)


    protected open suspend fun <T> createRequest(body: T, contentType: String, format: EInvoiceXmlFormat): Result<ByteArray> {
        val queryParameters = buildMap<String, Any> {
            put("format", format.name)
        }
        val response = webClient.postAsync(RequestParameters("create/facturx/pdf", ByteArray::class, body, contentType, ContentTypes.OCTET_STREAM, queryParameters = queryParameters))

        return response.toResult()
    }

}
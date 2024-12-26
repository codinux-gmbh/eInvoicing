package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceXmlCreator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun createXRechnungXml(invoice: Invoice) = createInvoiceXml(invoice, EInvoiceXmlFormat.XRechnung)

    open suspend fun createZugferdXml(invoice: Invoice) = createFacturXXml(invoice)

    open suspend fun createFacturXXml(invoice: Invoice) = createInvoiceXml(invoice, EInvoiceXmlFormat.FacturX)

    open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat): String? {
        val response = webClient.postAsync(RequestParameters("create", String::class, invoice, ContentTypes.JSON, ContentTypes.XML, queryParameters = mapOf("format" to format.toString())))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
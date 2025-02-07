package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceXmlCreator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun createXRechnungXml(invoice: Invoice) = createInvoiceXml(invoice, EInvoiceFormat.XRechnung)

    open suspend fun createZugferdXml(invoice: Invoice) = createFacturXXml(invoice)

    open suspend fun createFacturXXml(invoice: Invoice) = createInvoiceXml(invoice, EInvoiceFormat.FacturX)

    open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceFormat): Result<String> =
        webClient.postAsync(RequestParameters("create", String::class, invoice, ContentTypes.JSON, ContentTypes.XML,
            queryParameters = mapOf("format" to format.toString())))
            .toResult()

}
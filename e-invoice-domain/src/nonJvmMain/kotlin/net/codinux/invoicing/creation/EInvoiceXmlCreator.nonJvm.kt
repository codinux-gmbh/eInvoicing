package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceXmlCreator(
    protected open val webServiceXmlCreator: WebServiceEInvoiceXmlCreator = WebServiceEInvoiceXmlCreator(),
    protected open val xmlCreator: EInvoiceXmlCreatorMP = EInvoiceXmlCreatorMP()
) {

    constructor(webClient: WebClient) : this(WebServiceEInvoiceXmlCreator(webClient))

    actual constructor() : this(DI.DefaultWebClient)


    actual open suspend fun createXRechnungXml(invoice: Invoice) =
        webServiceXmlCreator.createXRechnungXml(invoice)

    actual open suspend fun createZugferdXml(invoice: Invoice) =
        createFacturXXml(invoice)

    actual open suspend fun createFacturXXml(invoice: Invoice) =
        xmlCreator.createFacturXXml(invoice)

    actual open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceFormat): Result<String> =
        if (format == EInvoiceFormat.FacturX) createFacturXXml(invoice)
        else webServiceXmlCreator.createInvoiceXml(invoice, format)

}
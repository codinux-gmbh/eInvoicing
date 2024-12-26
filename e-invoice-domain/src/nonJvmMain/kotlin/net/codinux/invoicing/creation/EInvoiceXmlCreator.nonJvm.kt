package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceXmlCreator(protected open val xmlCreator: WebServiceEInvoiceXmlCreator = WebServiceEInvoiceXmlCreator()) {

    constructor(webClient: WebClient) : this(WebServiceEInvoiceXmlCreator(webClient))

    actual constructor() : this(DI.DefaultWebClient)


    actual open suspend fun createXRechnungXml(invoice: Invoice) =
        xmlCreator.createXRechnungXml(invoice)

    actual open suspend fun createZugferdXml(invoice: Invoice) =
        xmlCreator.createZugferdXml(invoice)

    actual open suspend fun createFacturXXml(invoice: Invoice) =
        xmlCreator.createFacturXXml(invoice)

    actual open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat) =
        xmlCreator.createInvoiceXml(invoice, format)

}
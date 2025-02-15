package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.InvoicePdfSettings
import net.codinux.invoicing.web.WebClient

actual open class EInvoicePdfCreator(protected open val pdfCreator: WebServiceEInvoicePdfCreator = WebServiceEInvoicePdfCreator()) {

    constructor(webClient: WebClient) : this(WebServiceEInvoicePdfCreator(webClient))

    actual constructor() : this(DI.DefaultWebClient)


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoice: Invoice, settings: InvoicePdfSettings) =
        pdfCreator.createFacturXPdf(invoice, settings)

    /**
     * Creates a hybrid PDF that also contains provided Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoiceXml: String, settings: InvoicePdfSettings) =
        pdfCreator.createFacturXPdf(invoiceXml, settings)

}
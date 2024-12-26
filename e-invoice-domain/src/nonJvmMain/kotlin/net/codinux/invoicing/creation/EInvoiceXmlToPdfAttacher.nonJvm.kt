package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceXmlToPdfAttacher(protected open val attacher: WebServiceEInvoiceXmlToPdfAttacher = WebServiceEInvoiceXmlToPdfAttacher()) {

    constructor(webClient: WebClient) : this(WebServiceEInvoiceXmlToPdfAttacher(webClient))

    actual constructor() : this(Constants.DefaultWebClient)


    actual open suspend fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat) =
        attacher.attachInvoiceXmlToPdf(invoice, pdfFile, format)

}
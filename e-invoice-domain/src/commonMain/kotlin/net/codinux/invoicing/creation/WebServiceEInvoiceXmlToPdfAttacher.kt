package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

class WebServiceEInvoiceXmlToPdfAttacher(
    protected open val webClient: WebClient = Constants.DefaultWebClient,
) {

    open suspend fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): ByteArray? {
        val body = AttachInvoiceToPdfRequest(invoice, pdfFile, format)

        val response = webClient.postAsync(RequestParameters("attach", ByteArray::class, body, ContentTypes.JSON, ContentTypes.PDF))

        if (response.successful) {
            return response.body
        }

        return null
    }

//    open suspend fun attachInvoiceXmlToPdf(invoiceXml: String, pdfFile: ByteArray, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): ByteArray? {
//        val body = AttachInvoiceXmlToPdfRequest(invoiceXml, pdfFile, format)
//
//        val response = webClient.postAsync(RequestParameters("attach/xml", ByteArray::class, body, ContentTypes.JSON, ContentTypes.PDF))
//
//        if (response.successful) {
//            return response.body
//        }
//
//        return null
//    }

}
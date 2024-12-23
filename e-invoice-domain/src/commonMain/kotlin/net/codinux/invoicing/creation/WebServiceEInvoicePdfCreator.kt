package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoicePdfCreator(
    protected open val webClient: WebClient = Constants.DefaultWebClient,
) {

    open suspend fun createFacturXPdf(invoice: Invoice): ByteArray? {
        val response = webClient.postAsync(RequestParameters("create/facturx/pdf", ByteArray::class, invoice, ContentTypes.JSON, ContentTypes.OCTET_STREAM))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
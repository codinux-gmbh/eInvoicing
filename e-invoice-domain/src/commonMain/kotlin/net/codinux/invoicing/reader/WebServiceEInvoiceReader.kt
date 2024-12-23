package net.codinux.invoicing.reader

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceReader(
    protected open val webClient: WebClient = Constants.DefaultWebClient
) {

    open suspend fun extractFromXml(xml: String): ReadEInvoiceXmlResult? {
        val response = webClient.postAsync(RequestParameters("extract", ReadEInvoiceXmlResult::class, xml, ContentTypes.XML, ContentTypes.JSON))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
package net.codinux.invoicing.reader

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceReader(
    protected open val webClient: WebClient = Constants.DefaultWebClient
) {

    open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResult? {
        val response = webClient.postAsync(RequestParameters("extract", ReadEInvoiceXmlResult::class, xml, ContentTypes.XML, ContentTypes.JSON, queryParameters = createQueryParameter(ignoreCalculationErrors)))

        if (response.successful) {
            return response.body
        }

        return null
    }

    open suspend fun extractFromPdf(pdfData: ByteArray, ignoreCalculationErrors: Boolean = false): PdfEInvoiceExtractionResult? {
        val response = webClient.postAsync(RequestParameters("extract", PdfEInvoiceExtractionResult::class, pdfData, ContentTypes.OCTET_STREAM, ContentTypes.JSON, queryParameters = createQueryParameter(ignoreCalculationErrors)))

        if (response.successful) {
            return response.body
        }

        return null
    }

    private fun createQueryParameter(ignoreCalculationErrors: Boolean): Map<String, Any> = buildMap {
        put("ignoreCalculationErrors", ignoreCalculationErrors)
    }

}
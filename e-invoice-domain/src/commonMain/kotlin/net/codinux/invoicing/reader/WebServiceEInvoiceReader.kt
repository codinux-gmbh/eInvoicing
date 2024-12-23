package net.codinux.invoicing.reader

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.dto.ExtractInvoiceDataFromPdfResponseDto
import net.codinux.invoicing.model.dto.ExtractInvoiceDataFromXmlDto
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceReader(
    protected open val webClient: WebClient = Constants.DefaultWebClient
) {

    open suspend fun extractFromXml(xml: String): ExtractInvoiceDataFromXmlDto? {
        val response = webClient.postAsync(RequestParameters("extract", ExtractInvoiceDataFromXmlDto::class, xml, ContentTypes.XML, ContentTypes.JSON))

        if (response.successful) {
            return response.body
        }

        return null
    }

    open suspend fun extractFromPdf(pdfData: ByteArray): ExtractInvoiceDataFromPdfResponseDto? {
        val response = webClient.postAsync(RequestParameters("extract", ExtractInvoiceDataFromPdfResponseDto::class, pdfData, ContentTypes.OCTET_STREAM, ContentTypes.JSON))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
package net.codinux.invoicing.reader

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceReader(
    protected open val webClient: WebClient = DI.DefaultWebClient
) {

    open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean = false): ReadEInvoiceXmlResult? {
        val response = webClient.postAsync(RequestParameters("extract", ReadEInvoiceXmlResult::class, xml, ContentTypes.XML, ContentTypes.JSON, queryParameters = createQueryParameter(ignoreCalculationErrors)))

        if (response.successful) {
            return response.body
        }

        return null
    }

    open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean = false): ReadEInvoicePdfResult? {
        val response = webClient.postAsync(RequestParameters("extract", ReadEInvoicePdfResult::class, pdfFile, ContentTypes.OCTET_STREAM, ContentTypes.JSON, queryParameters = createQueryParameter(ignoreCalculationErrors)))

        if (response.successful) {
            return response.body
        }

        return null
    }

    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult? {
        val response = webClient.postAsync(RequestParameters("extractXml", PdfAttachmentExtractionResult::class, pdfFile, ContentTypes.OCTET_STREAM, ContentTypes.JSON))

        if (response.successful) {
            return response.body
        }

        return null
    }


    private fun createQueryParameter(ignoreCalculationErrors: Boolean): Map<String, Any> = buildMap {
        put("ignoreCalculationErrors", ignoreCalculationErrors)
    }

}
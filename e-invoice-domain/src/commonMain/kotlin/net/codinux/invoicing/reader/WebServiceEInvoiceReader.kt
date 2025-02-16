package net.codinux.invoicing.reader

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceReader(
    protected open val webClient: WebClient = DI.DefaultWebClient
) {

    open suspend fun extractFromXml(xml: String): ReadEInvoiceXmlResult {
        val response = webClient.postAsync(RequestParameters("extract", ReadEInvoiceXmlResult::class, xml, ContentTypes.XML, ContentTypes.JSON))

        return response.body ?: ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.TechnicalError, response.error)
    }

    open suspend fun extractFromPdf(pdfFile: ByteArray): ReadEInvoicePdfResult {
        val response = webClient.postAsync(RequestParameters("extract", ReadEInvoicePdfResult::class, pdfFile, ContentTypes.PDF, ContentTypes.JSON))

        return response.body ?: run {
            val error = response.error?.let { SerializableException(it) }
            val extractionResult = PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.TechnicalError, emptyList())
            ReadEInvoicePdfResult(ReadEInvoicePdfResultType.TechnicalError, extractionResult, null, error)
        }
    }

    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult {
        val response = webClient.postAsync(RequestParameters("extractXml", PdfAttachmentExtractionResult::class, pdfFile, ContentTypes.PDF, ContentTypes.JSON))

        return response.body
            ?: PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.TechnicalError, emptyList(), response.error?.let { SerializableException(it) })
    }

}
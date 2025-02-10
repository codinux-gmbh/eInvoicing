package net.codinux.invoicing.validation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceValidator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun validateEInvoiceXml(xml: String): Result<InvoiceValidationResult> {
        val response = webClient.postAsync(RequestParameters("validate", InvoiceValidationResult::class, xml,
            ContentTypes.XML, ContentTypes.JSON, requestTimeoutMillis = 5 * 60_000))

        return response.toResult()
    }

    open suspend fun validateEInvoicePdf(pdfBytes: ByteArray): Result<InvoiceValidationResult> {
        val response = webClient.postAsync(RequestParameters("validate", InvoiceValidationResult::class, pdfBytes,
            ContentTypes.OCTET_STREAM, ContentTypes.JSON, requestTimeoutMillis = 5 * 60_000))

        return response.toResult()
    }

}
package net.codinux.invoicing.validation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceValidator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun validateEInvoiceXml(xml: String) =
        validateEInvoiceFile(xml.encodeToByteArray())

    open suspend fun validateEInvoiceFile(fileContent: ByteArray): Result<InvoiceValidationResult> {
        val response = webClient.postAsync(RequestParameters("validate", InvoiceValidationResult::class, fileContent,
            ContentTypes.OCTET_STREAM, ContentTypes.JSON))

        return response.toResult()
    }

}
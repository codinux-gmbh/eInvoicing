package net.codinux.invoicing.validation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceValidator(
    protected open val webClient: WebClient = Constants.DefaultWebClient,
) {

    open suspend fun validateEInvoiceXml(xml: String, disableNotices: Boolean = false) =
        validateEInvoiceFile(xml.encodeToByteArray(), disableNotices)

    open suspend fun validateEInvoiceFile(fileContent: ByteArray, disableNotices: Boolean = false): InvoiceValidationResult? {
        val response = webClient.postAsync(RequestParameters("validate", InvoiceValidationResult::class, fileContent, ContentTypes.OCTET_STREAM, ContentTypes.JSON))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
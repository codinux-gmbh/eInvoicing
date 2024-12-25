package net.codinux.invoicing.validation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceValidator(
    protected open val webClient: WebClient = Constants.DefaultWebClient,
) {

    open suspend fun validateEInvoiceXml(xml: String, disableNotices: Boolean = false, invoiceFilename: String? = null) =
        validateEInvoiceFile(xml.encodeToByteArray(), disableNotices, invoiceFilename)

    open suspend fun validateEInvoiceFile(fileContent: ByteArray, disableNotices: Boolean = false, invoiceFilename: String? = null): InvoiceValidationResult? {
        val queryParams = buildMap {
            if (disableNotices) {
                put("disableNotices", disableNotices)
            }
            if (invoiceFilename != null) {
                put("invoiceFilename", invoiceFilename)
            }
        }

        val response = webClient.postAsync(RequestParameters("validate", InvoiceValidationResult::class, fileContent, ContentTypes.OCTET_STREAM, ContentTypes.JSON, queryParameters = queryParams))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
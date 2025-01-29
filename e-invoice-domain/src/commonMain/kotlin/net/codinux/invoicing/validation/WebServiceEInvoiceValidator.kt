package net.codinux.invoicing.validation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceEInvoiceValidator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun validateEInvoiceXml(xml: String, disableNotices: Boolean = false, invoiceFilename: String? = null) =
        validateEInvoiceFile(xml.encodeToByteArray(), disableNotices, invoiceFilename)

    open suspend fun validateEInvoiceFile(fileContent: ByteArray, disableNotices: Boolean = false, invoiceFilename: String? = null): Result<InvoiceValidationResult> {
        val queryParams = buildMap {
            if (disableNotices) {
                put("disableNotices", disableNotices)
            }
            if (invoiceFilename != null) {
                put("invoiceFilename", invoiceFilename)
            }
        }

        val response = webClient.postAsync(RequestParameters("validate", InvoiceValidationResult::class, fileContent,
            ContentTypes.OCTET_STREAM, ContentTypes.JSON, queryParameters = queryParams))

        return response.toResult()
    }

}
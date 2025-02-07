package net.codinux.invoicing.validation

import net.codinux.invoicing.web.WebClient

actual open class EInvoiceValidator(
    protected open val validator: WebServiceEInvoiceValidator
) {

    constructor(webClient: WebClient) : this(WebServiceEInvoiceValidator(webClient))


    actual open suspend fun validateEInvoiceXml(xml: String) =
        validator.validateEInvoiceXml(xml)

    actual open suspend fun validateEInvoiceFile(fileContent: ByteArray) =
        validator.validateEInvoiceFile(fileContent)

}
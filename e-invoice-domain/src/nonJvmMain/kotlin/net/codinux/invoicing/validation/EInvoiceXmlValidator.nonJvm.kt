package net.codinux.invoicing.validation

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceXmlValidator(
    protected open val validator: WebServiceEInvoiceValidator
) {

    actual constructor() : this(DI.DefaultWebClient)

    constructor(webClient: WebClient) : this(WebServiceEInvoiceValidator(webClient))


    actual open suspend fun validateEInvoiceXml(xml: String) =
        validator.validateEInvoiceXml(xml)

}
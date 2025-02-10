package net.codinux.invoicing.validation

import net.codinux.invoicing.web.WebClient

actual open class EInvoiceXmlValidator(
    protected open val validator: WebServiceEInvoiceValidator
) {

    constructor(webClient: WebClient) : this(WebServiceEInvoiceValidator(webClient))


    actual open suspend fun validateEInvoiceXml(xml: String) =
        validator.validateEInvoiceXml(xml)

    actual open suspend fun validateEInvoicePdf(pdfBytes: ByteArray) =
        validator.validateEInvoicePdf(pdfBytes)

}
package net.codinux.invoicing.validation

import net.codinux.invoicing.web.WebClient

actual open class EInvoicePdfValidator(
    protected open val validator: WebServiceEInvoiceValidator
) {

    constructor(webClient: WebClient) : this(WebServiceEInvoiceValidator(webClient))


    actual open suspend fun validateEInvoicePdf(pdfBytes: ByteArray) =
        validator.validateEInvoicePdf(pdfBytes)

}
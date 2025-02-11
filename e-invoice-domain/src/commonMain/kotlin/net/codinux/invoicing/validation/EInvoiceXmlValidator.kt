package net.codinux.invoicing.validation

import net.codinux.invoicing.model.Result

expect open class EInvoiceXmlValidator constructor() {

    open suspend fun validateEInvoiceXml(xml: String): Result<InvoiceXmlValidationResult>

}
package net.codinux.invoicing.validation

import net.codinux.invoicing.model.Result

expect open class EInvoiceValidator {

    open suspend fun validateEInvoiceXml(xml: String): Result<InvoiceValidationResult>

    open suspend fun validateEInvoicePdf(pdfBytes: ByteArray): Result<InvoiceValidationResult>

}
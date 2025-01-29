package net.codinux.invoicing.validation

import net.codinux.invoicing.model.Result

expect open class EInvoiceValidator {

    open suspend fun validateEInvoiceXml(xml: String, disableNotices: Boolean = false, invoiceFilename: String? = null): Result<InvoiceValidationResult>

    open suspend fun validateEInvoiceFile(fileContent: ByteArray, disableNotices: Boolean = false, invoiceFilename: String? = null): Result<InvoiceValidationResult>

}
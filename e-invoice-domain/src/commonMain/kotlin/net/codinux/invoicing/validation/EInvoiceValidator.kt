package net.codinux.invoicing.validation

expect open class EInvoiceValidator {

    open suspend fun validateEInvoiceXml(xml: String, disableNotices: Boolean = false, invoiceFilename: String? = null): InvoiceValidationResult?

    open suspend fun validateEInvoiceFile(fileContent: ByteArray, disableNotices: Boolean = false, invoiceFilename: String? = null): InvoiceValidationResult?

}
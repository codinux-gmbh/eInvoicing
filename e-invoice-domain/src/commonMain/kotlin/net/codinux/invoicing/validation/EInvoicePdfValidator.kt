package net.codinux.invoicing.validation

import net.codinux.invoicing.model.Result

expect open class EInvoicePdfValidator {

    open suspend fun validateEInvoicePdf(pdfBytes: ByteArray): Result<PdfValidationResult>

}
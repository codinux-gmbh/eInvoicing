package net.codinux.invoicing.pdf

class PdfInvoiceDataExtractionResult(
    val error: Throwable?,
    val data: PdfInvoiceData?
) {
    override fun toString() =
        if (data != null) "Success: $data"
        else "Error: $error"
}
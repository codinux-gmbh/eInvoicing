package net.codinux.invoicing.pdf

data class PdfTextExtractorResult(
    val text: String?,
    val error: Throwable?
) {
    override fun toString() =
        if (text != null) "Success: $text"
        else "Error: $error"
}
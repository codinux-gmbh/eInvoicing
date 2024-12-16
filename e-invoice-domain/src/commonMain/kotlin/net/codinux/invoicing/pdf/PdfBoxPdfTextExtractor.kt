package net.codinux.invoicing.pdf

import net.dankito.text.extraction.ITextExtractor
import net.dankito.text.extraction.pdf.PdfBoxPdfTextExtractor
import java.io.File

open class PdfBoxPdfTextExtractor(
    protected open val textExtractor: ITextExtractor = PdfBoxPdfTextExtractor()
) : PdfTextExtractor {

    override fun extractTextFromPdf(pdfFile: File): PdfTextExtractorResult {
        val result = textExtractor.extractText(pdfFile)

        return PdfTextExtractorResult(result.text, result.error?.exception)
    }

}
package net.codinux.invoicing.pdf

import java.io.File

interface PdfTextExtractor {

    fun extractTextFromPdf(pdfFile: File): PdfTextExtractorResult

}
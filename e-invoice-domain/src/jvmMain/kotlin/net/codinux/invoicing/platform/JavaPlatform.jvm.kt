package net.codinux.invoicing.platform

import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.PdfBoxPdfAttachmentReader
import net.codinux.invoicing.pdf.PdfBoxPdfTextExtractor
import net.codinux.invoicing.pdf.PdfTextExtractor

actual object JavaPlatform {

    actual val pdfTextExtractor: PdfTextExtractor = PdfBoxPdfTextExtractor()

    actual val pdfAttachmentReader: PdfAttachmentReader = PdfBoxPdfAttachmentReader()

}
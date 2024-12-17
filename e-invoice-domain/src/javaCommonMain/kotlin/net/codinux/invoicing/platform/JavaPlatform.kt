package net.codinux.invoicing.platform

import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.PdfTextExtractor

expect object JavaPlatform {

    val pdfTextExtractor: PdfTextExtractor

    val pdfAttachmentReader: PdfAttachmentReader

}
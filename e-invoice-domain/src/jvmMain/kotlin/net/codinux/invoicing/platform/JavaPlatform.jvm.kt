package net.codinux.invoicing.platform

import net.codinux.invoicing.pdf.*

actual object JavaPlatform {

    actual val pdfTextExtractor: PdfTextExtractor = PdfBoxPdfTextExtractor()

    actual val pdfAttachmentReader: PdfAttachmentReader = PdfBoxPdfAttachmentReader()

    actual val pdfAttachmentWriter: PdfAttachmentWriter = PdfBoxPdfAttachmentWriter()

}
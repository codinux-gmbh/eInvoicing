package net.codinux.invoicing.platform

import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.PdfBoxAndroidPdfAttachmentReader
import net.codinux.invoicing.pdf.PdfBoxAndroidPdfTextExtractor
import net.codinux.invoicing.pdf.PdfTextExtractor

actual object JavaPlatform {

    actual val pdfTextExtractor: PdfTextExtractor = PdfBoxAndroidPdfTextExtractor(AndroidContext.applicationContext)

    actual val pdfAttachmentReader: PdfAttachmentReader = PdfBoxAndroidPdfAttachmentReader(AndroidContext.applicationContext)

}
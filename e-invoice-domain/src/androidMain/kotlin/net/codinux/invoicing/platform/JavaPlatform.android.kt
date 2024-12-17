package net.codinux.invoicing.platform

import net.codinux.invoicing.pdf.*

actual object JavaPlatform {

    actual val pdfTextExtractor: PdfTextExtractor = PdfBoxAndroidPdfTextExtractor(AndroidContext.applicationContext)

    actual val pdfAttachmentReader: PdfAttachmentReader = PdfBoxAndroidPdfAttachmentReader(AndroidContext.applicationContext)

    actual val pdfAttachmentWriter: PdfAttachmentWriter = PdfBoxAndroidPdfAttachmentWriter()

}
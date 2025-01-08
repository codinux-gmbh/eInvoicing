package net.codinux.invoicing.platform

import net.codinux.invoicing.creation.EInvoicePdfCreator
import net.codinux.invoicing.pdf.*
import net.codinux.kotlin.android.AndroidContext

actual object JavaPlatform {

    actual val pdfTextExtractor: PdfTextExtractor = PdfBoxAndroidPdfTextExtractor(AndroidContext.applicationContext)

    actual val pdfAttachmentReader: PdfAttachmentReader = PdfBoxAndroidPdfAttachmentReader(AndroidContext.applicationContext)

    actual val pdfAttachmentWriter: PdfAttachmentWriter = PdfBoxAndroidPdfAttachmentWriter()

    actual val invoicePdfCreator: EInvoicePdfCreator? = null

}
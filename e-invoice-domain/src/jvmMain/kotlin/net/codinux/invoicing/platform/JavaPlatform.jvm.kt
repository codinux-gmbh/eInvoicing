package net.codinux.invoicing.platform

import net.codinux.invoicing.creation.EInvoicePdfCreator
import net.codinux.invoicing.creation.JvmEInvoicePdfCreator
import net.codinux.invoicing.pdf.*

actual object JavaPlatform {

    actual val pdfTextExtractor: PdfTextExtractor = PdfBoxPdfTextExtractor()

    actual val pdfAttachmentReader: PdfAttachmentReader = PdfBoxPdfAttachmentReader()

    actual val pdfAttachmentWriter: PdfAttachmentWriter = PdfBoxPdfAttachmentWriter()

    actual val invoicePdfCreator: EInvoicePdfCreator? = JvmEInvoicePdfCreator() // define as last so that pdfAttachmentWriter, ... are created

}
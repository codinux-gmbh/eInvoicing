package net.codinux.invoicing.reader

import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.log.logger

open class LocalOrWebServiceEInvoiceReader(
    protected val webServiceEInvoiceReader: WebServiceEInvoiceReader,
    protected val attachmentReader: PdfAttachmentReader
) {

    private val log by logger()


    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult = try {
        attachmentReader.getFileAttachments(pdfFile)
    } catch (e: Throwable) {
        log.error(e) { "Could not read PDF file locally, using webservice" }
        webServiceEInvoiceReader.extractXmlFromPdf(pdfFile)
    }

}
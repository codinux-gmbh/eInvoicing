package net.codinux.invoicing.reader

import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.log.logger

open class LocalOrWebServiceEInvoiceReader(
    protected val xmlReader: EInvoiceXmlReader,
    protected val attachmentReader: PdfAttachmentReader,
    protected val webServiceEInvoiceReader: WebServiceEInvoiceReader,
) {

    private val log by logger()


    open suspend fun extractXmlFromPdf(pdfFile: ByteArray): PdfAttachmentExtractionResult = try {
        attachmentReader.getFileAttachments(pdfFile)
    } catch (e: Throwable) {
        log.error(e) { "Could not read PDF file locally, using webservice" }
        webServiceEInvoiceReader.extractXmlFromPdf(pdfFile)
    }

    open fun extractXmlFromPdfLocally(pdfFile: ByteArray): PdfAttachmentExtractionResult =
        attachmentReader.getFileAttachments(pdfFile)


    open suspend fun extractFromPdf(pdfFile: ByteArray): ReadEInvoicePdfResult = try {
        extractFromPdfLocally(pdfFile)
    } catch (e: Throwable) {
        log.error(e) { "Could not read PDF file locally, using webservice" }
        webServiceEInvoiceReader.extractFromPdf(pdfFile)
    }

    protected open fun extractFromPdfLocally(pdfFile: ByteArray): ReadEInvoicePdfResult {
        val attachmentsResult = extractXmlFromPdfLocally(pdfFile)
        val invoiceXml = attachmentsResult.invoiceXml
        if (attachmentsResult.type != PdfAttachmentExtractionResultType.HasXmlAttachments || invoiceXml.isNullOrBlank()) {
            return ReadEInvoicePdfResult(ReadEInvoicePdfResultType.from(attachmentsResult.type), attachmentsResult)
        }

        val readXmlResult = xmlReader.parseInvoiceXml(invoiceXml)
        return ReadEInvoicePdfResult(attachmentsResult, readXmlResult)
    }

}
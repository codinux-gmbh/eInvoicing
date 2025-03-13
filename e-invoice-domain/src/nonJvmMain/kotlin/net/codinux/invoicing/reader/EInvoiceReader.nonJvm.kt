package net.codinux.invoicing.reader

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.pdf.Pdf4kPdfAttachmentReader
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceReader(
    protected open val webServiceReader: WebServiceEInvoiceReader,
    protected open val xmlReader: EInvoiceXmlReader = EInvoiceXmlReader(),
    protected open val attachmentReader: PdfAttachmentReader = Pdf4kPdfAttachmentReader(),
    protected open val localEInvoiceReader: LocalOrWebServiceEInvoiceReader = LocalOrWebServiceEInvoiceReader(webServiceReader, attachmentReader),
) {

    actual constructor() : this(DI.DefaultWebClient)

    constructor(webClient: WebClient) : this(WebServiceEInvoiceReader(webClient))


    actual open suspend fun extractFromXml(xml: String) =
        xmlReader.parseInvoiceXml(xml)

    actual open suspend fun extractFromPdf(pdfFile: ByteArray) =
        webServiceReader.extractFromPdf(pdfFile)

    actual open suspend fun extractXmlFromPdf(pdfFile: ByteArray) =
        localEInvoiceReader.extractXmlFromPdf(pdfFile)

}
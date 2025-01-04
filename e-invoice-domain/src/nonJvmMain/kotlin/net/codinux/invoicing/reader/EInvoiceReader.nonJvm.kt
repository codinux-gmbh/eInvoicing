package net.codinux.invoicing.reader

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceReader(
    protected open val reader: WebServiceEInvoiceReader
) {

    actual constructor() : this(DI.DefaultWebClient)

    constructor(webClient: WebClient) : this(WebServiceEInvoiceReader(webClient))


    actual open suspend fun extractFromXml(xml: String) =
        reader.extractFromXml(xml)

    actual open suspend fun extractFromPdf(pdfFile: ByteArray) =
        reader.extractFromPdf(pdfFile)

    actual open suspend fun extractXmlFromPdf(pdfFile: ByteArray) =
        reader.extractXmlFromPdf(pdfFile)

}
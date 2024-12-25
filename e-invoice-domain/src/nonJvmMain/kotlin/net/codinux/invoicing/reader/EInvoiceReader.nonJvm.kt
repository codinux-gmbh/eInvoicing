package net.codinux.invoicing.reader

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.web.WebClient

actual open class EInvoiceReader(
    protected open val reader: WebServiceEInvoiceReader
) {

    actual constructor() : this(Constants.DefaultWebClient)

    constructor(webClient: WebClient) : this(WebServiceEInvoiceReader(webClient))


    actual open suspend fun extractFromXml(xml: String, ignoreCalculationErrors: Boolean) =
        reader.extractFromXml(xml)

    actual open suspend fun extractFromPdf(pdfFile: ByteArray, ignoreCalculationErrors: Boolean) =
        reader.extractFromPdf(pdfFile, ignoreCalculationErrors)

    actual open suspend fun extractXmlFromPdf(pdfFile: ByteArray) =
        reader.extractXmlFromPdf(pdfFile)

}
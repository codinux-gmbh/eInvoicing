package net.codinux.invoicing.reader

import kotlinx.serialization.decodeFromString
import net.codinux.invoicing.format.EInvoiceFormatDetector
import net.codinux.invoicing.format.EInvoicingStandard
import net.codinux.invoicing.model.cii.lenient.CrossIndustryInvoice
import net.codinux.invoicing.model.mapper.CiiMapper
import net.codinux.invoicing.model.mapper.UblMapper
import net.codinux.invoicing.model.ubl.UblInvoice
import net.codinux.kotlin.extensions.ofMaxLength
import net.codinux.log.logger
import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.serialization.UnknownChildHandler
import nl.adaptivity.xmlutil.serialization.XML

open class EInvoiceXmlReader(
    protected val formatDetector: EInvoiceFormatDetector = EInvoiceFormatDetector(),
    protected val ciiMapper: CiiMapper = CiiMapper(),
    protected val ublMapper: UblMapper = UblMapper(),
) {

    @OptIn(ExperimentalXmlUtilApi::class)
    protected val xml = XML {
        this.defaultPolicy {
            unknownChildHandler = UnknownChildHandler { input, inputKind, descriptor, name, candidates ->
                    log.warn { "Unknown child encountered: $inputKind $name in ${descriptor}" }
                    emptyList()
                }
        }
    }

    private val log by logger()


    open fun parseInvoiceXml(invoiceXml: String): ReadEInvoiceXmlResult =
        try {
            val fixedXml = fixXmlForReading(invoiceXml) // a simple non-breaking space before first '<' makes XmlReader crash
            val format = formatDetector.detectFormat(fixedXml)

            if (format?.standard == EInvoicingStandard.CII) {
                val ciiInvoice = xml.decodeFromString<CrossIndustryInvoice>(fixedXml)
                ciiMapper.map(ciiInvoice, format)
            } else if (format?.standard == EInvoicingStandard.UBL) {
                val ublInvoice = xml.decodeFromString<UblInvoice>(fixedXml)
                ublMapper.map(ublInvoice, format)
            } else {
                ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.UnsupportedInvoiceFormat, null as? Throwable)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not extract invoice from XML:: ${invoiceXml.ofMaxLength(250)}" }
            ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, e)
        }

}
package net.codinux.invoicing.creation

import kotlinx.serialization.encodeToString
import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.model.mapper.DomainToCiiMapper
import net.codinux.log.logger
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

open class EInvoiceXmlCreatorMP(
    protected val mapper: DomainToCiiMapper = DomainToCiiMapper(),
    protected val calculator: AmountsCalculator = AmountsCalculator(),
) {

    protected val xml = XML {
        xmlDeclMode = XmlDeclMode.Charset // without "<?xml version='1.0' encoding='UTF-8' ?>" wouldn't get written
        // with the default set to false namespace declarations would be written at elements in body where they first
        // occur instead of in root element
        isCollectingNSAttributes = true
        indentString = "  " // pretty print
    }

    private val log by logger()


    open fun createFacturXXml(invoice: Invoice): Result<String> =
        try {
            calculator.ensureTotalAmountsIsSet(invoice)

            val facturXInvoice = mapper.mapInvoice(invoice)

            Result.success(xml.encodeToString(facturXInvoice))
        } catch (e: Throwable) {
            log.error(e) { "Could not create Factur-X invoice" }
            Result.error(e)
        }

}
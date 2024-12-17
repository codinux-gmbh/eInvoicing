package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*

open class EInvoiceXmlCreator(
    protected open val mapper: MustangMapper = MustangMapper()
) {

    open fun createXRechnungXml(invoice: Invoice) = createXml(invoice, EInvoiceXmlFormat.XRechnung)

    /**
     * Synonym for [createFacturXXml] (ZUGFeRD 2 is a synonym for Factur-X).
     */
    open fun createZugferdXml(invoice: Invoice) = createFacturXXml(invoice)

    open fun createFacturXXml(invoice: Invoice) = createXml(invoice, EInvoiceXmlFormat.FacturX)

    open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): String {
        val exporter = ZUGFeRDExporterFromA3()
            .setProfile(getProfileNameForFormat(format))

        return createXml(exporter.provider, invoice)
    }

    protected open fun createXml(provider: IXMLProvider, invoice: Invoice): String {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return String(provider.xml, Charsets.UTF_8)
    }


    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        Constants.getProfileNameForFormat(format)

}
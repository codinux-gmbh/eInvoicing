package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*

actual open class EInvoiceXmlCreator(
    protected open val mapper: MustangMapper = MustangMapper()
) {

    actual constructor() : this(MustangMapper())


    actual open suspend fun createXRechnungXml(invoice: Invoice): String? =
        createXRechnungXmlJvm(invoice)

    actual open suspend fun createZugferdXml(invoice: Invoice): String? =
        createZugferdXmlJvm(invoice)

    actual open suspend fun createFacturXXml(invoice: Invoice): String? =
        createFacturXXmlJvm(invoice)

    actual open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat): String? =
        createInvoiceXmlJvm(invoice, format)


    // TODO: find a better name
    open fun createXRechnungXmlJvm(invoice: Invoice) = createInvoiceXmlJvm(invoice, EInvoiceXmlFormat.XRechnung)

    /**
     * Synonym for [createFacturXXmlJvm] (ZUGFeRD 2 is a synonym for Factur-X).
     */
    // TODO: find a better name
    open fun createZugferdXmlJvm(invoice: Invoice) = createFacturXXmlJvm(invoice)

    // TODO: find a better name
    open fun createFacturXXmlJvm(invoice: Invoice) = createInvoiceXmlJvm(invoice, EInvoiceXmlFormat.FacturX)

    // TODO: find a better name
    open fun createInvoiceXmlJvm(invoice: Invoice, format: EInvoiceXmlFormat): String {
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
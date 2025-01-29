package net.codinux.invoicing.creation

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.log.logger
import org.mustangproject.ZUGFeRD.*

actual open class EInvoiceXmlCreator(
    protected open val mapper: MustangMapper = MustangMapper(),
    protected open val xmlCreator: EInvoiceXmlCreatorMP = EInvoiceXmlCreatorMP()
) {

    actual constructor() : this(MustangMapper())


    private val log by logger()


    actual open suspend fun createXRechnungXml(invoice: Invoice) =
        createXRechnungXmlJvm(invoice)

    actual open suspend fun createZugferdXml(invoice: Invoice) =
        createZugferdXmlJvm(invoice)

    actual open suspend fun createFacturXXml(invoice: Invoice) =
        createFacturXXmlJvm(invoice)

    actual open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat) =
        createInvoiceXmlJvm(invoice, format)


    // TODO: find a better name
    open fun createXRechnungXmlJvm(invoice: Invoice) = createInvoiceXmlJvm(invoice, EInvoiceXmlFormat.XRechnung)

    /**
     * Synonym for [createFacturXXmlJvm] (ZUGFeRD 2 is a synonym for Factur-X).
     */
    // TODO: find a better name
    open fun createZugferdXmlJvm(invoice: Invoice) = createFacturXXmlJvm(invoice)

    // TODO: find a better name
    open fun createFacturXXmlJvm(invoice: Invoice) = xmlCreator.createFacturXXml(invoice)

    // TODO: find a better name
    open fun createInvoiceXmlJvm(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        if (format == EInvoiceXmlFormat.FacturX) xmlCreator.createFacturXXml(invoice)
        else {
            try {
                val exporter = ZUGFeRDExporterFromA3()
                    .setProfile(getProfileNameForFormat(format))

                createXml(exporter.provider, invoice)
            } catch (e: Throwable) {
                log.error(e) { "Could not create Invoice XML" }
                Result.error(e)
            }
        }

    protected open fun createXml(provider: IXMLProvider, invoice: Invoice): Result<String> {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return Result.success(String(provider.xml, Charsets.UTF_8))
    }


    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        Constants.getProfileNameForFormat(format)

}
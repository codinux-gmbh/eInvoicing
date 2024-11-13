package net.codinux.invoicing.creation

import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.IXMLProvider
import org.mustangproject.ZUGFeRD.Profiles
import org.mustangproject.ZUGFeRD.ZUGFeRD2PullProvider
import org.mustangproject.ZUGFeRD.ZUGFeRDExporterFromA3

class EInvoiceCreator(
    private val mapper: MustangMapper = MustangMapper()
) {

    fun createXRechnungXml(invoice: Invoice): String {
        val provider = ZUGFeRD2PullProvider()
        provider.profile = Profiles.getByName("XRechnung")

        return createXml(provider, invoice)
    }

    fun createZugferdXml(invoice: Invoice, zugferdVersion: Int = 2): String {
        val exporter = ZUGFeRDExporterFromA3()
            .setZUGFeRDVersion(zugferdVersion)
            .setProfile("EN16931")
            .setProducer("danki die geile Sau")
            .setCreator(System.getProperty("user.name"))

        return createXml(exporter.provider, invoice)
    }


    private fun createXml(provider: IXMLProvider, invoice: Invoice): String {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return String(provider.xml, Charsets.UTF_8)
    }

}
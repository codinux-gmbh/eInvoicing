package net.codinux.invoicing.creation

import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.Profiles
import org.mustangproject.ZUGFeRD.ZUGFeRD2PullProvider

class EInvoiceCreator(
    private val mapper: MustangMapper = MustangMapper()
) {

    fun createXRechnungXml(invoice: Invoice): String {
        val provider = ZUGFeRD2PullProvider()
        provider.profile = Profiles.getByName("XRechnung")
        provider.generateXML(mapper.mapToTransaction(invoice))

        return String(provider.xml, Charsets.UTF_8)
    }

}
package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice

expect open class EInvoiceXmlCreator constructor() {

    open suspend fun createXRechnungXml(invoice: Invoice): String?

    open suspend fun createZugferdXml(invoice: Invoice): String?

    open suspend fun createFacturXXml(invoice: Invoice): String?

    open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat): String?

}
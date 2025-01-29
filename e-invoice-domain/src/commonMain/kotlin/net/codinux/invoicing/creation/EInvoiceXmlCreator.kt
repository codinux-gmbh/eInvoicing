package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result

expect open class EInvoiceXmlCreator constructor() {

    open suspend fun createXRechnungXml(invoice: Invoice): Result<String>

    open suspend fun createZugferdXml(invoice: Invoice): Result<String>

    open suspend fun createFacturXXml(invoice: Invoice): Result<String>

    open suspend fun createInvoiceXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String>

}
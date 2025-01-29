package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result

expect open class EInvoicePdfCreator constructor() {

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open suspend fun createFacturXPdf(invoice: Invoice, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Result<ByteArray>

    /**
     * Creates a hybrid PDF that also contains provided Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open suspend fun createFacturXPdf(invoiceXml: String, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Result<ByteArray>

}
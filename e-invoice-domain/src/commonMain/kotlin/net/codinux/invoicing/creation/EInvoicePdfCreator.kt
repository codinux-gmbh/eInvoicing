package net.codinux.invoicing.creation

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.InvoicePdfConfig

expect open class EInvoicePdfCreator constructor() {

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open suspend fun createFacturXPdf(invoice: Invoice, config: InvoicePdfConfig = InvoicePdfConfig()): Result<ByteArray>

    /**
     * Creates a hybrid PDF that also contains provided Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open suspend fun createFacturXPdf(invoiceXml: String, config: InvoicePdfConfig = InvoicePdfConfig()): Result<ByteArray>

}
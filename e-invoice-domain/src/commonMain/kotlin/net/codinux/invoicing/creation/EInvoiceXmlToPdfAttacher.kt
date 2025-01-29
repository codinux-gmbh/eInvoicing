package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result

expect open class EInvoiceXmlToPdfAttacher constructor() {

    open suspend fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: ByteArray, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Result<ByteArray>

}
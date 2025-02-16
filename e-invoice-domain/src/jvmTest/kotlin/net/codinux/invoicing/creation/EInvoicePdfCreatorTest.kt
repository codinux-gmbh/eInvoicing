package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.InvoicePdfSettings
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceXmlAsserter
import kotlin.test.Test

class EInvoicePdfCreatorTest {

    private val pdfAttachmentReader = JavaPlatform.pdfAttachmentReader

    private val underTest = EInvoicePdfCreator()


    @Test
    fun createFacturXPdf_FacturX() {
        val invoice = createInvoice()

        val result = underTest.createFacturXPdfJvm(invoice, InvoicePdfSettings(EInvoiceXmlFormat.FacturX))

        assertInvoiceXml(result)
    }

    @Test
    fun createFacturXPdf_XRechnung() {
        val invoice = createInvoice()

        val result = underTest.createFacturXPdfJvm(invoice, InvoicePdfSettings(EInvoiceXmlFormat.XRechnung))

        assertInvoiceXml(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(createResult: Result<Pdf>) {
        val pdfBytes = Asserts.assertSuccess(createResult).bytes

        val xml = pdfAttachmentReader.getFileAttachments(pdfBytes).invoiceXml

        InvoiceXmlAsserter.assertInvoiceXml(xml)
    }

}
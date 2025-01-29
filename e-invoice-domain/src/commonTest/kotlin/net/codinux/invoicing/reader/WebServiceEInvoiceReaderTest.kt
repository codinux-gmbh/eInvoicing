package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoiceReaderTest {

    private val underTest = WebServiceEInvoiceReader()


    @Test
    fun extractFromFacturXXml() = runTest {
        val result = underTest.extractFromXml(TestData.FacturXXml)

        assertInvoice(result)
    }

    @Test
    fun extractFromXRechnungXml() = runTest {
        val result = underTest.extractFromXml(TestData.XRechnungXml)

        assertInvoice(result)
    }


    @Test
    fun extractFromPdf() = runTest {
        val result = underTest.extractFromPdf(TestData.FacturXPdf)

        assertThat(result.type).isEqualByComparingTo(ReadEInvoicePdfResultType.Success)

        assertInvoice(result.invoice)
    }


    @Test
    fun extractXmlFromPdf() = runTest {
        val result = underTest.extractXmlFromPdf(TestData.FacturXPdf)

        assertThat(result.type).isEqualByComparingTo(PdfAttachmentExtractionResultType.HasXmlAttachments)

        assertThat(result.attachments).hasSize(1)

        val attachment = result.attachments.first()
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.isProbablyEN16931InvoiceXml).isTrue()
        assertThat(attachment.xml).isEqualTo(result.invoiceXml)
        assertThat(attachment.filename).isEqualTo("factur-x.xml")

        assertThat(result.invoiceXml).isNotNull()
        assertThat(result.invoiceXml!!).hasLength(6567)
    }


    private fun assertInvoice(result: ReadEInvoiceXmlResult?) {
        assertThat(result).isNotNull()
        assertThat(result!!.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)

        assertInvoice(result.invoice)
    }

    private fun assertInvoice(invoice: MapInvoiceResult?) {
        InvoiceAsserter.assertInvoice(invoice)
    }

}
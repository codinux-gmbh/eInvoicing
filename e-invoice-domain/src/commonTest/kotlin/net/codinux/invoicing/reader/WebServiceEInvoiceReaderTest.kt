package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.Invoice
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

        assertThat(result).isNotNull()
        assertThat(result!!.type).isEqualByComparingTo(PdfExtractionResultType.Success)

        assertInvoice(result.invoice)
    }


    private fun assertInvoice(result: ReadEInvoiceXmlResult?) {
        assertThat(result).isNotNull()
        assertThat(result!!.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)

        assertInvoice(result.invoice)
    }

    private fun assertInvoice(invoice: Invoice?) {
        assertThat(invoice).isNotNull()

        InvoiceAsserter.assertInvoice(invoice)
    }

}
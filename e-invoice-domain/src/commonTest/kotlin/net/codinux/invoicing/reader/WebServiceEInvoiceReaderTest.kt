package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
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

    private fun assertInvoice(result: ReadEInvoiceXmlResult?) {
        assertThat(result).isNotNull()
        assertThat(result!!.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)
        assertThat(result.invoice).isNotNull()

        InvoiceAsserter.assertInvoice(result.invoice)
    }

}
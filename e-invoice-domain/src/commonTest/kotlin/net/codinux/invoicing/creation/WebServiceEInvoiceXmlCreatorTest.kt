package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isGreaterThan
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.DataGenerator
import kotlin.test.Test

// TODO: these tests are bad, currently they require that the e-invoice-api is running - they don't start and neither stop it
class WebServiceEInvoiceXmlCreatorTest {

    private val underTest = WebServiceEInvoiceXmlCreator()


    @Test
    fun createXRechnungXml() = runTest {
        val invoice = createInvoice()

        val result = underTest.createXRechnungXml(invoice)

        assertInvoiceXml(result)
    }

    @Test
    fun createFacturXXml() = runTest {
        val invoice = createInvoice()

        val result = underTest.createFacturXXml(invoice)

        assertInvoiceXml(result)
    }

    @Test
    fun createInvoiceXml() = runTest {
        val invoice = createInvoice()

        val result = underTest.createInvoiceXml(invoice, EInvoiceXmlFormat.FacturX)

        assertInvoiceXml(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(result: Result<String>) {
        //InvoiceXmlAsserter.assertInvoiceXml(xml)

        val xml = Asserts.assertSuccess(result)
        assertThat(xml.length).isGreaterThan(5800)
    }

}
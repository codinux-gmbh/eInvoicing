package net.codinux.invoicing.creation

import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceXmlAsserter
import kotlin.test.Test

class EInvoiceXmlCreatorTest {

    private val underTest = EInvoiceXmlCreator()


    @Test
    fun createXRechnung() {
        val invoice = createInvoice()

        val result = underTest.createXRechnungXml(invoice)

        assertInvoiceXml(result)
    }

    @Test
    fun createFacturXXml() {
        val invoice = createInvoice()

        val result = underTest.createFacturXXml(invoice)

        assertInvoiceXml(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(xml: String) {
        InvoiceXmlAsserter.assertInvoiceXml(xml)
    }

}
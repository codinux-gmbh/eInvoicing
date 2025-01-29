package net.codinux.invoicing.creation

import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceXmlAsserter
import kotlin.test.Test

class EInvoiceXmlCreatorTest {

    private val underTest = EInvoiceXmlCreator()


    @Test
    fun createXRechnung() {
        val invoice = createInvoice()

        val result = underTest.createXRechnungXmlJvm(invoice)

        assertInvoiceXml(result)
    }

    @Test
    fun createFacturXXml() {
        val invoice = createInvoice()

        val result = underTest.createFacturXXmlJvm(invoice)

        assertInvoiceXml(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(result: Result<String>) {
        InvoiceXmlAsserter.assertInvoiceXml(result)
    }

}
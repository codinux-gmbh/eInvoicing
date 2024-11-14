package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isNotEmpty
import net.codinux.invoicing.test.DataGenerator
import kotlin.test.Test

class EInvoiceCreatorTest {

    private val underTest = EInvoiceCreator()


    @Test
    fun createXRechnung() {
        val invoice = createInvoice()

        val result = underTest.createXRechnungXml(invoice)

        assertInvoiceXml(result)
    }

    @Test
    fun createZugferdXml() {
        val invoice = createInvoice()

        val result = underTest.createZugferdXml(invoice)

        assertInvoiceXml(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(xml: String) {
        assertThat(xml).isNotEmpty()

        assertThat(xml).contains("<ram:ID>${DataGenerator.InvoiceNumber}</ram:ID>")
        assertThat(xml).contains("""<udt:DateTimeString format="102">${DataGenerator.InvoicingDate.toString().replace("-", "")}</udt:DateTimeString>""")
    }

}
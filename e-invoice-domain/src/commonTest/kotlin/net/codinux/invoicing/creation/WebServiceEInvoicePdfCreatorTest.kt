package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoicePdfCreatorTest {

    private val underTest = WebServiceEInvoicePdfCreator()


    @Test
    fun createFacturXPdf() = runTest {
        val invoice = createInvoice()

        val result = underTest.createFacturXPdf(invoice)

        assertInvoice(result)
    }

    @Test
    fun createFacturXPdfFromXml() = runTest {
        val result = underTest.createFacturXPdf(TestData.FacturXXml, EInvoiceXmlFormat.FacturX)

        assertInvoice(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoice(response: ByteArray?) {
        assertThat(response).isNotNull()

        assertThat(response!!.size).isGreaterThan(32_000)
    }

}
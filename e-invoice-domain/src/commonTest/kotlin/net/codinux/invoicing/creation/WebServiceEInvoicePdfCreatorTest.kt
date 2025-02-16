package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isGreaterThan
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.InvoicePdfSettings
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoicePdfCreatorTest {

    private val underTest = WebServiceEInvoicePdfCreator()


    @Test
    fun createInvoicePdf() = runTest {
        val invoice = createInvoice()

        val result = underTest.createInvoicePdf(invoice)

        assertInvoice(result)
    }

    @Test
    fun createInvoicePdfFromXml() = runTest {
        val result = underTest.createInvoicePdf(TestData.FacturXXml, InvoicePdfSettings(EInvoiceXmlFormat.FacturX))

        assertInvoice(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoice(result: Result<Pdf>) {
        val response = Asserts.assertSuccess(result).bytes

        assertThat(response.size).isGreaterThan(14_000)
    }

}
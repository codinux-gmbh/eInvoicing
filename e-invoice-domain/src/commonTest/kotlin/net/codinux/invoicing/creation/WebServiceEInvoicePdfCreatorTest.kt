package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.test.DataGenerator
import kotlin.test.Test

class WebServiceEInvoicePdfCreatorTest {

    private val underTest = WebServiceEInvoicePdfCreator()


    @Test
    fun createFacturXPdf() = runTest {
        val invoice = createInvoice()

        val result = underTest.createFacturXPdf(invoice)

        assertInvoice(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoice(response: ByteArray?) {
        assertThat(response).isNotNull()

        assertThat(response!!.size).isGreaterThan(32_000)
    }

}
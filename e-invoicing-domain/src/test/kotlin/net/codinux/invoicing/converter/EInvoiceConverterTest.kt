package net.codinux.invoicing.converter

import assertk.assertThat
import assertk.assertions.isNotEmpty
import net.codinux.invoicing.test.DataGenerator
import kotlin.test.Test

class EInvoiceConverterTest {

    private val underTest = EInvoiceConverter()


    @Test
    fun convertCiiToUbl() {
        val invoice = createInvoice()

        val result = underTest.convertCiiToUbl(invoice)

        assertThat(result).isNotEmpty()
    }


    private fun createInvoice() = DataGenerator.createInvoice()

}
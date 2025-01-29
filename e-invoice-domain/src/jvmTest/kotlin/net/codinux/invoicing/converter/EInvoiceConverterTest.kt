package net.codinux.invoicing.converter

import assertk.assertThat
import assertk.assertions.isNotEmpty
import assertk.assertions.isNotNull
import net.codinux.invoicing.test.DataGenerator
import java.io.File
import kotlin.test.Test

class EInvoiceConverterTest {

    private val underTest = EInvoiceConverter()


    @Test
    fun convertInvoiceToHtml() {
        val invoice = createInvoice()
        val testFile = File.createTempFile("Zugferd", ".html")

        val result = underTest.convertInvoiceToHtml(invoice, testFile)

        assertThat(result.value).isNotNull().isNotEmpty()
    }

    @Test
    fun convertCiiToUbl() {
        val invoice = createInvoice()

        val result = underTest.convertCiiToUbl(invoice)

        assertThat(result.value).isNotNull().isNotEmpty()
    }


    private fun createInvoice() = DataGenerator.createInvoice()

}
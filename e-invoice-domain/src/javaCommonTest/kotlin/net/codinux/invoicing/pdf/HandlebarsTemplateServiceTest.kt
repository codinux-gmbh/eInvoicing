package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.contains
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.TestUtils
import kotlin.io.path.readText
import kotlin.test.Test

class HandlebarsTemplateServiceTest {

    private val invoice = DataGenerator.createInvoice()

    private val template by lazy { TestUtils.getTestFile("Invoice.handlebars.html", "templates/invoice").readText() }

    private val underTest = HandlebarsTemplateService()


    @Test
    fun renderTemplate() {
        val result = underTest.renderTemplate(template, invoice)

        assertThat(result).contains(invoice.details.invoiceNumber)
    }

}
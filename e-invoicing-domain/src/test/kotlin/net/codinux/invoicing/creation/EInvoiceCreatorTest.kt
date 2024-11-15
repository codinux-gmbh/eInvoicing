package net.codinux.invoicing.creation

import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceAsserter
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
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

    @Test
    fun createZugferdPdf() {
        val invoice = createInvoice()
        val testFile = File.createTempFile("Zugferd", ".pdf")

        underTest.createZugferdPdf(invoice, testFile)

        val importer = ZUGFeRDInvoiceImporter(testFile.inputStream())
        val xml = String(importer.rawXML, Charsets.UTF_8)

        assertInvoiceXml(xml)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(xml: String) {
        InvoiceAsserter.assertInvoiceXml(xml)
    }

}
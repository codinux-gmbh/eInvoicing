package net.codinux.invoicing.reader

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestUtils
import kotlin.test.Test

class EInvoiceReaderTest {

    private val underTest = EInvoiceReader()


    @Test
    fun extractFromXml() {
        val result = underTest.extractFromXml(getTestFile("XRechnung.xml"))

        assertInvoice(result.invoice)
    }

    @Test
    fun extractFromPdf() {
        val result = underTest.extractFromPdf(getTestFile("ZUGFeRD.pdf"))

        assertInvoice(result.invoice)
    }

    @Test
    fun extractXmlFromPdf() {
        val result = underTest.extractXmlFromPdf(getTestFile("ZUGFeRD.pdf"))

        InvoiceAsserter.assertInvoiceXml(result.invoiceXml)
    }


    private fun getTestFile(filename: String) = TestUtils.getTestFileAsStream(filename)

    private fun assertInvoice(invoice: Invoice?) {
        InvoiceAsserter.assertInvoice(invoice)
    }

}
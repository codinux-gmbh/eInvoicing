package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceXmlAsserter
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import kotlin.test.Test

class JvmEInvoicePdfCreatorTest {

    private val underTest = JvmEInvoicePdfCreator()


    @Test
    fun createPdfWithAttachedXml_FacturX() {
        val invoice = createInvoice()
        val testFile = File.createTempFile("Zugferd", ".pdf")

        underTest.createPdfWithAttachedXml(invoice, testFile, EInvoiceXmlFormat.FacturX)

        val importer = testFile.inputStream().use { ZUGFeRDInvoiceImporter(it) }
        val xml = importer.utF8

        assertInvoiceXml(xml)
    }

    @Test
    fun createPdfWithAttachedXml_XRechnung() {
        val invoice = createInvoice()
        val testFile = File.createTempFile("Zugferd", ".pdf")

        underTest.createPdfWithAttachedXml(invoice, testFile, EInvoiceXmlFormat.XRechnung)

        val importer = testFile.inputStream().use { ZUGFeRDInvoiceImporter(it) }
        val xml = importer.utF8

        assertInvoiceXml(xml)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(xml: String) {
        InvoiceXmlAsserter.assertInvoiceXml(xml)
    }

}
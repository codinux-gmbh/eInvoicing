package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.pdf.ReadEInvoicePdfResultJvm
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.InvoiceXmlAsserter
import net.codinux.invoicing.test.TestUtils
import kotlin.test.Test

class EInvoiceReaderTest {

    private val underTest = EInvoiceReader()


    @Test
    fun extractFromXml() {
        val result = underTest.extractFromXml(getTestFile("XRechnung.xml"))

        assertInvoice(result)
    }

    @Test
    fun extractFromXml_EmptyFile() {
        val result = underTest.extractFromXml(getInvalidInvoiceFile("EmptyFile.xml"))

        assertThat(result.invoice).isNull()
        assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.InvalidXml)
        assertThat(result.readError).isNotNull()
    }

    @Test
    fun extractFromXml_NoCountryCode() {
        val result = underTest.extractFromXml(getInvalidInvoiceFile("NoCountryCode.xml"))

        assertThat(result.invoice).isNull()
        assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.InvalidInvoiceData)
        assertThat(result.readError).isNotNull().hasClass(IllegalArgumentException::class)
    }


    @Test
    fun extractFromPdf() {
        val result = underTest.extractFromPdf(getTestFile("ZUGFeRD.pdf"))

        assertInvoice(result)
    }

    @Test
    fun extractXmlFromPdf() {
        val result = underTest.extractXmlFromPdf(getTestFile("ZUGFeRD.pdf"))

        InvoiceXmlAsserter.assertInvoiceXml(result.invoiceXml)
    }


    private fun getTestFile(filename: String) = TestUtils.getTestFileAsStream(filename)

    private fun getInvalidInvoiceFile(filename: String) = TestUtils.getInvalidInvoiceFileAsStream(filename)


    private fun assertInvoice(result: ReadEInvoiceXmlResult) {
        assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)
        assertThat(result.readError).isNull()

        assertInvoice(result.invoice)
    }

    private fun assertInvoice(result: ReadEInvoicePdfResultJvm) {
        assertThat(result.attachmentExtractionResult.type).isEqualByComparingTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(result.attachmentExtractionResult.attachments).hasSize(1)

        val attachment = result.attachmentExtractionResult.attachments.first()
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.isProbablyEN16931InvoiceXml).isTrue()
        InvoiceXmlAsserter.assertInvoiceXml(attachment.xml)

        assertThat(result.readEInvoiceXmlResult).isNotNull()
        assertThat(result.readEInvoiceXmlResult!!.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)
        assertThat(result.readEInvoiceXmlResult!!.readError).isNull()

        assertInvoice(result.invoice)
    }

    private fun assertInvoice(invoice: Invoice?) {
        InvoiceAsserter.assertInvoice(invoice)
    }

}
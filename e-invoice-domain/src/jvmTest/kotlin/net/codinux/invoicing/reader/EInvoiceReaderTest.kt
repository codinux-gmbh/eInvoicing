package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.InvoiceDataErrorType
import net.codinux.invoicing.model.InvoiceField
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
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

        assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.InvalidInvoiceData)
        assertThat(result.readError).isNull()
        assertThat(result.invoice).isNotNull()
        assertThat(result.invoice!!.invoice.supplier.country).isEqualByComparingTo(MustangMapper.CountryFallbackValue)
        assertThat(result.invoice!!.invoiceDataErrors).hasSize(2)

        result.invoice!!.invoiceDataErrors.forEach { invoiceDataError ->
            assertThat(invoiceDataError.field).isIn(InvoiceField.SupplierCountry, InvoiceField.CustomerCountry)
            assertThat(invoiceDataError.errorType).isEqualTo(InvoiceDataErrorType.ValueNotSet)
            assertThat(invoiceDataError.erroneousValue).isNullOrEmpty()
        }
    }

    @Test
    fun extractFromXml_InvalidTotalAmount() {
        val result = underTest.extractFromXml(getInvalidInvoiceFile("InvalidTotalAmount.xml"))

        assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.InvalidInvoiceData)
        assertThat(result.readError).isNull()
        assertThat(result.invoice).isNotNull()
        assertThat(result.invoice!!.invoiceDataErrors).hasSize(1)

        val invoiceDataError = result.invoice!!.invoiceDataErrors.first()
        assertThat(invoiceDataError.field).isIn(InvoiceField.TotalAmount)
        assertThat(invoiceDataError.errorType).isEqualTo(InvoiceDataErrorType.CalculatedAmountsAreInvalid)
        assertThat(invoiceDataError.erroneousValue).isNull()
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

    private fun assertInvoice(result: ReadEInvoicePdfResult) {
        assertThat(result.attachmentExtractionResult.type).isEqualByComparingTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(result.attachmentExtractionResult.attachments).hasSize(1)

        val attachment = result.attachmentExtractionResult.attachments.first()
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.isProbablyEN16931InvoiceXml).isTrue()
        InvoiceXmlAsserter.assertInvoiceXml(attachment.xml)

        assertThat(result.type).isEqualByComparingTo(ReadEInvoicePdfResultType.Success)
        assertThat(result.attachmentExtractionResult.type).isEqualByComparingTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(result.readError).isNull()

        assertInvoice(result.invoice)
    }

    private fun assertInvoice(invoice: MapInvoiceResult?) {
        InvoiceAsserter.assertInvoice(invoice)
    }

}
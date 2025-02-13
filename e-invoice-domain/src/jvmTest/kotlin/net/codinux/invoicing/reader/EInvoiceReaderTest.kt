package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.model.InvoiceDataErrorType
import net.codinux.invoicing.model.InvoiceField
import net.codinux.invoicing.model.MapInvoiceResult
import net.codinux.invoicing.model.mapper.CiiMapper
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
        assertThat(result.invoice!!.invoice.supplier.country).isEqualByComparingTo(CiiMapper.CountryFallbackValue)
        assertThat(result.invoice!!.invoiceDataErrors).hasSize(4)

        val countryErrors = result.invoice!!.invoiceDataErrors.filter { it.field in listOf(InvoiceField.SupplierCountry, InvoiceField.CustomerCountry) }
        assertThat(countryErrors).hasSize(2)

        countryErrors.forEach { invoiceDataError ->
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
    fun extractFromXml_AllInvalidDataErrors() {
        val result = underTest.extractFromXml(getInvalidInvoiceFile("AllInvalidDataErrors.xml"))

        assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.InvalidInvoiceData)
        assertThat(result.readError).isNull()
        assertThat(result.invoice).isNotNull()
        assertThat(result.invoice!!.invoiceDataErrors).hasSize(5)

        val currencyError = result.invoice!!.invoiceDataErrors.first()
        assertThat(currencyError.field).isIn(InvoiceField.Currency)
        assertThat(currencyError.errorType).isEqualTo(InvoiceDataErrorType.ValueIsInvalid)
        assertThat(currencyError.erroneousValue).isEqualTo("D-MARK")

        val supplierCountryError = result.invoice!!.invoiceDataErrors[1]
        assertThat(supplierCountryError.field).isIn(InvoiceField.SupplierCountry)
        assertThat(supplierCountryError.errorType).isEqualTo(InvoiceDataErrorType.ValueNotUpperCase)
        assertThat(supplierCountryError.erroneousValue).isEqualTo("de")

        val customerCountryError = result.invoice!!.invoiceDataErrors[2]
        assertThat(customerCountryError.field).isIn(InvoiceField.CustomerCountry)
        assertThat(customerCountryError.errorType).isEqualTo(InvoiceDataErrorType.ValueNotSet)
        assertThat(customerCountryError.erroneousValue).isEqualTo("")

        val itemUnitError = result.invoice!!.invoiceDataErrors[3]
        assertThat(itemUnitError.field).isIn(InvoiceField.ItemUnit)
        assertThat(itemUnitError.errorType).isEqualTo(InvoiceDataErrorType.ValueIsInvalid)
        assertThat(itemUnitError.erroneousValue).isEqualTo("invalid")

        val totalAmountError = result.invoice!!.invoiceDataErrors[4]
        assertThat(totalAmountError.field).isIn(InvoiceField.TotalAmount)
        assertThat(totalAmountError.errorType).isEqualTo(InvoiceDataErrorType.CalculatedAmountsAreInvalid)
        assertThat(totalAmountError.erroneousValue).isNull()
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
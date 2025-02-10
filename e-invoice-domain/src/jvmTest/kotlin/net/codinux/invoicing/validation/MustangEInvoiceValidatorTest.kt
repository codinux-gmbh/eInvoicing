package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestUtils
import kotlin.test.Test

class MustangEInvoiceValidatorTest {

    private val underTest = MustangEInvoiceValidator()


    @Test
    fun validateXRechnung() {
        val testFile = getTestFile("XRechnung.xml")

        val resultHolder = underTest.validate(testFile)

        val result = Asserts.assertSuccess(resultHolder)
        assertThat(result.isValid).isFalse() // TODO: add required properties to XRechnung.xml
        assertThat(result.xmlValidationResults).hasSize(3)
        assertThat(result.countXmlNotices).isEqualTo(0)
        assertThat(result.countXmlErrors).isEqualTo(2)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }

    @Test
    fun validateZugferdPdf() {
        val testFile = getTestFile("ZUGFeRD.pdf")

        val resultHolder = underTest.validate(testFile)

        val result = Asserts.assertSuccess(resultHolder)
        assertThat(result.isValid).isTrue()
        assertThat(result.xmlValidationResults).hasSize(0)
        assertThat(result.countXmlNotices).isEqualTo(0)
        assertThat(result.countXmlErrors).isEqualTo(0)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }

    @Test
    fun validateZugferdXml() {
        val testFile = getTestFile("ZUGFeRD.xml")

        val resultHolder = underTest.validate(testFile)

        val result = Asserts.assertSuccess(resultHolder)
        assertThat(result.isValid).isTrue()
        assertThat(result.xmlValidationResults).hasSize(0)
        assertThat(result.countXmlNotices).isEqualTo(0)
        assertThat(result.countXmlErrors).isEqualTo(0)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }


    @Test
    fun validateInvoiceWithoutCountryCode() {
        val result = underTest.validate(getInvalidTestFile("NoCountryCode.xml"))

        assertValidationFailed(result, 11 * 2) // 11 is correct, but Mustang validates file twice, once with Profile Schematron and then a second time with CEN CII Schematron
    }

    @Test
    fun validateInvoiceWithManyErrors() {
        val result = underTest.validate(getInvalidTestFile("AllInvalidDataErrors.xml"))

        assertValidationFailed(result, 7 * 2 + 1) // Mustang validates twice + there's an additional rule in CEN CII Schematron that says calculated total amount is wrong, will have to check that
    }


    private fun assertValidationFailed(resultHolder: Result<InvoiceXmlValidationResult>, countValidationErrors: Int) {
        val result = Asserts.assertSuccess(resultHolder)

        assertThat(result.isValid).isFalse()
        assertThat(result.countXmlErrors).isEqualTo(countValidationErrors)
        assertThat(result.xmlValidationResults).hasSize(countValidationErrors)
    }


    private fun getTestFile(filename: String) = TestUtils.getTestFile(filename).toFile()

    private fun getInvalidTestFile(filename: String) = TestUtils.getInvalidInvoiceFile(filename).toFile()

}
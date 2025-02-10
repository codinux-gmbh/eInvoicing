package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoicingStandard
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestUtils
import kotlin.io.path.readText
import kotlin.test.Test

class EInvoiceXmlValidatorTest {

    private val underTest = EInvoiceXmlValidator()



    @Test
    fun validateEN16931InvoiceWithBasicProfile() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("ZUGFeRD.xml"), profileFor(FacturXProfile.Basic))

        assertWrongProfileResult(result)
    }

    @Test
    fun validateEN16931InvoiceWithEN16931Profile() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("ZUGFeRD.xml"), profileFor(FacturXProfile.EN16931))

        assertValidationSucceeded(result)
    }

    @Test
    fun validateEN16931InvoiceWithExtendedProfile() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("ZUGFeRD.xml"), profileFor(FacturXProfile.Extended))

        assertWrongProfileResult(result)
    }


    @Test
    fun validateInvoiceWithoutCountryCode() {
        val result = underTest.validateEInvoiceXmlJvm(getInvalidTestFile("NoCountryCode.xml"), profileFor(FacturXProfile.EN16931))

        assertValidationFailed(result, 11)
    }

    @Test
    fun validateInvoiceWithManyErrors() {
        val result = underTest.validateEInvoiceXmlJvm(getInvalidTestFile("AllInvalidDataErrors.xml"), profileFor(FacturXProfile.EN16931))

        assertValidationFailed(result, 7)
    }


    private fun profileFor(profile: FacturXProfile) =
        EInvoiceFormatDetectionResult(EInvoicingStandard.CII, null, null, profile)

    private fun getValidTestFile(filename: String) = TestUtils.getTestFile(filename).readText()

    private fun getInvalidTestFile(filename: String) = TestUtils.getInvalidInvoiceFile(filename).readText()


    private fun assertValidationSucceeded(result: Result<InvoiceXmlValidationResult>) {
        val result = Asserts.assertSuccess(result)

        assertThat(result.isValid).isTrue()
//        assertThat(result.countFiredRules).isGreaterThan(60)
//        assertThat(result.countActivePatterns).isGreaterThan(200)
//        assertThat(result.failedAsserts).isEmpty()
        assertThat(result.xmlValidationResults).isEmpty()
    }

    private fun assertWrongProfileResult(result: Result<InvoiceXmlValidationResult>) {
        val result = assertValidationFailed(result, 1)

        val failedAssert = result.xmlValidationResults.first()
        assertThat(failedAssert.location).isEqualTo("/:rsm:CrossIndustryInvoice/:rsm:ExchangedDocumentContext/:ram:GuidelineSpecifiedDocumentContextParameter/:ram:ID/")
//        assertThat(failedAssert.messages).hasSize(1)
//        assertThat(failedAssert.messages.first()).isEqualTo("Value of 'ram:ID' is not allowed.")
        assertThat(failedAssert.message).isEqualTo("Value of 'ram:ID' is not allowed.")
    }

    private fun assertValidationFailed(result: Result<InvoiceXmlValidationResult>, countValidationErrors: Int): InvoiceXmlValidationResult {
        val result = Asserts.assertSuccess(result)

        assertThat(result.isValid).isFalse()
//        assertThat(result.countFiredRules).isGreaterThan(40)
//        assertThat(result.countActivePatterns).isGreaterThan(200)
//        assertThat(result.failedAsserts).hasSize(countValidationErrors)
        assertThat(result.xmlValidationResults).hasSize(countValidationErrors)

        return result
    }

}
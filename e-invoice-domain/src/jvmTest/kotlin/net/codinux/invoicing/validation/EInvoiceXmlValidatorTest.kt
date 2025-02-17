package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoicingStandard
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestUtils
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.test.Test

class EInvoiceXmlValidatorTest {

    private val underTest = EInvoiceXmlValidator()



    @Test
    fun validateEN16931InvoiceWithMinimumProfile() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("ZUGFeRD.xml"), profileFor(FacturXProfile.Minimum))

        assertWrongProfileResult(result)
    }


    @Test
    fun validateEN16931InvoiceWithBasicWLProfile() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("ZUGFeRD.xml"), profileFor(FacturXProfile.BasicWL))

        assertWrongProfileResult(result)
    }


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
    fun validateEN16931InvoiceWithCenSchematronXslt() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("ZUGFeRD.xml"), EInvoiceFormatDetectionResult.CII)

        assertValidationSucceeded(result)
    }


    @Test
    fun validateXRechnungWithXRechnungProfile() {
        val result = underTest.validateEInvoiceXmlJvm(getValidTestFile("XRechnung.xml"), profileFor(FacturXProfile.XRechnung))

        assertValidationFailed(result, 2)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideXRechnungUblInvoices")
    fun `XRechnung UBL`(invoiceFile: Path) {
        val result = underTest.validateEInvoiceXmlJvm(invoiceFile.readText(), EInvoiceFormatDetectionResult(EInvoicingStandard.UBL, net.codinux.invoicing.format.EInvoiceFormat.XRechnung))

        assertValidationSucceeded(result)
    }


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideValidNonXRechnungUbl_2_1_Invoices")
    fun `Non-XRechnung UBL`(invoiceFile: Path) {
        val result = underTest.validateEInvoiceXmlJvm(invoiceFile.readText(), EInvoiceFormatDetectionResult.UBL)

        assertValidationSucceeded(result)
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

    @Test
    fun validateInvoiceWithManyErrorsWithCenSchematronXslt() {
        val result = underTest.validateEInvoiceXmlJvm(getInvalidTestFile("AllInvalidDataErrors.xml"), EInvoiceFormatDetectionResult.CII)

        // 8 instead of 7 as when validating with Factur-X Schematron: additionally finds:
        // "[BR-CO-15]-Invoice total amount with VAT (BT-112) = Invoice total amount without VAT (BT-109) + Invoice total VAT amount (BT-110)."
        assertValidationFailed(result, 8)
    }


    private fun profileFor(profile: FacturXProfile) =
        EInvoiceFormatDetectionResult(EInvoicingStandard.CII, null, null, profile)

    private fun getValidTestFile(filename: String) = TestUtils.getTestFile(filename).readText()

    private fun getInvalidTestFile(filename: String) = TestUtils.getInvalidInvoiceFile(filename).readText()


    private fun assertValidationSucceeded(result: Result<InvoiceXmlValidationResult>) {
        val result = Asserts.assertSuccess(result)

        assertThat(result.isValid).isTrue()
//        assertThat(result.countAvailableTests).isGreaterThan(200)
//        assertThat(result.countAppliedTests).isGreaterThan(60)
        assertThat(result.businessRulesValidationResult).isEmpty()
    }

    private fun assertWrongProfileResult(result: Result<InvoiceXmlValidationResult>) {
        val result = assertValidationFailed(result, 1)

        val failedAssert = result.businessRulesValidationResult.first()
        assertThat(failedAssert.location).isEqualTo("/:rsm:CrossIndustryInvoice/:rsm:ExchangedDocumentContext/:ram:GuidelineSpecifiedDocumentContextParameter/:ram:ID/")
//        assertThat(failedAssert.messages).hasSize(1)
//        assertThat(failedAssert.messages.first()).isEqualTo("Value of 'ram:ID' is not allowed.")
        assertThat(failedAssert.message).isEqualTo("Value of 'ram:ID' is not allowed.")
    }

    private fun assertValidationFailed(result: Result<InvoiceXmlValidationResult>, countValidationErrors: Int): InvoiceXmlValidationResult {
        val result = Asserts.assertSuccess(result)

        assertThat(result.isValid).isFalse()
//        assertThat(result.countAvailableTests).isGreaterThan(200)
//        assertThat(result.countAppliedTests).isGreaterThan(40)
        assertThat(result.businessRulesValidationResult.filter { it.severity == ValidationResultItemSeverity.Error }).hasSize(countValidationErrors)

        return result
    }

}
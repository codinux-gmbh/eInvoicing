package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.*
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoiceValidatorTest {

    private val underTest = WebServiceEInvoiceValidator()


    @Test
    fun validateXRechnungXml() = runTest {
        val webResult = underTest.validateEInvoiceXml(TestData.XRechnungXml)

        val result = Asserts.assertSuccess(webResult)
        assertThat(result.isValid).isFalse() // TODO: add required properties to XRechnung.xml
        assertThat(result.reportAsXml).isNotEmpty()
        assertThat(result.xmlValidationResults).hasSize(3)
        assertThat(result.countXmlNotices).isEqualTo(0)
        assertThat(result.countXmlErrors).isEqualTo(2)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }

    @Test
    fun validateFacturXXml() = runTest {
        val result = underTest.validateEInvoiceXml(TestData.FacturXXml)

        assertValidationResult(result)
    }

    @Test
    fun validateFacturXPdf() = runTest {
        val result = underTest.validateEInvoiceFile(TestData.FacturXPdf)

        assertValidationResult(result)
    }


    private fun assertValidationResult(result: Result<InvoiceValidationResult>) {
        val validationResult = Asserts.assertSuccess(result)
        assertThat(validationResult.xmlValidationResults).isEmpty()
    }

}
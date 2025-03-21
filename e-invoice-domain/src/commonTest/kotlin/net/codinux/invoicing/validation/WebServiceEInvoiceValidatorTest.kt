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
        assertThat(result.businessRulesValidationResult).hasSize(3)
    }

    @Test
    fun validateFacturXXml() = runTest {
        val result = underTest.validateEInvoiceXml(TestData.FacturXXml)

        assertXmlValidationResult(result)
    }

    @Test
    fun validateFacturXPdf() = runTest {
        val result = underTest.validateEInvoicePdf(TestData.FacturXPdf)

        assertPdfValidationResult(result)
    }


    private fun assertXmlValidationResult(result: Result<InvoiceXmlValidationResult>) {
        val validationResult = Asserts.assertSuccess(result)

        assertThat(validationResult.isValid).isTrue()
        assertThat(validationResult.businessRulesValidationResult).isEmpty()
    }

    private fun assertPdfValidationResult(result: Result<PdfValidationResult>) {
        val validationResult = Asserts.assertSuccess(result)

        assertThat(validationResult.isValid).isTrue()
        assertThat(validationResult.validationErrors).isEmpty()
    }

}
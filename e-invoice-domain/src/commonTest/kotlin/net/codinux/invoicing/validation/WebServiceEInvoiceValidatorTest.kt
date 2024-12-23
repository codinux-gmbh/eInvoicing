package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoiceValidatorTest {

    private val underTest = WebServiceEInvoiceValidator()


    @Test
    fun validateXRechnungXml() = runTest {
        val result = underTest.validateEInvoiceXml(TestData.XRechnungXml)

        assertValidationResult(result)
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


    private fun assertValidationResult(result: String?) {
        assertThat(result).isNotNull()

        assertThat(result!!.length).isGreaterThan(2_400)
    }

}
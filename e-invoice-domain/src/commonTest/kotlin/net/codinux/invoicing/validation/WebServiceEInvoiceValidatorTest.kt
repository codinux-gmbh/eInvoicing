package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotEmpty
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoiceValidatorTest {

    private val underTest = WebServiceEInvoiceValidator()


    @Test
    fun validateXRechnungXml() = runTest {
        val result = underTest.validateEInvoiceXml(TestData.XRechnungXml, invoiceFilename = "XRechnung.xml")

        assertValidationResult(result)
    }

    @Test
    fun validateFacturXXml() = runTest {
        val result = underTest.validateEInvoiceXml(TestData.FacturXXml, invoiceFilename = "Factur-X.xml")

        assertValidationResult(result)
    }

    @Test
    fun validateFacturXPdf() = runTest {
        val result = underTest.validateEInvoiceFile(TestData.FacturXPdf, invoiceFilename = "Factur-X.pdf")

        assertValidationResult(result)
    }


    private fun assertValidationResult(result: InvoiceValidationResult?) {
        assertThat(result).isNotNull()
        assertThat(result!!.xmlValidationResults).isNotEmpty()

        val report = result.reportAsXml
        assertThat(report.length).isGreaterThan(2_400)
    }

}
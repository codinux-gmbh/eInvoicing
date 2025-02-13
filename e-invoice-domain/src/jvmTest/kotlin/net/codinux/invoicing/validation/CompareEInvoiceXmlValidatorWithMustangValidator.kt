package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestUtils
import net.codinux.invoicing.test.mustang.MustangEInvoiceValidator
import kotlin.io.path.readText
import kotlin.test.Test

class CompareEInvoiceXmlValidatorWithMustangValidator {

    private val mustang = MustangEInvoiceValidator()

    private val eInvoicing = EInvoiceXmlValidator()


    @Test
    fun xrechnung() = runTest {
        val xrechnung = getValidTestFile("XRechnung.xml")


        val mustangResult = Asserts.assertSuccess(mustang.validateEInvoiceXml(xrechnung))

        val eInvoicingResult = Asserts.assertSuccess(eInvoicing.validateEInvoiceXml(xrechnung))


        assertThat(mustangResult.isValid).isEqualTo(eInvoicingResult.isValid)
        assertThat(mustangResult.errors).hasSize(eInvoicingResult.errors.size)
        mustangResult.errors.forEachIndexed { index, mustangError ->
            val eInvoicingError = eInvoicingResult.errors[index]
            // the messages differ as Mustang adds some information like the .xslt file
            assertThat(mustangError.message).contains(eInvoicingError.message)
            assertThat(mustangError.location).isEqualTo(eInvoicingError.location)
            assertThat(mustangError.test).isEqualTo(eInvoicingError.test)
            assertThat(mustangError.testId).isEqualTo(eInvoicingError.testId)
        }
    }


    private fun getValidTestFile(filename: String) = TestUtils.getTestFile(filename).readText()

    private fun getInvalidTestFile(filename: String) = TestUtils.getInvalidInvoiceFile(filename).readText()

}
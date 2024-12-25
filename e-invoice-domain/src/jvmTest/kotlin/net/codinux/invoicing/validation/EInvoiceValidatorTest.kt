package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.test.TestUtils
import kotlin.test.Test

class EInvoiceValidatorTest {

    private val underTest = EInvoiceValidator()


    @Test
    fun validateXRechnung() {
        val testFile = getTestFile("XRechnung.xml")

        val result = underTest.validate(testFile)

        assertThat(result.isValid).isFalse() // TODO: add required properties to XRechnung.xml
        assertThat(result.reportAsXml).isNotEmpty()
        assertThat(result.xmlValidationResults).hasSize(3)
        assertThat(result.countXmlNotices).isEqualTo(0)
        assertThat(result.countXmlErrors).isEqualTo(2)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }

    @Test
    fun validateZugferdPdf() {
        val testFile = getTestFile("ZUGFeRD.pdf")

        val result = underTest.validate(testFile)


        assertThat(result.isValid).isTrue()
        assertThat(result.reportAsXml).isNotEmpty()
        assertThat(result.xmlValidationResults).hasSize(5)
        assertThat(result.countXmlNotices).isEqualTo(5)
        assertThat(result.countXmlErrors).isEqualTo(0)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }

    @Test
    fun validateZugferdXml() {
        val testFile = getTestFile("ZUGFeRD.xml")

        val result = underTest.validate(testFile)

        assertThat(result.isValid).isTrue()
        assertThat(result.reportAsXml).isNotEmpty()
        assertThat(result.xmlValidationResults).hasSize(5)
        assertThat(result.countXmlNotices).isEqualTo(5)
        assertThat(result.countXmlErrors).isEqualTo(0)
        assertThat(result.countXmlFatalOrException).isEqualTo(0)
    }


    private fun getTestFile(filename: String) = TestUtils.getTestFile(filename).toFile()

}
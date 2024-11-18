package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isNotEmpty
import assertk.assertions.isTrue
import java.io.File
import kotlin.test.Test

class EInvoiceValidatorTest {

    private val underTest = EInvoiceValidator()


    @Test
    fun validateXRechnung() {
        val testFile = getTestFile("XRechnung.xml")

        val result = underTest.validate(testFile)

        assertThat(result.isValid).isFalse() // TODO: add required properties to XRechnung.xml
        assertThat(result.report).isNotEmpty()
    }

    @Test
    fun validateZugferdPdf() {
        val testFile = getTestFile("ZUGFeRD.pdf")

        val result = underTest.validate(testFile)

        assertThat(result.isValid).isTrue()
        assertThat(result.report).isNotEmpty()
    }

    @Test
    fun validateZugferdXml() {
        val testFile = getTestFile("ZUGFeRD.xml")

        val result = underTest.validate(testFile)

        assertThat(result.isValid).isTrue()
        assertThat(result.report).isNotEmpty()
    }


    private fun getTestFile(filename: String): File =
        File(this.javaClass.classLoader.getResource("files/$filename")!!.toURI())

}
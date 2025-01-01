package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import kotlinx.serialization.encodeToString
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestInstances
import net.codinux.invoicing.test.TestUtils
import kotlin.test.Test

class ReadInvoiceResultSerializationTest {

    private val jacksonObjectMapper = TestInstances.objectMapper

    private val kotlinxJson = TestInstances.json

    private val reader = EInvoiceReader()


    @Test
    fun extractFromXml() {
        val readXmlResult = reader.extractFromXml(getTestFile("XRechnung.xml"))

        val json = jacksonObjectMapper.writeValueAsString(readXmlResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoiceXmlResult>(json)

        assertEquals(readXmlResult, decoded)
    }

    @Test
    fun extractFromXml_EmptyFile() {
        val erroneousReadXmlResult = reader.extractFromXml(getInvalidInvoiceFile("EmptyFile.xml"))

        val json = jacksonObjectMapper.writeValueAsString(erroneousReadXmlResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoiceXmlResult>(json)

        assertEquals(erroneousReadXmlResult, decoded)
    }

    @Test
    fun extractFromXml_EmptyFile_SerializedJsonEquals() {
        val erroneousReadXmlResult = reader.extractFromXml(getInvalidInvoiceFile("EmptyFile.xml"))

        val jacksonResult = jacksonObjectMapper.writeValueAsString(erroneousReadXmlResult)

        val kotlinxSerializationResult = kotlinxJson.encodeToString(erroneousReadXmlResult)

        assertThat(jacksonResult).isEqualTo(kotlinxSerializationResult)
    }

    @Test
    fun extractFromXml_NoCountryCode() {
        val erroneousReadXmlResult = reader.extractFromXml(getInvalidInvoiceFile("NoCountryCode.xml"))

        val json = jacksonObjectMapper.writeValueAsString(erroneousReadXmlResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoiceXmlResult>(json)

        assertEquals(erroneousReadXmlResult, decoded)
    }

    @Test
    fun extractFromXml_NoCountryCode_SerializedJsonEquals() {
        val erroneousReadXmlResult = reader.extractFromXml(getInvalidInvoiceFile("NoCountryCode.xml"))

        val jacksonResult = jacksonObjectMapper.writeValueAsString(erroneousReadXmlResult)

        val kotlinxSerializationResult = kotlinxJson.encodeToString(erroneousReadXmlResult)

        assertThat(jacksonResult).isEqualTo(kotlinxSerializationResult)
    }


    private fun getTestFile(filename: String) = TestUtils.getTestFileAsStream(filename)

    private fun getInvalidInvoiceFile(filename: String) = TestUtils.getInvalidInvoiceFileAsStream(filename)


    private fun assertEquals(expected: ReadEInvoiceXmlResult, decoded: ReadEInvoiceXmlResult) {
        assertThat(decoded.type).isEqualTo(expected.type)
        assertThat(decoded.readError?.type).isEqualTo(expected.readError?.type)
        assertThat(decoded.readError?.message).isEqualTo(expected.readError?.message)
        assertThat(decoded.readError?.stackTrace).isEqualTo(expected.readError?.stackTrace)

        if (expected.invoice == null) {
            assertThat(decoded.invoice).isNull()
        } else {
            InvoiceAsserter.assertInvoice(decoded.invoice)
        }
    }

}
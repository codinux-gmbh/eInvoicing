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


    private fun getTestFile(filename: String) = TestUtils.getTestFileAsStream(filename)


    private fun assertEquals(expected: ReadEInvoiceXmlResult, decoded: ReadEInvoiceXmlResult) {
        assertThat(decoded.type).isEqualTo(expected.type)

        if (expected.invoice == null) {
            assertThat(decoded.invoice).isNull()
        } else {
            InvoiceAsserter.assertInvoice(decoded.invoice)
        }
    }

}
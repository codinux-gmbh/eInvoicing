package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import kotlinx.coroutines.test.runTest
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


    @Test
    fun extractFromPdf() = runTest {
        val readPdfResult = reader.extractFromPdf(getTestFile("ZUGFeRD.pdf").readAllBytes())

        val json = jacksonObjectMapper.writeValueAsString(readPdfResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoicePdfResult>(json)

        assertEquals(readPdfResult, decoded)
    }

    @Test
    fun extractFromPdf_NotAValidPdf() = runTest {
        val erroneousReadPdfResult = reader.extractFromPdf(getInvalidInvoiceFile("NotAValidPdf.pdf").readAllBytes())

        val json = jacksonObjectMapper.writeValueAsString(erroneousReadPdfResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoicePdfResult>(json)

        assertEquals(erroneousReadPdfResult, decoded)
    }

    @Test
    fun extractFromPdf_NotAValidPdf_SerializedJsonEquals() = runTest {
        val erroneousReadPdfResult = reader.extractFromPdf(getInvalidInvoiceFile("NotAValidPdf.pdf").readAllBytes())

        val jacksonResult = jacksonObjectMapper.writeValueAsString(erroneousReadPdfResult)

        val kotlinxSerializationResult = kotlinxJson.encodeToString(erroneousReadPdfResult)

        assertThat(jacksonResult).isEqualTo(kotlinxSerializationResult)
    }

    @Test
    fun extractFromPdf_NoAttachments() = runTest {
        val erroneousReadPdfResult = reader.extractFromPdf(getInvalidInvoiceFile("NoAttachments.pdf").readAllBytes())

        val json = jacksonObjectMapper.writeValueAsString(erroneousReadPdfResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoicePdfResult>(json)

        assertEquals(erroneousReadPdfResult, decoded)
    }

    @Test
    fun extractFromPdf_NoAttachments_SerializedJsonEquals() = runTest {
        val erroneousReadPdfResult = reader.extractFromPdf(getInvalidInvoiceFile("NoAttachments.pdf").readAllBytes())

        val jacksonResult = jacksonObjectMapper.writeValueAsString(erroneousReadPdfResult)

        val kotlinxSerializationResult = kotlinxJson.encodeToString(erroneousReadPdfResult)

        assertThat(jacksonResult).isEqualTo(kotlinxSerializationResult)
    }

    @Test
    fun extractFromPdf_NoXmlAttachments() = runTest {
        val erroneousReadPdfResult = reader.extractFromPdf(getInvalidInvoiceFile("NoXmlAttachments.pdf").readAllBytes())

        val json = jacksonObjectMapper.writeValueAsString(erroneousReadPdfResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoicePdfResult>(json)

        assertEquals(erroneousReadPdfResult, decoded)
    }

    @Test
    fun extractFromPdf_NoXmlAttachments_SerializedJsonEquals() = runTest {
        val erroneousReadPdfResult = reader.extractFromPdf(getInvalidInvoiceFile("NoXmlAttachments.pdf").readAllBytes())

        val jacksonResult = jacksonObjectMapper.writeValueAsString(erroneousReadPdfResult)

        val kotlinxSerializationResult = kotlinxJson.encodeToString(erroneousReadPdfResult)

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

    private fun assertEquals(expected: ReadEInvoicePdfResult?, decoded: ReadEInvoicePdfResult) {
        assertThat(expected).isNotNull()

        assertThat(decoded.type).isEqualTo(expected!!.type)

        assertThat(decoded.attachmentExtractionResult.type).isEqualTo(expected.attachmentExtractionResult.type)
        assertThat(decoded.attachmentExtractionResult.attachments).hasSize(expected.attachmentExtractionResult.attachments.size)

        expected.attachmentExtractionResult.attachments.forEachIndexed { index, attachment ->
            val decodedAttachment = decoded.attachmentExtractionResult.attachments[index]
            assertThat(decodedAttachment.filename).isEqualTo(attachment.filename)
            assertThat(decodedAttachment.isXmlFile).isEqualTo(attachment.isXmlFile)
            assertThat(decodedAttachment.isProbablyEN16931InvoiceXml).isEqualTo(attachment.isProbablyEN16931InvoiceXml)
            assertThat(decodedAttachment.xml).isEqualTo(attachment.xml)
        }

        if (expected.invoice == null) {
            assertThat(decoded.invoice).isNull()
        } else {
            InvoiceAsserter.assertInvoice(decoded.invoice)
        }
    }

}
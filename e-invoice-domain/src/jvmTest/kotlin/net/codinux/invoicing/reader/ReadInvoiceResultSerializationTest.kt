package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import net.codinux.invoicing.email.model.ContentDisposition
import net.codinux.invoicing.email.model.EmailAttachment
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestInstances
import net.codinux.invoicing.test.TestUtils
import net.codinux.kotlin.extensions.countOccurrences
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


    @Test
    fun extractXmlFromPdf() {
        val attachmentExtractionResult = reader.extractXmlFromPdf(getTestFile("ZUGFeRD.pdf"))

        val json = jacksonObjectMapper.writeValueAsString(attachmentExtractionResult)

        val decoded = kotlinxJson.decodeFromString<PdfAttachmentExtractionResult>(json)

        assertEquals(attachmentExtractionResult, decoded)
    }

    @Test
    fun extractXmlFromPdf_readError() {
        val attachmentExtractionResult = reader.extractXmlFromPdf(getInvalidInvoiceFile("NotAValidPdf.pdf"))

        val json = jacksonObjectMapper.writeValueAsString(attachmentExtractionResult)

        val decoded = kotlinxJson.decodeFromString<PdfAttachmentExtractionResult>(json)

        assertEquals(attachmentExtractionResult, decoded)
    }

    @Test
    fun extractXmlFromPdf_readError_SerializedJsonEquals() {
        val attachmentExtractionResult = reader.extractXmlFromPdf(getInvalidInvoiceFile("NotAValidPdf.pdf"))

        val jacksonResult = jacksonObjectMapper.writeValueAsString(attachmentExtractionResult)

        val kotlinxSerializationResult = kotlinxJson.encodeToString(attachmentExtractionResult)

        assertThat(jacksonResult).isEqualTo(kotlinxSerializationResult)
    }

    @Test
    fun extractXmlFromPdf_EnsureJacksonDoesNotSerializeIgnoredDelegates() {
        val attachmentExtractionResult = reader.extractXmlFromPdf(getTestFile("ZUGFeRD.pdf"))

        val json = jacksonObjectMapper.writeValueAsString(attachmentExtractionResult)


        // ensure PdfAttachmentExtractionResult val invoiceXml: String? by lazy { attachments.firstOrNull { it.isProbablyEN16931InvoiceXml && it.xml != null }?.xml } does not get serialized
        assertThat(json.contains("\"invoiceXml\"")).isFalse()
    }


    @Test
    fun extractFromFile_Xml() {
        val readFileResult = reader.extractFromFile(getTestFile("XRechnung.xml"), "XRechnung.xml")

        val json = jacksonObjectMapper.writeValueAsString(readFileResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoiceFileResult>(json)

        assertThat(decoded.filename).isEqualTo(readFileResult.filename)
        assertThat(decoded.readXmlResult).isNotNull()
        assertEquals(readFileResult.readXmlResult!!, decoded.readXmlResult!!)
    }

    @Test
    fun extractFromFile_Pdf() {
        val readFileResult = reader.extractFromFile(getTestFile("ZUGFeRD.pdf"), "ZUGFeRD.pdf")

        val json = jacksonObjectMapper.writeValueAsString(readFileResult)

        val decoded = kotlinxJson.decodeFromString<ReadEInvoiceFileResult>(json)

        assertThat(decoded.filename).isEqualTo(readFileResult.filename)
        assertThat(decoded.readPdfResult).isNotNull()
        assertEquals(readFileResult.readPdfResult, decoded.readPdfResult!!)
    }

    @Test
    fun extractFromFile_Pdf_EnsureJacksonDoesNotSerializeIgnoredDelegates() {
        val readFileResult = reader.extractFromFile(getTestFile("ZUGFeRD.pdf"), "ZUGFeRD.pdf")

        val json = jacksonObjectMapper.writeValueAsString(readFileResult)


        // ensure delegates of ReadEInvoiceFileResult do not get serialized
        assertThat(json.contains("\"mapInvoiceResult\"")).isFalse()
        assertThat(json.countOccurrences("\"invoice\"")).isEqualTo(2)
    }


    @Test
    fun emailAttachment() = runTest {
        val readPdfResult = reader.extractFromPdf(getTestFile("ZUGFeRD.pdf").readAllBytes())
        val attachment = EmailAttachment("ZUGFeRD.pdf", "pdf", 17, ContentDisposition.Attachment, "application/pdf", "application/pdf", readPdfResult?.invoice)

        val json = jacksonObjectMapper.writeValueAsString(attachment)

        val decoded = kotlinxJson.decodeFromString<EmailAttachment>(json)


        assertThat(decoded.filename).isEqualTo(attachment.filename)
        assertThat(decoded.extension).isEqualTo(attachment.extension)
        assertThat(decoded.size).isEqualTo(attachment.size)
        assertThat(decoded.disposition).isEqualTo(attachment.disposition)
        assertThat(decoded.mediaType).isEqualTo(attachment.mediaType)
        assertThat(decoded.contentType).isEqualTo(attachment.contentType)

        InvoiceAsserter.assertInvoice(decoded.mapInvoiceResult)
    }

    @Test
    fun emailAttachment_EnsureJacksonDoesNotSerializeIgnoredDelegates() = runTest {
        val readPdfResult = reader.extractFromPdf(getTestFile("ZUGFeRD.pdf").readAllBytes())
        val attachment = EmailAttachment("ZUGFeRD.pdf", "pdf", 17, ContentDisposition.Attachment, "application/pdf", "application/pdf", readPdfResult?.invoice)

        val json = jacksonObjectMapper.writeValueAsString(attachment)


        // ensure delegates of EmailAttachment do not get serialized
        assertThat(json.contains("\"isPdfFile\"")).isFalse()
        assertThat(json.contains("\"containsEInvoice\"")).isFalse()
        assertThat(json.contains("\"couldExtractPdfInvoiceData\"")).isFalse()
        assertThat(json.countOccurrences("\"invoice\"")).isEqualTo(1)
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
        } else if (expected.invoice!!.invoiceDataErrors.isNotEmpty()) {
            val mapInvoiceResult = decoded.invoice
            assertThat(mapInvoiceResult).isNotNull()

            assertThat(expected.invoice!!.invoiceDataErrors).hasSize(mapInvoiceResult!!.invoiceDataErrors.size)
            assertThat(expected.invoice!!.invoiceDataErrors.map { it.field }).containsExactly(*mapInvoiceResult.invoiceDataErrors.map { it.field }.toTypedArray())
            assertThat(expected.invoice!!.invoiceDataErrors.map { it.errorType }).containsExactly(*mapInvoiceResult.invoiceDataErrors.map { it.errorType }.toTypedArray())
            assertThat(expected.invoice!!.invoiceDataErrors.map { it.erroneousValue }).containsExactly(*mapInvoiceResult.invoiceDataErrors.map { it.erroneousValue }.toTypedArray())

        } else {
            InvoiceAsserter.assertInvoice(decoded.invoice?.invoice)
        }
    }

    private fun assertEquals(expected: ReadEInvoicePdfResult?, decoded: ReadEInvoicePdfResult) {
        assertThat(expected).isNotNull()

        assertThat(decoded.type).isEqualTo(expected!!.type)

        assertEquals(expected.attachmentExtractionResult, decoded.attachmentExtractionResult)

        if (expected.invoice == null) {
            assertThat(decoded.invoice).isNull()
        } else {
            InvoiceAsserter.assertInvoice(decoded.invoice)
        }
    }

    private fun assertEquals(expected: PdfAttachmentExtractionResult, decoded: PdfAttachmentExtractionResult) {
        assertThat(decoded.type).isEqualTo(expected.type)
        assertThat(decoded.attachments).hasSize(expected.attachments.size)

        expected.attachments.forEachIndexed { index, attachment ->
            val decodedAttachment = decoded.attachments[index]
            assertThat(decodedAttachment.filename).isEqualTo(attachment.filename)
            assertThat(decodedAttachment.isXmlFile).isEqualTo(attachment.isXmlFile)
            assertThat(decodedAttachment.isProbablyEN16931InvoiceXml).isEqualTo(attachment.isProbablyEN16931InvoiceXml)
            assertThat(decodedAttachment.xml).isEqualTo(attachment.xml)
        }
    }

}
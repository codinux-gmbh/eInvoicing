package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.test.TestUtils
import java.nio.file.Files
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream
import kotlin.test.Test

class PdfBoxPdfAttachmentReaderTest {

    private val underTest = PdfBoxPdfAttachmentReader()


    @Test
    fun notAPdf() {
        val result = underTest.getFileAttachments(getValidTestFile("ZUGFeRD.xml"))

        assertThat(result.type).isEqualTo(PdfAttachmentExtractionResultType.NotAPdf)
        assertThat(result.attachments).isEmpty()
    }

    @Test
    fun notAValidPdf() {
        val result = underTest.getFileAttachments(getTestFile("NotAValidPdf.pdf"))

        assertThat(result.type).isEqualTo(PdfAttachmentExtractionResultType.NotAPdf)
        assertThat(result.attachments).isEmpty()
    }

    @Test
    fun noAttachments() {
        val result = underTest.getFileAttachments(getTestFile("NoAttachments.pdf"))

        assertThat(result.type).isEqualTo(PdfAttachmentExtractionResultType.NoAttachments)
        assertThat(result.attachments).isEmpty()
    }

    @Test
    fun noXmlAttachments() {
        val result = underTest.getFileAttachments(getTestFile("NoXmlAttachments.pdf"))

        assertThat(result.type).isEqualTo(PdfAttachmentExtractionResultType.NoXmlAttachments)
        assertThat(result.attachments).hasSize(1)

        val attachment = result.attachments.first()
        assertThat(attachment.filename).isEqualTo("empty.txt")
        assertThat(attachment.isXmlFile).isFalse()
        assertThat(attachment.isProbablyEN16931InvoiceXml).isFalse()
        assertThat(attachment.xml).isNull()
    }

    @Test
    fun facturXPdf() {
        val result = underTest.getFileAttachments(getValidTestFile("ZUGFeRD.pdf"))

        assertThat(result.type).isEqualTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(result.attachments).hasSize(1)

        val attachment = result.attachments.first()
        assertThat(attachment.filename).isEqualTo("factur-x.xml")
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.isProbablyEN16931InvoiceXml).isTrue()
        assertThat(attachment.xml).isNotNull()
        assertThat(attachment.xml!!).hasLength(6567)
    }


    private fun getTestFile(filename: String) = TestUtils.getInvalidInvoiceFileAsStream(filename)

    private fun getValidTestFile(filename: String) = TestUtils.getTestFileAsStream(filename)

}
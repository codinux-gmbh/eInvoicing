package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestData
import net.codinux.invoicing.test.TestUtils
import net.codinux.invoicing.validation.EInvoicePdfValidator
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.*
import kotlin.test.Test

class PdfBoxPdfAttachmentWriterTest {

    private val underTest = PdfBoxPdfAttachmentWriter()

    private val reader = PdfBoxPdfAttachmentReader()

    private val pdfValidator = EInvoicePdfValidator()


    @Test
    fun addFileAttachment() {
        val format = EInvoiceFormat.FacturX
        val xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<rsm:CrossIndustryInvoice" // to trick Mustang's simple validation
        val destination = TestUtils.getInvalidInvoiceFile("NoAttachments.pdf").parent.parent.resolve("tmp").also { Files.createDirectories(it) }.resolve("AddAttachmentResult.pdf")
        destination.deleteIfExists()

        underTest.addFileAttachment(getTestFile("NoAttachments.pdf"), format, xmlContent, destination.outputStream())

        val createdFile = reader.getFileAttachments(destination.inputStream())

        assertThat(createdFile.type).isEqualTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(createdFile.attachments).hasSize(1)

        val attachment = createdFile.attachments.first()
        assertThat(attachment.filename).isEqualTo(Constants.getFilenameForFormat(format))
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.xml).isNotNull()
        assertThat(attachment.xml!!).isEqualTo(xmlContent)
    }

    @Test
    fun addFileAttachment_PdfIsValid() {
        val format = EInvoiceFormat.FacturX
        val xmlContent = TestData.FacturXXml
        val pdfBytes = underTest.createEmptyPdfA3()

        val destination = TestUtils.getInvalidInvoiceFile("NoAttachments.pdf").parent.parent.resolve("tmp").also { Files.createDirectories(it) }.resolve("AddAttachmentResult.pdf")
        destination.deleteIfExists()

        underTest.addFileAttachment(pdfBytes, format, xmlContent, destination.outputStream())

        val validationResult = pdfValidator.validate(destination)

        val result = Asserts.assertSuccess(validationResult)
        assertThat(result.isValid).isTrue()
        assertThat(result.validationErrors).isEmpty()
    }


    private fun getTestFile(filename: String) = TestUtils.getInvalidInvoiceFileAsStream(filename)

}
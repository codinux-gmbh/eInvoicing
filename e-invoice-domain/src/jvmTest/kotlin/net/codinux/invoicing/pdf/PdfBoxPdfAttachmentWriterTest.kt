package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import net.codinux.invoicing.test.TestUtils
import java.nio.file.Files
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream
import kotlin.test.Test

class PdfBoxPdfAttachmentWriterTest {

    private val underTest = PdfBoxPdfAttachmentWriter()

    private val reader = PdfBoxPdfAttachmentReader()


    @Test
    fun addFileAttachment() {
        val xmlFilename = "empty.xml"
        val xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        val destination = TestUtils.getInvalidInvoiceFile("NoAttachments.pdf").parent.parent.resolve("tmp").also { Files.createDirectories(it) }.resolve("AddAttachmentResult.pdf")

        underTest.addFileAttachment(getTestFile("NoAttachments.pdf"), xmlFilename, xmlContent, destination.outputStream())

        val createdFile = reader.getFileAttachments(destination.inputStream())

        assertThat(createdFile.type).isEqualTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(createdFile.attachments).hasSize(1)

        val attachment = createdFile.attachments.first()
        assertThat(attachment.filename).isEqualTo(xmlFilename)
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.xml).isNotNull()
        assertThat(attachment.xml!!).isEqualTo(xmlContent)
    }


    private fun getTestFile(filename: String) = TestUtils.getInvalidInvoiceFileAsStream(filename)

}
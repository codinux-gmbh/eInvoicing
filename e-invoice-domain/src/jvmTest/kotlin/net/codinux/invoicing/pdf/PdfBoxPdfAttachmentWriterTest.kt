package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.EInvoiceXmlFormat
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
        val format = EInvoiceXmlFormat.FacturX
        val xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<rsm:CrossIndustryInvoice" // to trick Mustang's simple validation
        val destination = TestUtils.getInvalidInvoiceFile("NoAttachments.pdf").parent.parent.resolve("tmp").also { Files.createDirectories(it) }.resolve("AddAttachmentResult.pdf")

        underTest.addFileAttachment(getTestFile("NoAttachments.pdf"), Constants.getProfileNameForFormat(format), xmlContent, destination.outputStream())

        val createdFile = reader.getFileAttachments(destination.inputStream())

        assertThat(createdFile.type).isEqualTo(PdfAttachmentExtractionResultType.HasXmlAttachments)
        assertThat(createdFile.attachments).hasSize(1)

        val attachment = createdFile.attachments.first()
        assertThat(attachment.filename).isEqualTo(Constants.getFilenameForFormat(format))
        assertThat(attachment.isXmlFile).isTrue()
        assertThat(attachment.xml).isNotNull()
        assertThat(attachment.xml!!).isEqualTo(xmlContent)
    }


    private fun getTestFile(filename: String) = TestUtils.getInvalidInvoiceFileAsStream(filename)

}
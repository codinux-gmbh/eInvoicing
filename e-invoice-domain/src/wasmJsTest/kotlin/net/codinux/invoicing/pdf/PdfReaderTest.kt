package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class PdfReaderTest {

    private val underTest = PdfReader()


    @Test
    fun getFileAttachments_EmptyByteArray() = runTest {
        val result = underTest.readEmbeddedFiles(ByteArray(0))

        assertThat(result).isEmpty()
    }

    @Test
    fun getFileAttachments() = runTest {
        val result = underTest.readEmbeddedFiles(TestData.FacturXPdf)

        assertThat(result).hasSize(1)

        val embeddedFile = result.first()
        assertThat(embeddedFile.filename).isEqualTo("factur-x.xml")
        assertThat(embeddedFile.description).isEqualTo("Invoice metadata conforming to ZUGFeRD standard (http://www.ferd-net.de/front_content.php?idcat=231&lang=4)")
        assertThat(embeddedFile.relationship).isEqualTo("Alternative")
        assertThat(embeddedFile.mimeType).isEqualTo("text/xml")
        assertThat(embeddedFile.size).isEqualTo(6572)
        assertThat(embeddedFile.data.length).isEqualTo(embeddedFile.size)
        assertThat(embeddedFile.creationDate?.getMinutes()).isEqualTo(36)
        assertThat(embeddedFile.modificationDate?.getMinutes()).isEqualTo(36)
    }

}
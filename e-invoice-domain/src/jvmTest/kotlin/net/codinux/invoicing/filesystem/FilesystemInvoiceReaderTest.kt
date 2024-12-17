package net.codinux.invoicing.filesystem

import assertk.assertThat
import assertk.assertions.hasSize
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestUtils
import java.nio.file.Path
import kotlin.test.Test

class FilesystemInvoiceReaderTest {

    private val underTest = FilesystemInvoiceReader()

    @Test
    fun readAllInvoicesOfDirectory() {
        val testDirectory = getTestFile("XRechnung.xml").parent

        val result = underTest.readAllInvoicesOfDirectory(testDirectory)

        assertThat(result).hasSize(3)
    }

    @Test
    fun readInvoiceFromFile() {
        val xRechnung = getTestFile("XRechnung.xml")

        val result = underTest.readInvoiceFromFile(xRechnung)

        assertInvoice(result.invoice)
    }


    private fun getTestFile(filename: String): Path =
        TestUtils.getTestFile(filename)

    private fun assertInvoice(invoice: Invoice?) {
        InvoiceAsserter.assertInvoice(invoice)
    }

}
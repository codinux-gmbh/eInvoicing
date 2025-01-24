package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import net.codinux.invoicing.format.EInvoicingStandard
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.invoicing.testfiles.*
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.inputStream
import kotlin.io.path.name
import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.fail

// TODO: make test-invoices a Kotlin Multiplatform library and move file to commonTest
class EInvoiceXmlReaderTest {

    private val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader

    private val underTest = EInvoiceXmlReader()


    @Test
    fun `Factur-X Version 1-0-7 Profile Minimum`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.Minimum)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile BasicWL`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.BasicWL)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile Basic`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.Basic)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.EN16931)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile Extended`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.Extended)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }


    @Test
    fun `Zugferd Version 2-3-2 Profile Minimum`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, FacturXProfile.Minimum)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @Test
    fun `Zugferd Version 2-3-2 Profile BasicWL`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, FacturXProfile.BasicWL)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @Test
    fun `Zugferd Version 2-3-2 Profile Basic`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, FacturXProfile.Basic)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @Test
    fun `Zugferd Version 2-3-2 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, FacturXProfile.EN16931)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @Test
    fun `Zugferd Version 2-3-2 Profile Extended`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, FacturXProfile.Extended)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }

    @Test
    fun `Zugferd Version 2-3-2 Profile XRechnung`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, FacturXProfile.XRechnung)

        assertCiiFiles(testFiles, EInvoiceFormat.XRechnung, null, null)
    }


    @Test
    fun `Zugferd Version 2-2 Profile Minimum`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.Minimum)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @Test
    fun `Zugferd Version 2-2 Profile BasicWL`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.BasicWL)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @Test
    fun `Zugferd Version 2-2 Profile Basic`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.Basic)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @Test
    fun `Zugferd Version 2-2 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.EN16931)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @Test
    fun `Zugferd Version 2-2 Profile Extended`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.Extended)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }

    @Test
    fun `Zugferd Version 2-2 Profile XRechnung`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.XRechnung)

        assertCiiFiles(testFiles, EInvoiceFormat.XRechnung, null, null)
    }


    @Test
    fun `Zugferd Version 2-1 Profile Minimum`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, FacturXProfile.Minimum)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @Test
    fun `Zugferd Version 2-1 Profile BasicWL`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, FacturXProfile.BasicWL)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @Test
    fun `Zugferd Version 2-1 Profile Basic`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, FacturXProfile.Basic)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @Test
    fun `Zugferd Version 2-1 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, FacturXProfile.EN16931)
            .filter { it.name.contains("_XRechnung") == false } // maintainers have put same XRechnung files into EN16931 folder

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @Test
    fun `Zugferd Version 2-1 Profile EN16931_XRechnung`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, FacturXProfile.EN16931)
            .filter { it.name.contains("_XRechnung") } // maintainers have put same XRechnung files into EN16931 folder

        assertCiiFiles(testFiles, EInvoiceFormat.XRechnung, null, null)
    }

    @Test
    fun `Zugferd Version 2-1 Profile Extended`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, FacturXProfile.Extended)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }


    @Test
    fun `Zugferd Version 2-0 Profile Minimum`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_0, FacturXProfile.Minimum)

        assertCiiFiles(testFiles, EInvoiceFormat.Zugferd, "2", FacturXProfile.Minimum)
    }

    @Test
    fun `Zugferd Version 2-0 Profile Basic`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_0, FacturXProfile.Basic)

        assertCiiFiles(testFiles, EInvoiceFormat.Zugferd, "2", FacturXProfile.Basic)
    }

    @Test
    fun `Zugferd Version 2-0 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_0, FacturXProfile.EN16931)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @Test
    fun `Zugferd Version 2-0 Profile Extended`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_0, FacturXProfile.Extended)

        assertCiiFiles(testFiles, EInvoiceFormat.Zugferd, "2", FacturXProfile.Extended)
    }


    @Test
    fun `XRechnung CII`() {
        val testFiles = EInvoiceTestFiles.getXRechnungTestFiles(EInvoiceXmlFlavour.CII)

        assertFiles(testFiles, EInvoicingStandard.CII, EInvoiceFormat.XRechnung)
    }

    @Test
    fun `XRechnung UBL`() {
        val testFiles = EInvoiceTestFiles.getXRechnungTestFiles(EInvoiceXmlFlavour.UBL)

        testFiles.forEach { testFile ->
            val invoiceXml = getInvoiceXml(testFile)
            if (invoiceXml == null) {
                fail("Could not get invoice XML for test file: $testFile")
            } else {
                val result = underTest.parseInvoiceXml(invoiceXml)

                assertThat(result).isNull() // UBL is not supported (yet)
            }
        }
    }


    private fun assertCiiFiles(testFiles: List<Path>, format: EInvoiceFormat, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertFiles(testFiles, EInvoicingStandard.CII, format, formatVersion, profile)
    }

    private fun assertFiles(testFiles: List<Path>, standard: EInvoicingStandard, format: EInvoiceFormat? = null, formatVersion: String? = null, profile: FacturXProfile? = null) {
        testFiles.forEach { testFile ->
            val invoiceXml = getInvoiceXml(testFile)
            if (invoiceXml == null) {
                fail("Could not get invoice XML for test file: $testFile")
            } else {
                val result = underTest.parseInvoiceXml(invoiceXml)

                assertThat(result).isNotNull()
                assertThat(result!!.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)
                assertThat(result.readError).isNull()
                assertThat(result.invoice).isNotNull()
                assertThat(result.invoice!!.invoiceDataErrors).isEmpty()

//                assertThat(result!!.standard).isEqualTo(standard)
//                assertThat(result.format).isEqualTo(format)
//
//                if (formatVersion != null) { // version may be stated by not tested, e.g. for XRechnung it can be any value of "1.2", "2.1" or "3.0"
//                    assertThat(result.formatVersion).isEqualTo(formatVersion)
//                }
//
//                if (profile == null) {
//                    assertThat(result.profile).isNull()
//                } else {
//                    assertThat(result.profile).isNotNull().isEqualByComparingTo(profile)
//                }
            }
        }
    }

    private fun getInvoiceXml(file: Path): String? =
        // TODO: improve file detection
        if (file.extension.lowercase() == "pdf") {
            pdfAttachmentReader.getFileAttachments(file.inputStream()).invoiceXml
        } else if (file.extension.lowercase() == "xml") {
            file.readText()
        } else {
            null
        }

    private fun getTestFiles(format: EInvoiceFormat, version: EInvoiceFormatVersion, profile: FacturXProfile? = null): List<Path> =
        EInvoiceTestFiles.getTestFiles(net.codinux.invoicing.testfiles.EInvoiceFormat.valueOf(format.name), version, profile?.let { EInvoiceProfile.valueOf(it.name) })

}
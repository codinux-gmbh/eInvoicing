package net.codinux.invoicing.format

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.invoicing.test.JavaTestPlatform
import net.codinux.invoicing.test.TestUtils
import net.codinux.invoicing.testfiles.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.inputStream
import kotlin.io.path.name
import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.fail

class EInvoiceFormatDetectorTest { // TODO: use TestUtils to get invoice files (also sets up JavaTestPlatform.initTestEnvironment() then)

    private val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader

    private val underTest = EInvoiceFormatDetector()


    @ParameterizedTest
    @MethodSource("provideFacturXMinimumProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Minimum`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXBasicWLProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile BasicWL`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXBasicProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Basic`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXEN16931ProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile EN16931`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXExtendedProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Extended`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_MinimumProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Minimum`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_BasicWLProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile BasicWL`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_BasicProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Basic`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_EN16931ProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile EN16931`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_ExtendedProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Extended`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_XRechnungProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile XRechnung`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.XRechnung, null, null)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_MinimumProfileInvoices")
    fun `Zugferd Version 2-2 Profile Minimum`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_BasicWLProfileInvoices")
    fun `Zugferd Version 2-2 Profile BasicWL`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_BasicProfileInvoices")
    fun `Zugferd Version 2-2 Profile Basic`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_EN16931ProfileInvoices")
    fun `Zugferd Version 2-2 Profile EN16931`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_ExtendedProfileInvoices")
    fun `Zugferd Version 2-2 Profile Extended`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_XRechnungProfileInvoices")
    fun `Zugferd Version 2-2 Profile XRechnung`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.XRechnung, null, null)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_MinimumProfileInvoices")
    fun `Zugferd Version 2-1 Profile Minimum`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_BasicWLProfileInvoices")
    fun `Zugferd Version 2-1 Profile BasicWL`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_BasicProfileInvoices")
    fun `Zugferd Version 2-1 Profile Basic`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_EN16931ProfileNonXRechnungInvoices")
    fun `Zugferd Version 2-1 Profile EN16931`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_EN16931ProfileXRechnungInvoices")
    fun `Zugferd Version 2-1 Profile EN16931_XRechnung`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.XRechnung, null, null)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_ExtendedProfileInvoices")
    fun `Zugferd Version 2-1 Profile Extended`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_MinimumProfileInvoices")
    fun `Zugferd Version 2-0 Profile Minimum`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.Zugferd, "2", FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_BasicProfileInvoices")
    fun `Zugferd Version 2-0 Profile Basic`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.Zugferd, "2", FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_EN16931ProfileInvoices")
    fun `Zugferd Version 2-0 Profile EN16931`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_ExtendedProfileInvoices")
    fun `Zugferd Version 2-0 Profile Extended`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.Zugferd, "2", FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("provideXRechnungCiiInvoices")
    fun `XRechnung CII`(invoiceFile: Path) {
        assertCiiFile(invoiceFile, EInvoiceFormat.XRechnung, null, null)
    }

    @ParameterizedTest
    @MethodSource("provideXRechnungUblInvoices")
    fun `XRechnung UBL`(invoiceFile: Path) {
        assertFile(invoiceFile, EInvoicingStandard.UBL, EInvoiceFormat.XRechnung, null, null)
    }


    private fun assertCiiFiles(testFiles: List<Path>, format: EInvoiceFormat, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertFiles(testFiles, EInvoicingStandard.CII, format, formatVersion, profile)
    }

    private fun assertCiiFile(testFile: Path, format: EInvoiceFormat, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertFile(testFile, EInvoicingStandard.CII, format, formatVersion, profile)
    }

    private fun assertFiles(testFiles: List<Path>, standard: EInvoicingStandard, format: EInvoiceFormat? = null, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertThat(testFiles).isNotEmpty()

        testFiles.forEach { testFile ->
            assertFile(testFile, standard, format, formatVersion, profile)
        }
    }

    private fun assertFile(testFile: Path, standard: EInvoicingStandard, format: EInvoiceFormat?, formatVersion: String?, profile: FacturXProfile?) {
        val invoiceXml = getInvoiceXml(testFile)
        if (invoiceXml == null) {
            fail("Could not get invoice XML for test file: $testFile")
        } else {
            val result = underTest.detectFormat(invoiceXml)

            assertThat(result).isNotNull()
            assertThat(result!!.standard).isEqualTo(standard)
            assertThat(result.format).isEqualTo(format)

            if (formatVersion != null) { // version may be stated by not tested, e.g. for XRechnung it can be any value of "1.2", "2.1" or "3.0"
                assertThat(result.formatVersion).isEqualTo(formatVersion)
            }

            if (profile == null) {
                assertThat(result.profile).isNull()
            } else {
                assertThat(result.profile).isNotNull().isEqualByComparingTo(profile)
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


    companion object {

        init {
            JavaTestPlatform.initTestEnvironment()
        }


        @JvmStatic
        fun provideFacturXMinimumProfileInvoices() = TestUtils.FacturXMinimumProfileInvoices

        @JvmStatic
        fun provideFacturXBasicWLProfileInvoices() = TestUtils.FacturXBasicWLProfileInvoices

        @JvmStatic
        fun provideFacturXBasicProfileInvoices() = TestUtils.FacturXBasicProfileInvoices

        @JvmStatic
        fun provideFacturXEN16931ProfileInvoices() = TestUtils.FacturXEN16931ProfileInvoices

        @JvmStatic
        fun provideFacturXExtendedProfileInvoices() = TestUtils.FacturXExtendedProfileInvoices


        @JvmStatic
        fun provideZugferd_2_3_MinimumProfileInvoices() = TestUtils.Zugferd_2_3_MinimumProfileInvoices

        @JvmStatic
        fun provideZugferd_2_3_BasicWLProfileInvoices() = TestUtils.Zugferd_2_3_BasicWLProfileInvoices

        @JvmStatic
        fun provideZugferd_2_3_BasicProfileInvoices() = TestUtils.Zugferd_2_3_BasicProfileInvoices

        @JvmStatic
        fun provideZugferd_2_3_EN16931ProfileInvoices() = TestUtils.Zugferd_2_3_EN16931ProfileInvoices

        @JvmStatic
        fun provideZugferd_2_3_ExtendedProfileInvoices() = TestUtils.Zugferd_2_3_ExtendedProfileInvoices

        @JvmStatic
        fun provideZugferd_2_3_XRechnungProfileInvoices() = TestUtils.Zugferd_2_3_XRechnungProfileInvoices


        @JvmStatic
        fun provideZugferd_2_2_MinimumProfileInvoices() = TestUtils.Zugferd_2_2_MinimumProfileInvoices

        @JvmStatic
        fun provideZugferd_2_2_BasicWLProfileInvoices() = TestUtils.Zugferd_2_2_BasicWLProfileInvoices

        @JvmStatic
        fun provideZugferd_2_2_BasicProfileInvoices() = TestUtils.Zugferd_2_2_BasicProfileInvoices

        @JvmStatic
        fun provideZugferd_2_2_EN16931ProfileInvoices() = TestUtils.Zugferd_2_2_EN16931ProfileInvoices

        @JvmStatic
        fun provideZugferd_2_2_ExtendedProfileInvoices() = TestUtils.Zugferd_2_2_ExtendedProfileInvoices

        @JvmStatic
        fun provideZugferd_2_2_XRechnungProfileInvoices() = TestUtils.Zugferd_2_2_XRechnungProfileInvoices


        @JvmStatic
        fun provideZugferd_2_1_MinimumProfileInvoices() = TestUtils.Zugferd_2_1_MinimumProfileInvoices

        @JvmStatic
        fun provideZugferd_2_1_BasicWLProfileInvoices() = TestUtils.Zugferd_2_1_BasicWLProfileInvoices

        @JvmStatic
        fun provideZugferd_2_1_BasicProfileInvoices() = TestUtils.Zugferd_2_1_BasicProfileInvoices

        @JvmStatic
        fun provideZugferd_2_1_EN16931ProfileNonXRechnungInvoices() = TestUtils.Zugferd_2_1_EN16931ProfileInvoices
            .filter { it.name.contains("_XRechnung") == false } // maintainers have put same XRechnung files into EN16931 folder

        @JvmStatic
        fun provideZugferd_2_1_EN16931ProfileXRechnungInvoices() = TestUtils.Zugferd_2_1_EN16931ProfileInvoices
            .filter { it.name.contains("_XRechnung") } // maintainers have put same XRechnung files into EN16931 folder

        @JvmStatic
        fun provideZugferd_2_1_ExtendedProfileInvoices() = TestUtils.Zugferd_2_1_ExtendedProfileInvoices


        @JvmStatic
        fun provideZugferd_2_0_MinimumProfileInvoices() = TestUtils.Zugferd_2_0_MinimumProfileInvoices

        @JvmStatic
        fun provideZugferd_2_0_BasicProfileInvoices() = TestUtils.Zugferd_2_0_BasicProfileInvoices

        @JvmStatic
        fun provideZugferd_2_0_EN16931ProfileInvoices() = TestUtils.Zugferd_2_0_EN16931ProfileInvoices

        @JvmStatic
        fun provideZugferd_2_0_ExtendedProfileInvoices() = TestUtils.Zugferd_2_0_ExtendedProfileInvoices


        @JvmStatic
        fun provideXRechnungCiiInvoices() = TestUtils.XRechnungCiiInvoices

        @JvmStatic
        fun provideXRechnungUblInvoices() = TestUtils.XRechnungUblInvoices

    }

}
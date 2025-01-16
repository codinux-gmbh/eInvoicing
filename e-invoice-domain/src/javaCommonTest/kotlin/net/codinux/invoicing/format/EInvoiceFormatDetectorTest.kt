package net.codinux.invoicing.format

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import net.codinux.invoicing.testfiles.*
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.test.Test

class EInvoiceFormatDetectorTest {

    private val underTest = EInvoiceFormatDetector()


    @Test
    fun `Factur-X Version 1-0-7 Profile Minimum`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.Minimum)

        assertFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile BasicWL`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.BasicWL)

        assertFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile Basic`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.Basic)

        assertFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.EN16931)

        assertFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @Test
    fun `Factur-X Version 1-0-7 Profile Extended`() {
        val testFiles = getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, FacturXProfile.Extended)

        assertFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)
    }


    private fun assertFiles(testFiles: List<Path>, format: EInvoiceFormat, formatVersion: String? = null, profile: FacturXProfile? = null) {
        testFiles.forEach { testFile ->
            val result = underTest.detectFormat(testFile)

            assertThat(result).isNotNull()
            assertThat(result!!.format).isEqualTo(format)
            assertThat(result.formatVersion).isEqualTo(formatVersion)

            if (profile == null) {
                assertThat(result.profile).isNull()
            } else {
                assertThat(result.profile).isNotNull().isEqualByComparingTo(profile)
            }
        }
    }

    private fun getTestFiles(format: EInvoiceFormat, version: EInvoiceFormatVersion, profile: FacturXProfile? = null): List<Path> =
        EInvoiceTestFiles.getTestFiles(net.codinux.invoicing.testfiles.EInvoiceFormat.valueOf(format.name), version, profile?.let { EInvoiceProfile.valueOf(it.name) })

}
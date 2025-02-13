package net.codinux.invoicing.validation

import assertk.assertThat
import assertk.assertions.*
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.creation.EInvoicePdfCreator
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.TestData
import net.codinux.invoicing.test.TestUtils
import net.codinux.invoicing.testfiles.EInvoiceFormat
import net.codinux.invoicing.testfiles.EInvoiceProfile
import net.codinux.invoicing.testfiles.EInvoiceTestFiles
import net.codinux.invoicing.testfiles.ZugferdVersion
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import kotlin.io.path.extension
import kotlin.io.path.name
import kotlin.test.Test

class EInvoicePdfValidatorTest {

    private val underTest = EInvoicePdfValidator()


    @Test
    fun notAPdfA3() {
        val validationResult = underTest.validate(TestUtils.getInvalidInvoiceFile("PDF-A-1.pdf"))

        val result = Asserts.assertSuccess(validationResult)
        assertThat(result.isValid).isFalse()
        assertThat(result.isPdfA).isTrue()
        assertThat(result.isPdfA3).isFalse()
        assertThat(result.pdfAFlavor).isEqualByComparingTo(PdfAFlavour.PDFA_1_B)
        assertThat(result.countExecutedTests).isGreaterThan(16_000)
        assertThat(result.validationErrors).hasSize(3)
    }

    @Test
    fun invalidPdfA3() {
        val invalidZugferdInvoice = EInvoiceTestFiles.getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_0, EInvoiceProfile.EN16931)
            .first { it.name == "zugferd_2p0_EN16931_Reisekostenabrechnung.pdf" } // this single file is invalid

        val validationResult = underTest.validate(invalidZugferdInvoice)

        val result = Asserts.assertSuccess(validationResult)
        assertThat(result.isValid).isFalse()
        assertThat(result.isPdfA).isTrue()
        assertThat(result.isPdfA3).isTrue()
        assertThat(result.pdfAFlavor).isEqualByComparingTo(PdfAFlavour.PDFA_3_B)
        assertThat(result.countExecutedTests).isGreaterThan(3_000)
        assertThat(result.validationErrors).hasSize(2)
    }

    @Test
    fun validateSelfCreatedPdf() = runTest {
        val pdfCreator = EInvoicePdfCreator()
        val pdfResult = pdfCreator.createFacturXPdf(DataGenerator.createInvoice())
        val pdfBytes = Asserts.assertSuccess(pdfResult).bytes
        
        val validationResult = underTest.validateEInvoicePdf(pdfBytes)

        val result = Asserts.assertSuccess(validationResult)
        assertThat(result.isValid).isTrue()
        assertThat(result.isPdfA).isTrue()
        assertThat(result.isPdfA3).isTrue()
        assertThat(result.pdfAFlavor).isEqualByComparingTo(PdfAFlavour.PDFA_3_A)
        assertThat(result.countExecutedTests).isGreaterThan(3_000)
        assertThat(result.validationErrors).isEmpty()
    }

    @Test
    fun validPdfA3() {
        val validationResult = underTest.validate(TestData.FacturXPdf)

        val result = Asserts.assertSuccess(validationResult)
        assertThat(result.isValid).isTrue()
        assertThat(result.isPdfA).isTrue()
        assertThat(result.isPdfA3).isTrue()
        assertThat(result.pdfAFlavor).isEqualByComparingTo(PdfAFlavour.PDFA_3_U)
        assertThat(result.countExecutedTests).isGreaterThan(19_000)
        assertThat(result.validationErrors).isEmpty()
    }

    @ParameterizedTest
    @MethodSource("facturXInvoices")
    fun validateFacturXInvoices_AllValid(pdfFile: Path) {
        val result = underTest.validate(pdfFile)

        assertIsValidPdfA3B(result)
    }

    @ParameterizedTest
    @MethodSource("zugferdInvoices")
    fun validateZugferdInvoices_AllValid(pdfFile: Path) {
        val result = underTest.validate(pdfFile)

        assertIsValidPdfA3(result)
    }

    private fun assertIsValidPdfA3(validationResult: Result<PdfValidationResult>) {
        val result = Asserts.assertSuccess(validationResult)

        assertThat(result.isValid).isTrue()
        assertThat(result.isPdfA).isTrue()
        assertThat(result.isPdfA3).isTrue()
        assertThat(result.pdfAFlavor).isIn(PdfAFlavour.PDFA_3_A, PdfAFlavour.PDFA_3_B)
        assertThat(result.countExecutedTests).isGreaterThan(2_000)
        assertThat(result.validationErrors).isEmpty()
    }

    private fun assertIsValidPdfA3B(validationResult: Result<PdfValidationResult>) {
        val result = Asserts.assertSuccess(validationResult)

        assertThat(result.isValid).isTrue()
        assertThat(result.isPdfA).isTrue()
        assertThat(result.isPdfA3).isTrue()
        assertThat(result.pdfAFlavor).isIn(PdfAFlavour.PDFA_3_B)
        assertThat(result.countExecutedTests).isGreaterThan(16_000)
        assertThat(result.validationErrors).isEmpty()
    }

    companion object {

        @JvmStatic
        fun facturXInvoices() = TestUtils.FacturXInvoices
            .filter { it.payload.extension.lowercase() == "pdf" }

        @JvmStatic
        fun zugferdInvoices() = TestUtils.ZugferdInvoices
            .filter { it.payload.extension.lowercase() == "pdf" }
            .filter { it.name != "zugferd_2p0_EN16931_Reisekostenabrechnung.pdf" } // this single file is invalid

    }

}
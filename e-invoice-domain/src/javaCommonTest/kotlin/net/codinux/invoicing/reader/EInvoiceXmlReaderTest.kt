package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.format.EInvoicingStandard
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.format.FacturXProfile.Companion.isNotMinimum
import net.codinux.invoicing.format.FacturXProfile.Companion.isNotMinimumOrBasicWL
import net.codinux.invoicing.model.Party
import net.codinux.invoicing.model.mapper.MapperConstants
import net.codinux.invoicing.test.TestUtils
import net.codinux.invoicing.testfiles.*
import net.codinux.log.logger
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.test.fail

// TODO: make test-invoices a Kotlin Multiplatform library and move file to commonTest
class EInvoiceXmlReaderTest {

    private val underTest = EInvoiceXmlReader()

    private val log by logger()


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideFacturXMinimumProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Minimum`(invoiceFile: Path) {
        assertFacturXFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideFacturXBasicWLProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile BasicWL`(invoiceFile: Path) {
        assertFacturXFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideFacturXBasicProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Basic`(invoiceFile: Path) {
        assertFacturXFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideFacturXEN16931ProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile EN16931`(invoiceFile: Path) {
        assertFacturXFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideFacturXExtendedProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Extended`(invoiceFile: Path) {
        assertFacturXFile(invoiceFile, FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_3_MinimumProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Minimum`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_3_BasicWLProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile BasicWL`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_3_BasicProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Basic`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_3_EN16931ProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile EN16931`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_3_ExtendedProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Extended`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Extended)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_3_XRechnungProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile XRechnung`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.XRechnung)
    }


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_2_MinimumProfileInvoices")
    fun `Zugferd Version 2-2 Profile Minimum`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_2_BasicWLProfileInvoices")
    fun `Zugferd Version 2-2 Profile BasicWL`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_2_BasicProfileInvoices")
    fun `Zugferd Version 2-2 Profile Basic`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_2_EN16931ProfileInvoices")
    fun `Zugferd Version 2-2 Profile EN16931`() {
        val testFiles = getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_2, FacturXProfile.EN16931)

        assertCiiFiles(testFiles, EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_2_ExtendedProfileInvoices")
    fun `Zugferd Version 2-2 Profile Extended`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Extended)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_2_XRechnungProfileInvoices")
    fun `Zugferd Version 2-2 Profile XRechnung`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.XRechnung)
    }


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_1_MinimumProfileInvoices")
    fun `Zugferd Version 2-1 Profile Minimum`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_1_BasicWLProfileInvoices")
    fun `Zugferd Version 2-1 Profile BasicWL`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_1_BasicProfileInvoices")
    fun `Zugferd Version 2-1 Profile Basic`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_1_EN16931ProfileNonXRechnungInvoices")
    fun `Zugferd Version 2-1 Profile EN16931`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_1_EN16931ProfileXRechnungInvoices")
    fun `Zugferd Version 2-1 Profile EN16931_XRechnung`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.XRechnung)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_1_ExtendedProfileInvoices")
    fun `Zugferd Version 2-1 Profile Extended`(invoiceFile: Path) {
        assertZugferdFile(invoiceFile, FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_0_MinimumProfileInvoices")
    fun `Zugferd Version 2-0 Profile Minimum`(invoiceFile: Path) {
        assertOldZugferdFile(invoiceFile, "2", FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_0_BasicProfileInvoices")
    fun `Zugferd Version 2-0 Profile Basic`(invoiceFile: Path) {
        assertOldZugferdFile(invoiceFile, "2", FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_0_EN16931ProfileInvoices")
    fun `Zugferd Version 2-0 Profile EN16931`(invoiceFile: Path) {
        assertOldZugferdFile(invoiceFile, "2", FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideZugferd_2_0_ExtendedProfileInvoices")
    fun `Zugferd Version 2-0 Profile Extended`(invoiceFile: Path) {
        assertOldZugferdFile(invoiceFile, "2", FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideXRechnungCiiInvoices")
    fun `XRechnung CII`(invoiceFile: Path) {
        assertFile(invoiceFile, EInvoicingStandard.CII, EInvoiceFormat.XRechnung,
            areAmountsAllowedToBeZero = invoiceFile.name in listOf("02.04a-INVOICE_uncefact.xml", "02.05a-INVOICE_uncefact.xml"))
    }

    @ParameterizedTest
    @MethodSource("net.codinux.invoicing.test.TestUtils#provideXRechnungUblInvoices")
    fun `XRechnung UBL`(invoiceFile: Path) {
        assertFile(invoiceFile, EInvoicingStandard.UBL, EInvoiceFormat.XRechnung)
    }


    private fun assertCiiFiles(invoiceFile: List<Path>, format: EInvoiceFormat, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertFiles(invoiceFile, EInvoicingStandard.CII, format, formatVersion, profile)
    }

    private fun assertFiles(invoiceFiles: List<Path>, standard: EInvoicingStandard, format: EInvoiceFormat? = null, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertThat(invoiceFiles).isNotEmpty()

        invoiceFiles.forEach { invoiceFile ->
            assertFile(invoiceFile, standard, format, formatVersion, profile)
        }
    }

    private fun assertFacturXFile(invoiceFile: Path, profile: FacturXProfile? = null) {
        assertCiiFile(invoiceFile, EInvoiceFormat.FacturX, "1", profile)
    }

    private fun assertZugferdFile(invoiceFile: Path, profile: FacturXProfile? = null) {
        assertFacturXFile(invoiceFile, profile)
    }

    private fun assertOldZugferdFile(invoiceFile: Path, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertCiiFile(invoiceFile, EInvoiceFormat.Zugferd, formatVersion, profile)
    }

    private fun assertCiiFile(invoiceFile: Path, format: EInvoiceFormat? = null, formatVersion: String? = null, profile: FacturXProfile? = null) {
        assertFile(invoiceFile, EInvoicingStandard.CII, format, formatVersion, profile)
    }

    private fun assertFile(invoiceFile: Path, standard: EInvoicingStandard, format: EInvoiceFormat? = null, formatVersion: String? = null, profile: FacturXProfile? = null, areAmountsAllowedToBeZero: Boolean = false) {
        val invoiceXml = getInvoiceXml(invoiceFile)
        if (invoiceXml == null) {
            fail("Could not get invoice XML for test file: $invoiceFile")
        } else {
            val result = underTest.parseInvoiceXml(invoiceXml)

            assertThat(result).isNotNull()
            if (result.invoice?.invoiceDataErrors.isNullOrEmpty() == false) {
                log.info { "The following invoice fields are erroneous:" }
                result.invoice!!.invoiceDataErrors.forEach { log.info { "${it.field}: ${it.errorType}" } }
            }
            assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)
            assertThat(result.readError).isNull()
            assertThat(result.invoice).isNotNull()
            assertThat(result.invoice!!.invoiceDataErrors).isEmpty()

            assertThat(result.invoice!!.invoice).isNotNull()
            val invoice = result.invoice!!.invoice

            assertThat(invoice.details).isNotNull()
            val details = invoice.details
            assertThat(details.invoiceNumber).isNotEqualTo(MapperConstants.IdFallbackValue)
            assertThat(details.invoiceDate).isNotEqualTo(MapperConstants.LocalDateFallbackValue)
            assertThat(details.currency).isNotEqualTo(MapperConstants.CurrencyFallbackValue)

            assertParty(invoice.supplier, profile)
            assertParty(invoice.customer, profile)

            if (profile.isNotMinimumOrBasicWL) {
                assertThat(invoice.items).isNotEmpty()

                invoice.items.forEach { item ->
                    assertThat(item.name).isNotEqualTo(MapperConstants.TextFallbackValue)
                    assertThat(item.quantity).isNotEqualTo(MapperConstants.BigDecimalFallbackValue)
                    assertThat(item.unit).isNotEqualTo(MapperConstants.TextFallbackValue)

                    val isItemWithoutPrice = item.description == "Artikel wie vereinbart ohne Berechnung" // there are some items in test invoices without a price
                            || item.name == "Lebensgefährte/in zur Privathaftpflicht"
                    if (areAmountsAllowedToBeZero == false && isItemWithoutPrice != true) {
                        assertThat(item.unitPrice).isNotEqualTo(MapperConstants.BigDecimalFallbackValue)
                    }
                    // vatRate may be zero
                }
            }

            assertThat(invoice.totals).isNotNull()
            val totals = invoice.totals!!
            if (areAmountsAllowedToBeZero == false) {
                if (profile.isNotMinimumOrBasicWL) {
                    assertThat(totals.lineTotalAmount).isNotEqualTo(MapperConstants.BigDecimalFallbackValue)
                }
                assertThat(totals.taxBasisTotalAmount).isNotEqualTo(MapperConstants.BigDecimalFallbackValue)
                assertThat(totals.grandTotalAmount).isNotEqualTo(MapperConstants.BigDecimalFallbackValue)
                if (totals.totalPrepaidAmount != totals.grandTotalAmount) { // if the whole amount has been prepaid, than duePayableAmount is zero
                    assertThat(totals.duePayableAmount).isNotEqualTo(MapperConstants.BigDecimalFallbackValue)
                }
            }

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

    private fun assertParty(party: Party?, profile: FacturXProfile?) {
        assertThat(party).isNotNull()

        assertThat(party!!.name).isNotEqualTo(MapperConstants.TextFallbackValue)
        if (profile.isNotMinimum) { // in Minimum profile TradeParty has no countryId
            assertThat(party.country).isNotEqualTo(MapperConstants.CountryFallbackValue)
        }
    }

    private fun getInvoiceXml(file: Path) = TestUtils.getInvoiceXml(file)

    private fun getTestFiles(format: EInvoiceFormat, version: EInvoiceFormatVersion, profile: FacturXProfile? = null): List<Path> =
        EInvoiceTestFiles.getTestFiles(net.codinux.invoicing.testfiles.EInvoiceFormat.valueOf(format.name), version, profile?.let { EInvoiceProfile.valueOf(it.name) })

}
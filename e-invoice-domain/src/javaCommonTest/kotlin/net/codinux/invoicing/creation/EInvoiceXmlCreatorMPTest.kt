package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.format.FacturXProfile.Companion.isNotMinimumOrBasicWL
import net.codinux.invoicing.model.Party
import net.codinux.invoicing.reader.EInvoiceXmlReader
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.TestUtils
import net.codinux.invoicing.testfiles.EInvoiceFormat
import net.codinux.invoicing.testfiles.EInvoiceProfile
import net.codinux.invoicing.testfiles.EInvoiceTestFiles
import net.codinux.invoicing.testfiles.ZugferdVersion
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.test.Test
import kotlin.test.fail

class EInvoiceXmlCreatorMPTest {

    private val xmlReader = EInvoiceXmlReader()

    private val underTest = EInvoiceXmlCreatorMP()


    @ParameterizedTest
    @MethodSource("provideFacturXMinimumProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Minimum`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXBasicWLProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile BasicWL`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXBasicProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Basic`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXEN16931ProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile EN16931`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideFacturXExtendedProfileInvoices")
    fun `Factur-X Version 1-0-7 Profile Extended`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_MinimumProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Minimum`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_BasicWLProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile BasicWL`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_BasicProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Basic`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_EN16931ProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile EN16931`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_ExtendedProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile Extended`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Extended)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_3_XRechnungProfileInvoices")
    fun `Zugferd Version 2-3-2 Profile XRechnung`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.XRechnung)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_MinimumProfileInvoices")
    fun `Zugferd Version 2-2 Profile Minimum`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_BasicWLProfileInvoices")
    fun `Zugferd Version 2-2 Profile BasicWL`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_BasicProfileInvoices")
    fun `Zugferd Version 2-2 Profile Basic`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_EN16931ProfileInvoices")
    fun `Zugferd Version 2-2 Profile EN16931`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_ExtendedProfileInvoices")
    fun `Zugferd Version 2-2 Profile Extended`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Extended)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_2_XRechnungProfileInvoices")
    fun `Zugferd Version 2-2 Profile XRechnung`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.XRechnung)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_MinimumProfileInvoices")
    fun `Zugferd Version 2-1 Profile Minimum`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_BasicWLProfileInvoices")
    fun `Zugferd Version 2-1 Profile BasicWL`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.BasicWL)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_BasicProfileInvoices")
    fun `Zugferd Version 2-1 Profile Basic`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_EN16931ProfileInvoices")
    fun `Zugferd Version 2-1 Profile EN16931`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.EN16931)
    }

    @Test
    fun `Zugferd Version 2-1 Profile EN16931_XRechnung`() {
        val testFiles = EInvoiceTestFiles.getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_1, EInvoiceProfile.EN16931)
            .filter { it.name.contains("_XRechnung") } // maintainers have put same XRechnung files into EN16931 folder

        testFiles.forEach { invoiceFile ->
            assertInvoiceFile(invoiceFile, FacturXProfile.XRechnung)
        }
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_1_ExtendedProfileInvoices")
    fun `Zugferd Version 2-1 Profile Extended`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_MinimumProfileInvoices")
    fun `Zugferd Version 2-0 Profile Minimum`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Minimum)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_BasicProfileInvoices")
    fun `Zugferd Version 2-0 Profile Basic`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Basic)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_EN16931ProfileInvoices")
    fun `Zugferd Version 2-0 Profile EN16931`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.EN16931)
    }

    @ParameterizedTest
    @MethodSource("provideZugferd_2_0_ExtendedProfileInvoices")
    fun `Zugferd Version 2-0 Profile Extended`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.Extended)
    }


    @ParameterizedTest
    @MethodSource("provideXRechnungCiiInvoices")
    fun `XRechnung CII`(invoiceFile: Path) {
        assertInvoiceFile(invoiceFile, FacturXProfile.XRechnung)
    }

//    @ParameterizedTest
//    @MethodSource("provideXRechnungUblInvoices")
//    fun `XRechnung UBL`(invoiceFile: Path) {
//        val invoiceXml = getInvoiceXml(invoiceFile)
//        if (invoiceXml == null) {
//            fail("Could not get invoice XML for test file: $invoiceFile")
//        } else {
//            val result = underTest.parseInvoiceXml(invoiceXml)
//
//            assertThat(result).isNotNull()
//            assertThat(result.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.UnsupportedInvoiceFormat) // UBL is not supported (yet)
//        }
//    }


    private fun assertInvoiceFile(invoiceFile: Path, profile: FacturXProfile? = null) {
        val invoiceXml = getInvoiceXml(invoiceFile)
        if (invoiceXml == null) {
            fail("Could not get invoice XML for test file: $invoiceFile")
        } else {
            val readResult = xmlReader.parseInvoiceXml(invoiceXml)
            val originalInvoice = readResult.invoice?.invoice
            if (originalInvoice == null) {
                fail("Could not read invoice from invoiceXml: ${readResult.type} ${readResult.readError ?: readResult.invoice?.invoiceDataErrors}")
            }

            val result = underTest.createFacturXXml(originalInvoice)
            val resultXml = Asserts.assertSuccess(result)

            // it's impossible to compare the XMLs directly due to different XML Decl, whitespaces and the original has more elements like notes
            // -> deserialize created XML and compare Invoice objects
            val invoiceFromCreatedXml = xmlReader.parseInvoiceXml(resultXml)

            // we cannot assert ReadEInvoiceXmlResultType as also invoices from Minimum and BasicWL profile get written
            // with EN16931 profile, so the result indicates invalid data as they don't contain line items
            if (profile.isNotMinimumOrBasicWL) {
                assertThat(invoiceFromCreatedXml.type).isEqualByComparingTo(ReadEInvoiceXmlResultType.Success)
            }
            assertThat(invoiceFromCreatedXml.invoice).isNotNull()
            assertThat(invoiceFromCreatedXml.invoice?.invoice).isNotNull()
            val invoice = invoiceFromCreatedXml.invoice!!.invoice

            assertThat(invoice.details.invoiceNumber).isEqualTo(originalInvoice.details.invoiceNumber)
            assertThat(invoice.details.invoiceDate).isEqualTo(originalInvoice.details.invoiceDate)
            assertThat(invoice.details.currency).isEqualTo(originalInvoice.details.currency)
            assertThat(invoice.details.dueDate).isEqualTo(originalInvoice.details.dueDate)

            assertThat(invoice.customerReferenceNumber).isEqualTo(originalInvoice.customerReferenceNumber)

            assertParty(invoice.supplier, originalInvoice.supplier)
            assertParty(invoice.customer, originalInvoice.customer)

            assertThat(invoice.amountAdjustments?.prepaidAmounts).isEqualTo(originalInvoice.amountAdjustments?.prepaidAmounts)

            assertThat(invoice.totals?.lineTotalAmount).isEqualTo(originalInvoice.totals?.lineTotalAmount)
            assertThat(invoice.totals?.chargeTotalAmount).isEqualTo(originalInvoice.totals?.chargeTotalAmount)
            assertThat(invoice.totals?.allowanceTotalAmount).isEqualTo(originalInvoice.totals?.allowanceTotalAmount)
            assertThat(invoice.totals?.taxBasisTotalAmount).isEqualTo(originalInvoice.totals?.taxBasisTotalAmount)
            assertThat(invoice.totals?.taxTotalAmount).isEqualTo(originalInvoice.totals?.taxTotalAmount)
            assertThat(invoice.totals?.grandTotalAmount).isEqualTo(originalInvoice.totals?.grandTotalAmount)
            assertThat(invoice.totals?.totalPrepaidAmount).isEqualTo(originalInvoice.totals?.totalPrepaidAmount)
            assertThat(invoice.totals?.duePayableAmount).isEqualTo(originalInvoice.totals?.duePayableAmount)
        }
    }

    private fun assertParty(actual: Party, expected: Party) {
        assertThat(actual.name).isEqualTo(expected.name)

        assertThat(actual.address).isEqualTo(expected.address)
        assertThat(actual.additionalAddressLine).isEqualTo(expected.additionalAddressLine)
        assertThat(actual.postalCode).isEqualTo(expected.postalCode)
        assertThat(actual.city).isEqualTo(expected.city)
        assertThat(actual.country).isEqualTo(expected.country)

        assertThat(actual.vatId).isEqualTo(expected.vatId)

        assertThat(actual.email).isEqualTo(expected.email)
        assertThat(actual.phone).isEqualTo(expected.phone)
        assertThat(actual.contactName).isEqualTo(expected.contactName)

        assertThat(actual.bankDetails?.accountNumber).isEqualTo(expected.bankDetails?.accountNumber)
        assertThat(actual.bankDetails?.bankCode).isEqualTo(expected.bankDetails?.bankCode)
        assertThat(actual.bankDetails?.accountHolderName).isEqualTo(expected.bankDetails?.accountHolderName)
        assertThat(actual.bankDetails?.financialInstitutionName).isEqualTo(expected.bankDetails?.financialInstitutionName)
    }


    private fun getInvoiceXml(file: Path) = TestUtils.getInvoiceXml(file)


    companion object {

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
        fun provideZugferd_2_1_EN16931ProfileInvoices() = TestUtils.Zugferd_2_1_EN16931ProfileInvoices

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

        @JvmStatic
        fun provideEN16931CIIXmlInvoices() = TestUtils.EN16931CIIXmlInvoices

    }

}
package net.codinux.invoicing.test

import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.ResourceUtil
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.invoicing.testfiles.*
import org.junit.jupiter.api.Named
import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.*

object TestUtils {

    init {
        JavaTestPlatform.initTestEnvironment()
    }


    private val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader


    fun getTestFileAsStream(filename: String, testFileFolder: String = "files"): InputStream =
        ResourceUtil.getResourceAsStream("$testFileFolder/$filename")

    fun getInvalidInvoiceFileAsStream(filename: String, testFileFolder: String = "erroneousInvoiceFiles") =
        getTestFileAsStream(filename, testFileFolder)


    fun getTestFile(filename: String, testFileFolder: String = "files"): Path =
        this.javaClass.classLoader.getResource("$testFileFolder/$filename")!!.toURI().toPath()

    fun getInvalidInvoiceFile(filename: String, testFileFolder: String = "erroneousInvoiceFiles") =
        getTestFile(filename, testFileFolder)



    fun getInvoiceXml(file: Path): String? =
        // TODO: improve file detection
        if (file.extension.lowercase() == "pdf") {
            pdfAttachmentReader.getFileAttachments(file.inputStream()).invoiceXml
        } else if (file.extension.lowercase() == "xml") {
            file.readText()
        } else {
            null
        }


    val FacturXInvoices by lazy { named(EInvoiceTestFiles.getTestFiles(EInvoiceFormat.FacturX)) }


    val FacturXMinimumProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.Minimum) }

    val FacturXBasicWLProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.BasicWL) }

    val FacturXBasicProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.Basic) }

    val FacturXEN16931ProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.EN16931) }

    val FacturXExtendedProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.Extended) }


    val ZugferdInvoices by lazy { named(EInvoiceTestFiles.getTestFiles(EInvoiceFormat.Zugferd)) }


//    val ZugferdMinimumProfileInvoices by lazy { zugferdInvoicesFor(EInvoiceProfile.Minimum) }
//
//    val ZugferdBasicWLProfileInvoices by lazy { zugferdInvoicesFor(EInvoiceProfile.BasicWL) }
//
//    val ZugferdBasicProfileInvoices by lazy { zugferdInvoicesFor(EInvoiceProfile.Basic) }
//
//    val ZugferdEN16931ProfileInvoices by lazy { zugferdInvoicesFor(EInvoiceProfile.EN16931) }
//
//    val ZugferdExtendedProfileInvoices by lazy { zugferdInvoicesFor(EInvoiceProfile.Extended) }
//
//    val ZugferdXRechnungProfileInvoices by lazy { zugferdInvoicesFor(EInvoiceProfile.XRechnung) }


    val Zugferd_2_3_MinimumProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_3, EInvoiceProfile.Minimum) }

    val Zugferd_2_3_BasicWLProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_3, EInvoiceProfile.BasicWL) }

    val Zugferd_2_3_BasicProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_3, EInvoiceProfile.Basic) }

    val Zugferd_2_3_EN16931ProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_3, EInvoiceProfile.EN16931) }

    val Zugferd_2_3_ExtendedProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_3, EInvoiceProfile.Extended) }

    val Zugferd_2_3_XRechnungProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_3, EInvoiceProfile.XRechnung) }


    val Zugferd_2_2_MinimumProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_2, EInvoiceProfile.Minimum) }

    val Zugferd_2_2_BasicWLProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_2, EInvoiceProfile.BasicWL) }

    val Zugferd_2_2_BasicProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_2, EInvoiceProfile.Basic) }

    val Zugferd_2_2_EN16931ProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_2, EInvoiceProfile.EN16931) }

    val Zugferd_2_2_ExtendedProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_2, EInvoiceProfile.Extended) }

    val Zugferd_2_2_XRechnungProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_2, EInvoiceProfile.XRechnung) }


    val Zugferd_2_1_MinimumProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_1, EInvoiceProfile.Minimum) }

    val Zugferd_2_1_BasicWLProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_1, EInvoiceProfile.BasicWL) }

    val Zugferd_2_1_BasicProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_1, EInvoiceProfile.Basic) }

    val Zugferd_2_1_EN16931ProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_1, EInvoiceProfile.EN16931) }

    val Zugferd_2_1_ExtendedProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_1, EInvoiceProfile.Extended) }


    val Zugferd_2_0_MinimumProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_0, EInvoiceProfile.Minimum) }

    val Zugferd_2_0_BasicProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_0, EInvoiceProfile.Basic) }

    val Zugferd_2_0_EN16931ProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_0, EInvoiceProfile.EN16931) }

    val Zugferd_2_0_ExtendedProfileInvoices by lazy { zugferdInvoicesFor(ZugferdVersion.V2_0, EInvoiceProfile.Extended) }


    val XRechnungCiiInvoices by lazy {
        namedIncludingParent(EInvoiceTestFiles.getXRechnungTestFiles(EInvoiceXmlFlavour.CII))
    }

    val XRechnungUblInvoices by lazy {
        namedIncludingParent(EInvoiceTestFiles.getXRechnungTestFiles(EInvoiceXmlFlavour.UBL))
    }

    val NonXRechnungUblInvoices by lazy {
        namedIncludingParent(EInvoiceTestFiles.getOfficialUblInvoiceFiles() + EInvoiceTestFiles.getEN16931UblInvoiceFiles())
    }

    val ValidNonXRechnungUbl_2_1_Invoices by lazy {
        namedIncludingParent(EInvoiceTestFiles.getValidNonXRechnungUbl_2_1_InvoiceFiles())
    }

    val UblInvoicesWithVersionSetTo2_0 by lazy {
        namedIncludingParent(EInvoiceTestFiles.getUblInvoiceFilesWithVersionSetTo2_0())
    }

    val UblInvoicesWithVersionSetTo2_1 by lazy {
        namedIncludingParent(EInvoiceTestFiles.getUblInvoiceFilesWithVersionSetTo2_1())
    }

    val EN16931CIIXmlInvoices by lazy { EInvoiceTestFiles.getEN16931TestFiles(EInvoiceXmlFlavour.CII) }


    private fun facturXInvoicesFor(profile: EInvoiceProfile): List<Named<Path>> =
        named(EInvoiceTestFiles.getTestFiles(EInvoiceFormat.FacturX, FacturXVersion.V1_0_7, profile))

    private fun zugferdInvoicesFor(profile: EInvoiceProfile): List<Named<Path>> =
        named(EInvoiceTestFiles.getTestFiles(EInvoiceFormat.Zugferd, ZugferdVersion.V2_3, profile))

    private fun zugferdInvoicesFor(version: ZugferdVersion, profile: EInvoiceProfile): List<Named<Path>> =
        named(EInvoiceTestFiles.getTestFiles(EInvoiceFormat.Zugferd, version, profile))

    private fun named(paths: List<Path>): List<Named<Path>> = paths.map {
        if (it.name == "factur-x.xml" || it.name == "xrechnung.xml") Named.of(it.parent.name, it)
        else Named.of(it.name, it)
    }

    private fun namedIncludingParent(paths: List<Path>): List<Named<Path>> = paths.map {
        Named.of("${it.parent.name}/${it.name}", it)
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
    fun provideZugferd_2_1_EN16931ProfileInvoices() = TestUtils.Zugferd_2_1_EN16931ProfileInvoices

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

    @JvmStatic
    fun provideNonXRechnungUblInvoices() = TestUtils.NonXRechnungUblInvoices

    @JvmStatic
    fun provideValidNonXRechnungUbl_2_1_Invoices() = TestUtils.ValidNonXRechnungUbl_2_1_Invoices

    @JvmStatic
    fun provideUblInvoicesWithVersionSetTo2_0() = TestUtils.UblInvoicesWithVersionSetTo2_0

    @JvmStatic
    fun provideUblInvoicesWithVersionSetTo2_1() = TestUtils.UblInvoicesWithVersionSetTo2_1

    @JvmStatic
    fun provideEN16931CIIXmlInvoices() = TestUtils.EN16931CIIXmlInvoices

}
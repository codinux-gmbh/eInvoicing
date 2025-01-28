package net.codinux.invoicing.test

import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.invoicing.testfiles.*
import org.junit.jupiter.api.Named
import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.*

object TestUtils {

    private val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader


    fun getTestFileAsStream(filename: String, testFileFolder: String = "files"): InputStream =
        this.javaClass.classLoader.getResourceAsStream("$testFileFolder/$filename")!!

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


    val FacturXMinimumProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.Minimum) }

    val FacturXBasicWLProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.BasicWL) }

    val FacturXBasicProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.Basic) }

    val FacturXEN16931ProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.EN16931) }

    val FacturXExtendedProfileInvoices by lazy { facturXInvoicesFor(EInvoiceProfile.Extended) }


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

}
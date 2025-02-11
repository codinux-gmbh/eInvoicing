package net.codinux.invoicing.validation

import net.codinux.invoicing.extension.readAllBytesAndClose
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.log.logger
import org.verapdf.gf.foundry.VeraGreenfieldFoundryProvider
import org.verapdf.pdfa.Foundries
import org.verapdf.pdfa.flavours.PDFAFlavour
import org.verapdf.pdfa.results.ValidationResult
import java.io.ByteArrayInputStream
import java.nio.file.Path
import kotlin.io.path.inputStream

// VeraPDF does not work on Android, e.g. calls on initialization JAXBContext.newInstance(type), and as almost everything
// gets called via static methods it's also not replaceable
actual open class EInvoicePdfValidator(
    protected val attachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader,
    protected val xmlValidator: EInvoiceXmlValidator = EInvoiceXmlValidator()
) {

    actual constructor() : this(JavaPlatform.pdfAttachmentReader, EInvoiceXmlValidator())

    init {
        VeraGreenfieldFoundryProvider.initialise() // alternative: PdfBoxFoundryProvider.initialise()
    }

    private val log by logger()


    actual open suspend fun validateEInvoicePdf(pdfBytes: ByteArray) =
        validate(pdfBytes)

    open fun validate(pdfFile: Path) =
        validate(pdfFile.inputStream().readAllBytesAndClose())

    open fun validate(pdfBytes: ByteArray): Result<PdfValidationResult> =
        // see https://docs.verapdf.org/develop/
        try {
            Foundries.defaultInstance().createParser(ByteArrayInputStream(pdfBytes)).use { parser ->
                // check if flavor is valid:
                val isPdfA = parser.flavour != PDFAFlavour.NO_FLAVOUR
                val isPdfA3 = parser.flavour.part == PDFAFlavour.Specification.ISO_19005_3 // ISO 19005-3 (2012) specifies PDF/A-3

                // if it's already a PDF/A-3 flavour - PDF/A-3a, -b or -u -, use that one, otherwise validate at least against PDF/A-3b
                val flavourToValidateAgainst = if (isPdfA3) parser.flavour else PDFAFlavour.PDFA_3_B
                val validator = Foundries.defaultInstance().createValidator(flavourToValidateAgainst, false)
                val result = validator.validate(parser)
                val validationErrors = mapErrors(result)

                val xmlValidationResult = validateXml(pdfBytes)

                Result.success(PdfValidationResult(result.isCompliant, isPdfA, isPdfA3, mapFlavor(parser.flavour), result.totalAssertions, validationErrors, xmlValidationResult))
            }
        } catch (e: Throwable) {
            log.error(e) { "PDF validation failed" }
            Result.error(e)
        }

    protected open fun mapFlavor(flavour: PDFAFlavour): PdfAFlavour =
        try {
            PdfAFlavour.valueOf(flavour.name)
        } catch (e: Throwable) {
            log.error(e) { "Could not map VeraPDF PDFAFlavour '$flavour to PdfAFlavour" }
            PdfAFlavour.Unknown
        }

    protected open fun mapErrors(result: ValidationResult): List<PdfValidationError> =
        result.testAssertions.map { assertion ->
            val rule = result.validationProfile.getRuleByRuleId(assertion.ruleId)!!
            PdfValidationError(assertion.message, rule.description, rule.test, rule.`object`,
                PdfValidationRule(rule.ruleId.specification.id, rule.ruleId.clause, rule.ruleId.testNumber),
                rule.references.map { PdfReference(it.specification, it.clause.takeUnless { it.isNullOrBlank() }) },
                rule.error.arguments.map { it.argument }, assertion.location.context)
        }

    protected open fun validateXml(pdfBytes: ByteArray): Result<InvoiceXmlValidationResult> =
        try {
            val invoiceXml = attachmentReader.getFileAttachments(pdfBytes).invoiceXml
            if (invoiceXml != null) {
                xmlValidator.validateEInvoiceXmlJvm(invoiceXml)
            } else {
                Result.error(IllegalStateException("No EInvoice XML attached to PDF file"))
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not validate PDF's attached invoice XML" }
            Result.error(e)
        }

}
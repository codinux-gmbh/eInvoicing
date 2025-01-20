package net.codinux.invoicing.validation

import net.codinux.invoicing.extension.readAllBytesAndClose
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.log.logger
import org.verapdf.gf.foundry.VeraGreenfieldFoundryProvider
import org.verapdf.pdfa.Foundries
import org.verapdf.pdfa.flavours.PDFAFlavour
import org.verapdf.pdfa.results.ValidationResult
import java.io.ByteArrayInputStream
import java.nio.file.Path
import kotlin.io.path.inputStream

open class EInvoicePdfValidator {

    companion object {
        private val CompliantPdfAVersions = listOf(PDFAFlavour.PDFA_3_A, PDFAFlavour.PDFA_3_B, PDFAFlavour.PDFA_3_U)
    }

    private val log by logger()


    init {
        VeraGreenfieldFoundryProvider.initialise() // alternative: PdfBoxFoundryProvider.initialise()
    }


    open fun validate(pdfFile: Path) =
        validate(pdfFile.inputStream().readAllBytesAndClose())

    open fun validate(pdfBytes: ByteArray): PdfValidationResult {
        // see https://docs.verapdf.org/develop/
        try {
            Foundries.defaultInstance().createParser(ByteArrayInputStream(pdfBytes)).use { parser ->
                // check if flavor is valid:
                val isPdfA = parser.flavour != PDFAFlavour.NO_FLAVOUR
                val isPdfA3 = parser.flavour in CompliantPdfAVersions

                // TODO: validate explicitly against PDF-A3
                val validator = Foundries.defaultInstance().createValidator(parser.flavour, false)
                val result = validator.validate(parser)
                val validationErrors = mapErrors(result)

                return PdfValidationResult(result.isCompliant, null, isPdfA, isPdfA3, mapFlavor(parser.flavour), result.totalAssertions, validationErrors)
            }
        } catch (e: Throwable) {
            return PdfValidationResult(false, SerializableException(e))
        }
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

}
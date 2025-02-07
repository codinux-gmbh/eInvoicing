package net.codinux.invoicing.validation

import kotlinx.coroutines.runBlocking
import net.codinux.invoicing.model.Result
import net.codinux.log.logger
import org.mustangproject.validator.ZUGFeRDValidator
import java.io.File
import java.lang.reflect.Field

open class MustangEInvoiceValidator {

    companion object {
        private val CriterionField = getPrivateField("criterion")

        private val log by logger()

        private fun getPrivateField(fieldName: String): Field? = try {
           org.mustangproject.validator.ValidationResultItem::class.java.getDeclaredField(fieldName).apply {
               trySetAccessible()
           }
        } catch (e: Throwable) {
            log.error(e) { "Could not access private field '$fieldName' of Mustang ValidationResultItem" }
            null
        }
    }


    open suspend fun validateEInvoiceXml(xml: String) =
        validateEInvoiceXmlJvm(xml)

    open fun validateEInvoiceXmlJvm(xml: String) =
        validateEInvoiceFileJvm(xml.toByteArray())

    open suspend fun validateEInvoiceFile(fileContent: ByteArray): Result<InvoiceValidationResult> =
        validateEInvoiceFileJvm(fileContent)

    open fun validateEInvoiceFileJvm(fileContent: ByteArray): Result<InvoiceValidationResult> =
        try {
            val validator = object : ZUGFeRDValidator() {
                fun getContext() = this.context
            }

            validator.disableNotices() // bedeutet letztlich, dass Nicht-XRechnungsdateien gegen das XRechnungs-Schematron validiert werden

            // - validiert zuerst das XSD Schema
            // - danach das Profil spezifische Schematron, z.B. /xslt/ZF_232/FACTUR-X_EN16931.xslt
            // - beim Basic, EN16931 und XRechnungs- (jedoch nicht beim Extended-)Profil wendet er dann die Schematrondatei
            //     "/xslt/en16931schematron/EN16931-CII-validation.xslt", an. Kommentar: "additionally validate against CEN".
            //     For file see: https://github.com/ConnectingEurope/eInvoicing-EN16931/blob/master/cii/xslt/EN16931-CII-validation.xslt
            //     There are also XSLT, XSD and Schematron files for UBL and EDIFACT in this repo
            // - Falls disableNotices = false oder XrechnungSeverity > notice, dann ruft er noch validateXR() auf - auch fuer Basic und EN16931 -
            //     mit der Datei "/xslt/XR_30/XRechnung-CII-validation.xslt". Begruendung: "This is the default check which is also run on en16931 files to generate notices"
            val report = validator.validate(fileContent, "validation.xml")

            val context = validator.getContext()
            val isXmlValid = context.isValid
            val xmlValidationResults = context.results.map { mapValidationResultItem(it) }

            // TODO: currently it's not possible to get PDF validation result as for PDF validation the same context object
            //  is used and then in a private method before XML validation context.clear() gets called removing all PDF validation results

            Result.success(InvoiceValidationResult(validator.wasCompletelyValid(), isXmlValid, xmlValidationResults, report))
        } catch (e: Throwable) {
            log.error(e) { "Could not validate EInvoice file" }
            Result.error(e)
        }

    open fun validate(fileToValidate: File) = runBlocking {
        validateEInvoiceFile(fileToValidate.readBytes())
    }

    protected open fun mapValidationResultItem(item: org.mustangproject.validator.ValidationResultItem) =
        ValidationResultItem(mapSeverity(item), item.message, item.location, CriterionField?.get(item) as? String)

    protected open fun mapSeverity(item: org.mustangproject.validator.ValidationResultItem): ValidationResultSeverity {
        var name = item.severity.name
        name = name.first().uppercase() + name.substring(1)

        return ValidationResultSeverity.valueOf(name)
    }

}
package net.codinux.invoicing.test.mustang

import kotlinx.coroutines.runBlocking
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.validation.InvoiceXmlValidationResult
import net.codinux.invoicing.validation.ValidationResultItem
import net.codinux.invoicing.validation.ValidationResultItemSeverity
import net.codinux.log.logger
import org.mustangproject.validator.ESeverity
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
        validateEInvoicePdf(xml.toByteArray())

    open fun validateEInvoicePdf(pdfBytes: ByteArray): Result<InvoiceXmlValidationResult> =
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
            val report = validator.validate(pdfBytes, "validation.xml")

            val context = validator.getContext()
            // id, test und location extrahiert er. Selten, sehr selten gibt es ein Attribute namens flag mit dem Wert "warning", sonst setzt auch er es.
            // criterion = test.
            // message ist zusammengesetzt aus text - er ist nimmt immer nur den letzten, egal ob es mehrere sind oder nicht (koennen es je mehrere sein?) -, id und XSLT filename
            // section setzt er, 4, 24 oder 27. Was sie bedeuten, weiss ich nicht, scheinen aber Quatsch zu sein
            val xmlValidationResults = context.results.map { mapValidationResultItem(it) }

            // TODO: currently it's not possible to get PDF validation result as for PDF validation the same context object
            //  is used and then in a private method before XML validation context.clear() gets called removing all PDF validation results

            Result.success(InvoiceXmlValidationResult(validator.wasCompletelyValid(), xmlValidationResults)) // not going to rebuild countAvailableTests and countAppliedTests for Mustang
        } catch (e: Throwable) {
            log.error(e) { "Could not validate EInvoice file" }
            Result.error(e)
        }

    open fun validate(fileToValidate: File) = runBlocking {
        validateEInvoicePdf(fileToValidate.readBytes())
    }

    protected open fun mapValidationResultItem(item: org.mustangproject.validator.ValidationResultItem) =
        ValidationResultItem(mapSeverity(item.severity), item.message, item.location, CriterionField?.get(item) as? String, item.id)

    private fun mapSeverity(severity: ESeverity?): ValidationResultItemSeverity = when (severity) {
        ESeverity.notice -> ValidationResultItemSeverity.Info
        ESeverity.warning -> ValidationResultItemSeverity.Warning
        ESeverity.error, ESeverity.fatal, ESeverity.exception -> ValidationResultItemSeverity.Error
        else -> ValidationResultItemSeverity.Error
    }

}
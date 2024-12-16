package net.codinux.invoicing.validation

import net.codinux.log.logger
import org.mustangproject.validator.ZUGFeRDValidator
import java.io.File
import java.lang.reflect.Field

open class EInvoiceValidator {

    companion object {
        private val SectionField = getPrivateField("section")
        private val CriterionField = getPrivateField("criterion")
        private val StacktraceField = getPrivateField("stacktrace")

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


    open fun validate(fileToValidate: File, disableNotices: Boolean = false): InvoiceValidationResult {
        val validator = object : ZUGFeRDValidator() {
            fun getContext() = this.context
        }

        if (disableNotices) {
            validator.disableNotices()
        }

        val report = validator.validate(fileToValidate.absolutePath)

        val context = validator.getContext()
        val isXmlValid = context.isValid
        val xmlValidationResults = context.results.map { mapValidationResultItem(it) }

        // TODO: currently it's not possible to get PDF validation result as for PDF validation the same context object
        //  is used and then in a private method before XML validation context.clear() gets called removing all PDF validation results

        return InvoiceValidationResult(validator.wasCompletelyValid(), isXmlValid, xmlValidationResults, report)
    }

    protected open fun mapValidationResultItem(item: org.mustangproject.validator.ValidationResultItem) =
        ValidationResultItem(mapSeverity(item), item.message, item.location, SectionField?.get(item) as? Int, CriterionField?.get(item) as? String, StacktraceField?.get(item) as? String)

    protected open fun mapSeverity(item: org.mustangproject.validator.ValidationResultItem): ValidationResultSeverity {
        var name = item.severity.name
        name = name.first().uppercase() + name.substring(1)

        return ValidationResultSeverity.valueOf(name)
    }

}
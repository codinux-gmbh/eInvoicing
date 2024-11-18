package net.codinux.invoicing.validation

import org.mustangproject.validator.ZUGFeRDValidator
import java.io.File

class EInvoiceValidator {

    fun validate(fileToValidate: File, disableNotices: Boolean = false): InvoiceValidationResult {
        val validator = ZUGFeRDValidator()
        if (disableNotices) {
            validator.disableNotices()
        }

        // TODO: this is far from ideal to have to report only as string and not the single failures as objects
        val report = validator.validate(fileToValidate.absolutePath)

        return InvoiceValidationResult(validator.wasCompletelyValid(), report)
    }

}
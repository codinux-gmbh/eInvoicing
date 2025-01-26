package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
data class InvoiceDataError(
    val field: InvoiceField,
    val errorType: InvoiceDataErrorType,
    val erroneousValue: String? = null,
) {
    companion object {
        fun missing(field: InvoiceField, erroneousValue: String? = null) =
            InvoiceDataError(field, InvoiceDataErrorType.ValueNotSet, erroneousValue)

        fun invalid(field: InvoiceField, erroneousValue: String? = null) =
            InvoiceDataError(field, InvoiceDataErrorType.ValueIsInvalid, erroneousValue)
    }
}

enum class InvoiceDataErrorType {
    ValueNotSet,
    ValueNotUpperCase,
    ValueIsInvalid,

    CalculatedAmountsAreInvalid
}
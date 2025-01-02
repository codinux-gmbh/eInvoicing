package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
data class InvoiceDataError(
    val field: InvoiceField,
    val errorType: InvoiceDataErrorType,
    val erroneousValue: String? = null,
)

enum class InvoiceDataErrorType {
    CurrencyIsoCodeNotSet,
    CurrencyIsoCodeNotUpperCase,
    CurrencyIsoCodeIsInvalid,

    CountryIsoCodeNotSet,
    CountryIsoCodeNotUpperCase,
    CountryIsoCodeIsInvalid,

    UnitCodeNotSet,
    UnitCodeNotUpperCase,
    UnitCodeIsInvalid,
}
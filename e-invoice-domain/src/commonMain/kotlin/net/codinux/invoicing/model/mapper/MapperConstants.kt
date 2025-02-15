package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.codes.Country
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure

object MapperConstants {

    val IdFallbackValue = ""

    val CodeFallbackValue = ""

    val TextFallbackValue = ""

    val BigDecimalFallbackValue = BigDecimal.Zero

    val LocalDateFallbackValue = LocalDate(0, 1, 1)

    val CurrencyFallbackValue = Currency.TheCodesAssignedForTransactionsWhereNoCurrencyIsInvolved

    val CountryFallbackValue = Country.UnknownCountry

    val UnitFallbackValue = UnitOfMeasure.ZZ



    val CountriesByIsoCode = Country.entries.associateBy { it.alpha2Code }

    val CurrenciesByIsoCode = Currency.entries.associateBy { it.alpha3Code }

    val UnitsByCode = UnitOfMeasure.entries.associateBy { it.code }

    fun mapCountry(countryIsoCode: String?): Country =
        countryIsoCode?.let { CountriesByIsoCode[it.uppercase()] } ?: CountryFallbackValue

    fun mapCurrency(currencyIsoCode: String?): Currency =
        currencyIsoCode?.let { CurrenciesByIsoCode[it.uppercase()] } ?: CurrencyFallbackValue

    fun mapUnit(countryIsoCode: String?): UnitOfMeasure =
        countryIsoCode?.let { UnitsByCode[it.uppercase()] } ?: UnitFallbackValue

}
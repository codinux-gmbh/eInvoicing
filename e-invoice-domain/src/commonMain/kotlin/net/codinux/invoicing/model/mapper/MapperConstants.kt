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

}
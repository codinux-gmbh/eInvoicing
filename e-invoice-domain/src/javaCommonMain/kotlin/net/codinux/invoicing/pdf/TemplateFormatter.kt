package net.codinux.invoicing.pdf

import com.github.jknack.handlebars.Options
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure

interface TemplateFormatter {

    fun formatDate(date: LocalDate): String

    fun formatPercent(percent: BigDecimal): String

    fun formatCurrency(amount: BigDecimal?, currency: Currency): String

    fun formatUnit(unit: UnitOfMeasure): String

    fun formatItemPosition(itemPosition: String?, itemIndex: Int): String

    fun i18n(english: String, german: String): String

    fun i18nArgs(english: String, german: String, args: Options): String

}
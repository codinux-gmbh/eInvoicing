package net.codinux.invoicing.pdf

import com.github.jknack.handlebars.Handlebars
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

open class HandlebarsTemplateService {

    protected val handlebars = Handlebars().apply {
        registerHelpers(HelperSource())
    }


    open fun renderTemplate(template: String, invoice: Invoice): String {
        val compiledTemplate = handlebars.compileInline(template) // TODO: cache invoice template

        return compiledTemplate.apply(invoice)
    }

    open class HelperSource {

        protected open val dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

        protected open val percentFormat = DecimalFormat.getPercentInstance()

        protected open val currencyFormat = DecimalFormat.getCurrencyInstance()


        open fun formatDate(date: LocalDate): String = dateFormat.format(date.toJvmDate())

        open fun formatPercent(percent: BigDecimal): String = percentFormat.format(percent.toJvmBigDecimal())

        open fun formatCurrency(amount: BigDecimal, currency: Currency): String = currencyFormat
            .apply { this.currency = java.util.Currency.getInstance(currency.alpha3Code) }
            .format(amount.toJvmBigDecimal())

        open fun formatUnit(unit: UnitOfMeasure): String = unit.symbol ?: unit.englishName // TODO

        open fun formatItemPosition(itemPosition: String?, itemIndex: Int): String = itemPosition ?: (itemIndex + 1).toString()

    }

}
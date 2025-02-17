package net.codinux.invoicing.pdf

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Options
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Base64
import java.util.Locale

open class HandlebarsTemplateService : TemplateService {

    protected val handlebarsByLanguage = mapOf(
        InvoiceLanguage.English to Handlebars().registerHelpers(HelperSource(InvoiceLanguage.English)),
        InvoiceLanguage.German to Handlebars().registerHelpers(HelperSource(InvoiceLanguage.German))
    )


    data class TemplateContext(
        val invoice: Invoice,
        val settings: InvoicePdfTemplateSettings?
    )


    override fun renderTemplate(template: String, invoice: Invoice, settings: InvoicePdfTemplateSettings?): String {
        val handlebars = handlebarsByLanguage[settings?.language ?: InvoiceLanguage.English]!!

        val compiledTemplate = handlebars.compileInline(template) // TODO: cache invoice template

        val templateSettings = if (settings?.logo?.imageBytes != null) settings.copy(logo = Image.forUrl(createLogoDataUrl(settings)))
                                else settings
        return compiledTemplate.apply(TemplateContext(invoice, templateSettings))
    }

    protected open fun createLogoDataUrl(settings: InvoicePdfTemplateSettings) =
        "data:${settings.logo?.imageMimeType ?: "image/png"};base64,${Base64.getEncoder().encodeToString(settings.logo?.imageBytes)}"


    open class HelperSource(protected val language: InvoiceLanguage) {

        protected val locale = if (language == InvoiceLanguage.German) Locale.GERMAN else Locale.ENGLISH

        protected open val dateFormat = DateTimeFormatter.ofLocalizedDate(if (language == InvoiceLanguage.German) FormatStyle.MEDIUM else FormatStyle.SHORT)
            .withLocale(locale) // don't use localizedBy() as that requires Android API >= 34

        protected open val percentFormat = DecimalFormat.getPercentInstance(locale)

        protected open val currencyFormat = DecimalFormat.getCurrencyInstance(locale)


        open fun formatDate(date: LocalDate): String = dateFormat.format(date.toJvmDate())

        open fun formatPercent(percent: BigDecimal): String = percentFormat.format(percent.toJvmBigDecimal())

        open fun formatCurrency(amount: BigDecimal?, currency: Currency): String = amount?.let {
            currencyFormat.apply { this.currency = java.util.Currency.getInstance(currency.alpha3Code) }
                .format(amount.toJvmBigDecimal())
        } ?: ""

        open fun formatUnit(unit: UnitOfMeasure): String = unit.symbol ?: unit.englishName // TODO

        open fun formatItemPosition(itemPosition: String?, itemIndex: Int): String = itemPosition ?: (itemIndex + 1).toString()

        open fun i18n(english: String, german: String): String = if (language == InvoiceLanguage.German) german else english

        open fun i18nArgs(english: String, german: String, args: Options): String {
            val stringTemplate = i18n(english, german)

            return String.format(stringTemplate, *args.params.drop(1).toTypedArray())
        }

    }

}
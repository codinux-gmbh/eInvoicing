package net.codinux.invoicing.pdf

import com.github.jknack.handlebars.Handlebars
import net.codinux.invoicing.model.Image
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.InvoiceLanguage
import java.util.*

open class HandlebarsTemplateService(creator: TemplateFormatterCreator? = null) : TemplateService {

    protected val handlebarsByLanguage = mapOf(
        InvoiceLanguage.English to Handlebars().registerHelpers(creator?.createFormatter(InvoiceLanguage.English) ?: TemplateFormatterHelperSource(InvoiceLanguage.English)),
        InvoiceLanguage.German to Handlebars().registerHelpers(creator?.createFormatter(InvoiceLanguage.German) ?: TemplateFormatterHelperSource(InvoiceLanguage.German))
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

}
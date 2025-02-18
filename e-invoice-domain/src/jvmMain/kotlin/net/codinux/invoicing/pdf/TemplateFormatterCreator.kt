package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.InvoiceLanguage

interface TemplateFormatterCreator {

    fun createFormatter(language: InvoiceLanguage): TemplateFormatter

}
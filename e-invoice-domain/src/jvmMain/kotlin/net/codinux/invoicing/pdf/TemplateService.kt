package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.Invoice

interface TemplateService {

    fun renderTemplate(template: String, invoice: Invoice, settings: InvoicePdfTemplateSettings? = null): String

}
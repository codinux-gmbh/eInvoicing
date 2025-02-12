package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.InvoiceLanguage

interface TemplateService {

    fun renderTemplate(template: String, invoice: Invoice, language: InvoiceLanguage? = null): String

}
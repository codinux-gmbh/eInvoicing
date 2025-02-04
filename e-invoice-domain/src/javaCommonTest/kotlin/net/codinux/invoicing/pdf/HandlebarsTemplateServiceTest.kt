package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.contains
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.TestUtils
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.io.path.readText
import kotlin.test.Test

class HandlebarsTemplateServiceTest {

    private val invoice = DataGenerator.createInvoice()

    private val template by lazy { TestUtils.getTestFile("Invoice.handlebars.html", "templates/invoice").readText() }

    private val underTest = HandlebarsTemplateService()


    @Test
    fun renderTemplate_English() {
        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.English)


        val locale = Locale.ENGLISH
        val dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale)
        val currencyFormat = DecimalFormat.getCurrencyInstance(locale).apply { currency = Currency.getInstance(invoice.details.currency.alpha3Code) }
        val percentFormat = DecimalFormat.getPercentInstance(locale)


        // first block left side
        assertCommonText(result)

        // first block right side
        assertThat(result).contains("<strong>Phone:</strong> ${invoice.supplier.phone}")
        assertThat(result).contains("<strong>Email:</strong> ${invoice.supplier.email}")

        assertThat(result).contains("<strong>Invoice No:</strong> ${invoice.details.invoiceNumber}")
        assertThat(result).contains("<strong>Invoice date:</strong> ${dateFormat.format(invoice.details.invoiceDate.toJvmDate())}")
        assertThat(result).contains("<strong>VAT ID:</strong> ${invoice.supplier.vatId}")

        // invoice description
        assertThat(result).contains("Invoice ${invoice.details.invoiceNumber}")
        val startDate = dateFormat.format(invoice.details.serviceDate!!.asServicePeriod()!!.startDate.toJvmDate())
        val endDate = dateFormat.format(invoice.details.serviceDate!!.asServicePeriod()!!.endDate.toJvmDate())
        assertThat(result).contains("For the services rendered from $startDate to $endDate, I hereby invoice you for the following")

        // totals
        assertThat(result).contains("Net Amount:")
        assertThat(result).contains(currencyFormat.format(invoice.totals!!.taxBasisTotalAmount.toJvmBigDecimal()))
        assertThat(result).contains("Tax Amount:")
        assertThat(result).contains(currencyFormat.format(invoice.totals!!.taxTotalAmount.toJvmBigDecimal()))
        assertThat(result).contains("Total Amount Due:")
        assertThat(result).contains(currencyFormat.format(invoice.totals!!.duePayableAmount.toJvmBigDecimal()))

        // thanks
        assertThat(result).contains("Thank you for your order!")

        // footer
        assertThat(result).contains("Company Address")
        assertThat(result).contains("Contact")
        assertThat(result).contains("Bank Details")

        assertThat(result).contains("Page")

        // line items
        assertThat(result).contains("<th>Pos.</th>")
        assertThat(result).contains("<th>Article Number</th>")
        assertThat(result).contains("<th>Article</th>")
        assertThat(result).contains("<th>Quantity</th>")
        assertThat(result).contains("<th>Unit</th>")
        assertThat(result).contains("<th>Unit Price</th>")
        assertThat(result).contains("<th>VAT %</th>")

        assertLineItems(result, currencyFormat, percentFormat)
    }

    @Test
    fun renderTemplate_German() {
        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.German)


        val locale = Locale.GERMAN
        val dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(locale)
        val currencyFormat = DecimalFormat.getCurrencyInstance(locale).apply { currency = Currency.getInstance(invoice.details.currency.alpha3Code) }
        val percentFormat = DecimalFormat.getPercentInstance(locale)


        // first block left side
        assertCommonText(result)

        // first block right side
        assertThat(result).contains("<strong>Tel.:</strong> ${invoice.supplier.phone}")
        assertThat(result).contains("<strong>E-Mail:</strong> ${invoice.supplier.email}")

        assertThat(result).contains("<strong>Rechnungsnr.:</strong> ${invoice.details.invoiceNumber}")
        assertThat(result).contains("<strong>Rechnungsdatum:</strong> ${dateFormat.format(invoice.details.invoiceDate.toJvmDate())}")
        assertThat(result).contains("<strong>Ust-IdNr.:</strong> ${invoice.supplier.vatId}")

        // invoice description
        assertThat(result).contains("Rechnung ${invoice.details.invoiceNumber}")
        val startDate = dateFormat.format(invoice.details.serviceDate!!.asServicePeriod()!!.startDate.toJvmDate())
        val endDate = dateFormat.format(invoice.details.serviceDate!!.asServicePeriod()!!.endDate.toJvmDate())
        assertThat(result).contains("Für die im Zeitraum $startDate – $endDate erbrachten Leistungen erlaube ich mir in Rechnung zu stellen")

        // totals
        assertThat(result).contains("Netto:")
        assertThat(result).contains(currencyFormat.format(invoice.totals!!.taxBasisTotalAmount.toJvmBigDecimal()))
        assertThat(result).contains("Umsatzsteuer:")
        assertThat(result).contains(currencyFormat.format(invoice.totals!!.taxTotalAmount.toJvmBigDecimal()))
        assertThat(result).contains("Rechnungsbetrag:")
        assertThat(result).contains(currencyFormat.format(invoice.totals!!.duePayableAmount.toJvmBigDecimal()))

        // thanks
        assertThat(result).contains("Herzlichen Dank für Ihren Auftrag!")

        // footer
        assertThat(result).contains("Anschrift")
        assertThat(result).contains("Kontakt")
        assertThat(result).contains("Bankverbindung")

        assertThat(result).contains("Seite")

        // line items
        assertThat(result).contains("<th>Nr.</th>")
        assertThat(result).contains("<th>Artikelnr.</th>")
        assertThat(result).contains("<th>Artikel</th>")
        assertThat(result).contains("<th>Menge</th>")
        assertThat(result).contains("<th>Einheit</th>")
        assertThat(result).contains("<th>Einzelpreis</th>")
        assertThat(result).contains("<th>Mwst. %</th>")

        assertLineItems(result, currencyFormat, percentFormat)
    }

    private fun assertCommonText(result: String) {
        assertThat(result).contains("""${invoice.supplier.name} | ${invoice.supplier.address} | ${invoice.supplier.postalCode} ${invoice.supplier.city}""")
        assertThat(result).contains(invoice.customer.name)
        assertThat(result).contains(invoice.customer.address)
        assertThat(result).contains("${invoice.customer.postalCode} ${invoice.customer.city}")

        assertThat(result).contains(invoice.details.invoiceNumber)
    }

    private fun assertLineItems(result: String, currencyFormat: NumberFormat, percentFormat: NumberFormat) {
        invoice.items.forEachIndexed { index, item ->
            assertThat(result).contains("<td class=\"right-aligned\">${index + 1}</td>")
            assertThat(result).contains("<td>${item.articleNumber ?: ""}</td>")
            assertThat(result).contains("<td>${item.name}</td>")
            assertThat(result).contains("<td class=\"right-aligned\">${item.quantity.toPlainString()}</td>")
            //            assertThat(result).contains("<td>${item.unit}</td>") // TODO: assert unit
            assertThat(result).contains("<td class=\"right-aligned\">${currencyFormat.format(item.unitPrice.toJvmBigDecimal())}</td>")
            assertThat(result).contains("<td class=\"right-aligned\">${percentFormat.format(item.vatRate.toJvmBigDecimal())}</td>")
        }
    }

}
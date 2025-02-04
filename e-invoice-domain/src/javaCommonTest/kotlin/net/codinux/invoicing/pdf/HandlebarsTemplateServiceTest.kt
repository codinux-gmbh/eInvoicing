package net.codinux.invoicing.pdf

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.ServiceDate
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
    fun emailNotSet_English() {
        val invoice = invoice.copy(supplier = invoice.supplier.copy(email = null))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.English)

        assertThat(result).doesNotContain("Email")
    }

    @Test
    fun emailNotSet_German() {
        val invoice = invoice.copy(supplier = invoice.supplier.copy(email = null))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.German)

        assertThat(result).doesNotContain("E-Mail")
    }

    @Test
    fun phoneNotSet_English() {
        val invoice = invoice.copy(supplier = invoice.supplier.copy(phone = null))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.English)

        assertThat(result).doesNotContain("Phone")
    }

    @Test
    fun phoneNotSet_German() {
        val invoice = invoice.copy(supplier = invoice.supplier.copy(phone = null))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.German)

        assertThat(result).doesNotContain("Tel.")
    }

    @Test
    fun serviceDateNotSet_English() { // in an earlier version if serviceDate was not set, applying template crashed
        val invoice = invoice.copy(details = invoice.details.copy(serviceDate = null))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.English)

        assertThat(result).contains("For the services rendered, I hereby invoice you for the following:")
    }

    @Test
    fun serviceDateNotSet_German() { // in an earlier version if serviceDate was not set, applying template crashed
        val invoice = invoice.copy(details = invoice.details.copy(serviceDate = null))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.German)

        assertThat(result).contains("Für die erbrachten Leistungen erlaube ich mir in Rechnung zu stellen:")
    }

    @Test
    fun deliveryDateSet_English() {
        val invoice = invoice.copy(details = invoice.details.copy(serviceDate = ServiceDate.DeliveryDate(LocalDate(2015, 10, 21))))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.English)

        assertThat(result).contains("For the goods delivered on 10/21/15, I hereby invoice you for the following:")
    }

    @Test
    fun deliveryDateSet_German() {
        val invoice = invoice.copy(details = invoice.details.copy(serviceDate = ServiceDate.DeliveryDate(LocalDate(2015, 10, 21))))

        val result = underTest.renderTemplate(template, invoice, InvoiceLanguage.German)

        assertThat(result).contains("Für die am 21.10.2015 gelieferten Güter erlaube ich mir in Rechnung zu stellen:")
    }


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
        assertThat(result).contains("Phone: ${invoice.supplier.phone}")
        assertThat(result).contains(invoice.supplier.email!!)

        assertThat(result).contains("<td class=\"bold\">Invoice No:</td>")
        assertThat(result).contains("<td class=\"right-aligned\">${invoice.details.invoiceNumber}</td>")
        assertThat(result).contains("<td class=\"bold\">Invoice date:</td>")
        assertThat(result).contains("<td class=\"right-aligned\">${dateFormat.format(invoice.details.invoiceDate.toJvmDate())}</td>")
        assertThat(result).contains("<td class=\"bold\">VAT ID:</td>")
        assertThat(result).contains("<td class=\"right-aligned\">${invoice.supplier.vatId}</td>")

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
        assertThat(result).contains("Tel.: ${invoice.supplier.phone}")
        assertThat(result).contains(invoice.supplier.email!!)

        assertThat(result).contains("<td class=\"bold\">Rechnungsnr.:</td>")
        assertThat(result).contains("<td class=\"right-aligned\">${invoice.details.invoiceNumber}</td>")
        assertThat(result).contains("<td class=\"bold\">Rechnungsdatum:</td>")
        assertThat(result).contains("<td class=\"right-aligned\">${dateFormat.format(invoice.details.invoiceDate.toJvmDate())}</td>")
        assertThat(result).contains("<td class=\"bold\">Ust-IdNr.:</td>")
        assertThat(result).contains("<td class=\"right-aligned\">${invoice.supplier.vatId}</td>")

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
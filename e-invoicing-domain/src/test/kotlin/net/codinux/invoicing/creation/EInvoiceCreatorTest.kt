package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isNotEmpty
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.LineItem
import net.codinux.invoicing.model.Party
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.test.Test

class EInvoiceCreatorTest {

    private val underTest = EInvoiceCreator()


    @Test
    fun createXRechnung() {
        val invoice = createInvoice()

        val result = underTest.createXRechnungXml(invoice)

        assertThat(result).isNotEmpty()
    }

    @Test
    fun createZugferdXml() {
        val invoice = createInvoice()

        val result = underTest.createZugferdXml(invoice)

        assertThat(result).isNotEmpty()
    }


    private fun createInvoice(
        invoiceNumber: String = "12345",
        invoicingDate: LocalDate = LocalDate.of(2015, 10, 21),
        sender: Party = createParty("Hochwürdiger Leistungserbringer"),
        recipient: Party = createParty("Untertänigster Leistungsempfänger"),
        items: List<LineItem> = listOf(createItem()),
        dueDate: LocalDate? = null
    ) = Invoice(invoiceNumber, invoicingDate, sender, recipient, items, dueDate)

    private fun createParty(
        name: String,
        streetName: String = "Fun Street",
        houseNumber: String = "1",
        postalCode: String = "12345",
        city: String = "Glückstadt",
        country: String? = null,
        taxNumber: String? = "DE12345678",
        email: String? = null,
    ) = Party(name, streetName, houseNumber, postalCode, city, country, taxNumber, email)

    private fun createItem(
        name: String = "Erbrachte Dienstleistungen",
        unit: String = "",
        quantity: BigDecimal = BigDecimal(1),
        price: BigDecimal = BigDecimal(99),
        vatPercentage: BigDecimal = BigDecimal(0.19),
        description: String? = null,
    ) = LineItem(name, unit, quantity, price, vatPercentage, description)

}
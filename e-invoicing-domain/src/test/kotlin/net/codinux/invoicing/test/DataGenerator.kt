package net.codinux.invoicing.test

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.LineItem
import net.codinux.invoicing.model.Party
import java.math.BigDecimal
import java.time.LocalDate

object DataGenerator {

    const val InvoiceNumber = "12345"
    val InvoicingDate = LocalDate.of(2015, 10, 21)

    const val SenderName = "Hochwürdiger Leistungserbringer"
    const val SenderStreet = "Fun Street 1"
    const val SenderPostalCode = "12345"
    const val SenderCity = "Glückstadt"
    val SenderCountry: String? = null
    const val SenderVatId = "DE12345678"
    const val SenderEmail = "working-class-hero@rock.me"

    const val RecipientName = "Untertänigster Leistungsempfänger"
    const val RecipientStreet = "Party Street 1"
    const val RecipientPostalCode = SenderPostalCode
    const val RecipientCity = SenderCity
    val RecipientCountry: String? = SenderCountry
    const val RecipientVatId = "DE87654321"
    const val RecipientEmail = "exploiter@your.boss"

    const val ItemName = "Erbrachte Dienstleistungen"
    const val ItemUnit = "HUR" // EN code for 'hour'
    val ItemQuantity = BigDecimal(1)
    val ItemPrice = BigDecimal(99)
    val ItemVat = BigDecimal(0.19)
    val ItemDescription: String? = null


    fun createInvoice(
        invoiceNumber: String = InvoiceNumber,
        invoicingDate: LocalDate = InvoicingDate,
        sender: Party = createParty(SenderName, SenderStreet, SenderPostalCode, SenderCity, SenderCountry, SenderVatId, SenderEmail),
        recipient: Party = createParty(RecipientName, RecipientStreet, RecipientPostalCode, RecipientCity, RecipientCountry, RecipientVatId, RecipientEmail),
        items: List<LineItem> = listOf(createItem()),
        dueDate: LocalDate? = null
    ) = Invoice(invoiceNumber, invoicingDate, sender, recipient, items, dueDate)

    fun createParty(
        name: String,
        streetName: String = SenderStreet,
        postalCode: String = SenderPostalCode,
        city: String = SenderCity,
        country: String? = SenderCountry,
        taxNumber: String? = SenderVatId,
        email: String? = SenderEmail,
    ) = Party(name, streetName, postalCode, city, country, taxNumber, email)

    fun createItem(
        name: String = ItemName,
        unit: String = ItemUnit,
        quantity: BigDecimal = ItemQuantity,
        price: BigDecimal = ItemPrice,
        vatPercentage: BigDecimal = ItemVat,
        description: String? = ItemDescription,
    ) = LineItem(name, unit, quantity, price, vatPercentage, description)

}
package net.codinux.invoicing.test

import net.codinux.invoicing.model.BankDetails
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.InvoiceItem
import net.codinux.invoicing.model.Party
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DataGenerator {

    const val InvoiceNumber = "12345"
    val InvoicingDate = LocalDate.of(2015, 10, 21)
    val DueDate = LocalDate.of(2016, 6, 15)

    const val SenderName = "Hochwürdiger Leistungserbringer"
    const val SenderStreet = "Fun Street 1"
    const val SenderPostalCode = "12345"
    const val SenderCity = "Glückstadt"
    const val SenderCountry = "DE"
    const val SenderVatId = "DE123456789"
    const val SenderEmail = "working-class-hero@rock.me"
    const val SenderPhone = "+4917012345678"
    val SenderBankDetails = BankDetails("DE00123456780987654321", "ABZODEFFXXX", "Manuela Musterfrau")

    const val RecipientName = "Untertänigster Leistungsempfänger"
    const val RecipientStreet = "Party Street 1"
    const val RecipientPostalCode = SenderPostalCode
    const val RecipientCity = SenderCity
    const val RecipientCountry = "DE"
    const val RecipientVatId = "DE987654321"
    const val RecipientEmail = "exploiter@your.boss"
    const val RecipientPhone = "+491234567890"
    val RecipientBankDetails: BankDetails? = null

    const val ItemName = "Erbrachte Dienstleistungen"
    val ItemQuantity = BigDecimal(1)
    const val ItemUnit = "HUR" // EN code for 'hour'
    val ItemUnitPrice = BigDecimal(99)
    val ItemVatRate = BigDecimal(19)
    val ItemDescription: String? = null


    fun createInvoice(
        invoiceNumber: String = InvoiceNumber,
        invoicingDate: LocalDate = InvoicingDate,
        sender: Party = createParty(SenderName, SenderStreet, SenderPostalCode, SenderCity, SenderCountry, SenderVatId, SenderEmail, SenderPhone,
            bankDetails = SenderBankDetails),
        recipient: Party = createParty(RecipientName, RecipientStreet, RecipientPostalCode, RecipientCity, RecipientCountry, RecipientVatId, RecipientEmail, RecipientPhone,
            bankDetails = RecipientBankDetails),
        items: List<InvoiceItem> = listOf(createItem()),
        dueDate: LocalDate? = DueDate,
        paymentDescription: String? = dueDate?.let { "Zahlbar ohne Abzug bis ${DateTimeFormatter.ofPattern("dd.MM.yyyy").format(dueDate)}" },
        buyerReference: String? = null
    ) = Invoice(invoiceNumber, invoicingDate, sender, recipient, items, dueDate, paymentDescription, buyerReference)

    fun createParty(
        name: String,
        streetName: String = SenderStreet,
        postalCode: String = SenderPostalCode,
        city: String = SenderCity,
        country: String? = SenderCountry,
        vatId: String? = SenderVatId,
        email: String? = SenderEmail,
        phone: String? = SenderPhone,
        fax: String? = null,
        contactName: String? = null,
        bankDetails: BankDetails? = null
    ) = Party(name, streetName, postalCode, city, country, vatId, email, phone, fax, contactName, bankDetails)

    fun createItem(
        name: String = ItemName,
        quantity: BigDecimal = ItemQuantity,
        unit: String = ItemUnit,
        unitPrice: BigDecimal = ItemUnitPrice,
        vatRate: BigDecimal = ItemVatRate,
        description: String? = ItemDescription,
    ) = InvoiceItem(name, quantity, unit, unitPrice, vatRate, description)

}
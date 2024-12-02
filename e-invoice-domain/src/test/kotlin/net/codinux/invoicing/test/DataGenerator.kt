package net.codinux.invoicing.test

import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DataGenerator {

    const val InvoiceNumber = "12345"
    val InvoiceDate = LocalDate.of(2015, 10, 21)
    val DueDate = LocalDate.of(2016, 6, 15)

    const val SupplierName = "Hochwürdiger Leistungserbringer"
    const val SupplierAddress = "Fun Street 1"
    val SupplierAdditionalAddressLine: String? = null
    const val SupplierPostalCode = "12345"
    const val SupplierCity = "Glückstadt"
    const val SupplierCountry = "DE"
    const val SupplierVatId = "DE123456789"
    const val SupplierEmail = "working-class-hero@rock.me"
    const val SupplierPhone = "+4917012345678"
    val SupplierFax: String? = null
    val SupplierBankDetails = BankDetails("DE00123456780987654321", "ABZODEFFXXX", "Manuela Musterfrau", "Abzock-Bank")

    const val CustomerName = "Untertänigster Leistungsempfänger"
    const val CustomerAddress = "Party Street 1"
    val CustomerAdditionalAddressLine: String? = null
    const val CustomerPostalCode = SupplierPostalCode
    const val CustomerCity = SupplierCity
    const val CustomerCountry = "DE"
    const val CustomerVatId = "DE987654321"
    const val CustomerEmail = "exploiter@your.boss"
    const val CustomerPhone = "+491234567890"
    val CustomerFax: String? = null
    val CustomerBankDetails: BankDetails? = null

    const val ItemName = "Erbrachte Dienstleistungen"
    val ItemQuantity = BigDecimal(1)
    const val ItemUnit = "HUR" // EN code for 'hour'
    val ItemUnitPrice = BigDecimal(99)
    val ItemVatRate = BigDecimal(19)
    val ItemArticleNumber: String? = null
    val ItemDescription: String? = null


    fun createInvoice(
        invoiceNumber: String = InvoiceNumber,
        invoiceDate: LocalDate = InvoiceDate,
        supplier: Party = createParty(SupplierName, SupplierAddress, SupplierAdditionalAddressLine, SupplierPostalCode, SupplierCity, SupplierCountry,
            SupplierVatId, SupplierEmail, SupplierPhone, SupplierFax, bankDetails = SupplierBankDetails),
        customer: Party = createParty(CustomerName, CustomerAddress, CustomerAdditionalAddressLine, CustomerPostalCode, CustomerCity, CustomerCountry,
            CustomerVatId, CustomerEmail, CustomerPhone, CustomerFax, bankDetails = CustomerBankDetails),
        items: List<InvoiceItem> = listOf(createItem()),
        dueDate: LocalDate? = DueDate,
        paymentDescription: String? = dueDate?.let { "Zahlbar ohne Abzug bis ${DateTimeFormatter.ofPattern("dd.MM.yyyy").format(dueDate)}" },
    ) = Invoice(InvoiceDetails(invoiceNumber, invoiceDate, dueDate, paymentDescription), supplier, customer, items).apply {
        this.totals = AmountsCalculator().calculateTotalAmounts(this)
    }

    fun createParty(
        name: String,
        address: String = SupplierAddress,
        additionalAddressLine: String? = SupplierAdditionalAddressLine,
        postalCode: String = SupplierPostalCode,
        city: String = SupplierCity,
        country: String? = SupplierCountry,
        vatId: String? = SupplierVatId,
        email: String? = SupplierEmail,
        phone: String? = SupplierPhone,
        fax: String? = SupplierFax,
        contactName: String? = null,
        bankDetails: BankDetails? = null
    ) = Party(name, address, additionalAddressLine, postalCode, city, country, vatId, email, phone, fax, contactName, bankDetails)

    fun createItem(
        name: String = ItemName,
        quantity: BigDecimal = ItemQuantity,
        unit: String = ItemUnit,
        unitPrice: BigDecimal = ItemUnitPrice,
        vatRate: BigDecimal = ItemVatRate,
        articleNumber: String? = ItemArticleNumber,
        description: String? = ItemDescription,
    ) = InvoiceItem(name, quantity, unit, unitPrice, vatRate, articleNumber, description)

}
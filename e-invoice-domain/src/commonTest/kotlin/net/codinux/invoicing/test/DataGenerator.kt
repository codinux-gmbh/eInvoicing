package net.codinux.invoicing.test

import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.Country
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure

object DataGenerator {

    const val InvoiceNumber = "12345"
    val InvoiceDate = LocalDate(2015, 10, 21)
    val ServicePeriodStart = LocalDate(2015, 10, 1)
    val ServicePeriodEnd = LocalDate(2015, 10, 31)
    val DueDate = LocalDate(2016, 6, 15)

    const val SupplierName = "Hochwürdiger Leistungserbringer"
    const val SupplierAddress = "Fun Street 1"
    val SupplierAdditionalAddressLine: String? = null
    const val SupplierPostalCode = "12345"
    const val SupplierCity = "Glückstadt"
    val SupplierCountry = Country.Germany
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
    val CustomerCountry = SupplierCountry
    const val CustomerVatId = "DE987654321"
    const val CustomerEmail = "exploiter@your.boss"
    const val CustomerPhone = "+491234567890"
    val CustomerFax: String? = null
    val CustomerBankDetails: BankDetails? = null

    const val ItemName = "Erbrachte Dienstleistungen"
    val ItemQuantity = BigDecimal(1)
    val ItemUnit = UnitOfMeasure.HUR
    val ItemUnitPrice = BigDecimal(99)
    val ItemVatRate = BigDecimal(19)
    val ItemArticleNumber: String? = null
    val ItemDescription: String? = null

    val SerializedInvoiceJson = """{"details":{"invoiceNumber":"12345","invoiceDate":{"year":2015,"month":10,"dayOfMonth":21},"dueDate":{"year":2016,"month":6,"dayOfMonth":15},"paymentDescription":"Zahlbar ohne Abzug bis 15.6.2016}"},"supplier":{"name":"Hochwürdiger Leistungserbringer","address":"Fun Street 1","postalCode":"12345","city":"Glückstadt","vatId":"DE123456789","email":"working-class-hero@rock.me","phone":"+4917012345678","bankDetails":{"accountNumber":"DE00123456780987654321","bankCode":"ABZODEFFXXX","accountHolderName":"Manuela Musterfrau","financialInstitutionName":"Abzock-Bank"}},"customer":{"name":"Untertänigster Leistungsempfänger","address":"Party Street 1","postalCode":"12345","city":"Glückstadt","vatId":"DE987654321","email":"exploiter@your.boss","phone":"+491234567890"},"items":[{"name":"Erbrachte Dienstleistungen","quantity":{"value":"1"},"unit":"HUR","unitPrice":{"value":"99"},"vatRate":{"value":"19"}}]}"""


    fun createInvoice(
        invoiceNumber: String = InvoiceNumber,
        invoiceDate: LocalDate = InvoiceDate,
        supplier: Party = createParty(SupplierName, SupplierAddress, SupplierAdditionalAddressLine, SupplierPostalCode, SupplierCity, SupplierCountry,
            SupplierVatId, SupplierEmail, SupplierPhone, SupplierFax, bankDetails = SupplierBankDetails),
        customer: Party = createParty(CustomerName, CustomerAddress, CustomerAdditionalAddressLine, CustomerPostalCode, CustomerCity, CustomerCountry,
            CustomerVatId, CustomerEmail, CustomerPhone, CustomerFax, bankDetails = CustomerBankDetails),
        items: List<InvoiceItem> = listOf(createItem()),
        currency: Currency = Currency.Euro,
        serviceDate: ServiceDate = ServiceDate.ServicePeriod(ServicePeriodStart, ServicePeriodEnd),
        dueDate: LocalDate? = DueDate,
        paymentDescription: String? = dueDate?.let { "Zahlbar ohne Abzug bis ${dueDate.dayOfMonth}.${dueDate.month}.${dueDate.year}}" },
    ) = Invoice(InvoiceDetails(invoiceNumber, invoiceDate, currency, serviceDate, dueDate, paymentDescription), supplier, customer, items).apply {
        this.totals = AmountsCalculator().ensureTotalAmountsIsSet(this)
    }

    fun createParty(
        name: String,
        address: String = SupplierAddress,
        additionalAddressLine: String? = SupplierAdditionalAddressLine,
        postalCode: String = SupplierPostalCode,
        city: String = SupplierCity,
        country: Country = SupplierCountry,
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
        unit: UnitOfMeasure = ItemUnit,
        unitPrice: BigDecimal = ItemUnitPrice,
        vatRate: BigDecimal = ItemVatRate,
        articleNumber: String? = ItemArticleNumber,
        description: String? = ItemDescription,
    ) = InvoiceItem(name, quantity, unit, unitPrice, vatRate, articleNumber, description)

}
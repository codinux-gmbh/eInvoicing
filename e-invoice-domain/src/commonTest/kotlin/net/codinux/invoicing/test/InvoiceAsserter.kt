package net.codinux.invoicing.test

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.Country
import net.codinux.invoicing.model.codes.UnitOfMeasure

object InvoiceAsserter {

    fun assertInvoice(invoice: Invoice?) {
        assertThat(invoice).isNotNull()

        assertThat(invoice!!.details.invoiceNumber).isEqualTo(DataGenerator.InvoiceNumber)
        assertThat(invoice.details.invoiceDate).isEqualTo(DataGenerator.InvoiceDate)

        assertParty(invoice.supplier, DataGenerator.SupplierName, DataGenerator.SupplierAddress, DataGenerator.SupplierPostalCode, DataGenerator.SupplierCity, DataGenerator.SupplierCountry, DataGenerator.SupplierVatId, DataGenerator.SupplierEmail, DataGenerator.SupplierPhone, DataGenerator.SupplierBankDetails)

        assertParty(invoice.customer, DataGenerator.CustomerName, DataGenerator.CustomerAddress, DataGenerator.CustomerPostalCode, DataGenerator.CustomerCity, DataGenerator.CustomerCountry, DataGenerator.CustomerVatId, DataGenerator.CustomerEmail, DataGenerator.CustomerPhone, DataGenerator.CustomerBankDetails)

        assertThat(invoice.items).hasSize(1)
        assertLineItem(invoice.items.first(), DataGenerator.ItemName, DataGenerator.ItemQuantity, DataGenerator.ItemUnit, DataGenerator.ItemUnitPrice, DataGenerator.ItemVatRate, DataGenerator.ItemDescription)
    }

    private fun assertParty(party: Party, name: String, address: String, postalCode: String, city: String, country: Country, vatId: String, email: String, phone: String, bankDetails: BankDetails?) {
        assertThat(party.name).isEqualTo(name)

        assertThat(party.address).isEqualTo(address)
        assertThat(party.postalCode).isEqualTo(postalCode)
        assertThat(party.city).isEqualTo(city)
        assertThat(party.country).isEqualTo(country)

        assertThat(party.vatId).isEqualTo(vatId)

        assertThat(party.email).isEqualTo(email)
        assertThat(party.phone).isEqualTo(phone)

        if (bankDetails == null) {
            assertThat(party.bankDetails).isNull()
        } else {
            assertThat(party.bankDetails!!.accountNumber).isEqualTo(bankDetails.accountNumber)
            assertThat(party.bankDetails!!.bankCode).isEqualTo(bankDetails.bankCode)
            // due to a bug in Mustang accountName doesn't get extracted from XML, see https://github.com/ZUGFeRD/mustangproject/issues/558
//            assertThat(party.bankDetails!!.accountHolderName).isEqualTo(bankDetails.accountHolderName)
        }
    }

    private fun assertLineItem(item: InvoiceItem, name: String, quantity: BigDecimal, unit: UnitOfMeasure, unitPrice: BigDecimal, vatRate: BigDecimal, description: String?) {
        assertThat(item.name).isEqualTo(name)

        assertThat(item.unit).isEqualTo(unit)
        assertThat(item.quantity.setScale(0)).isEqualTo(quantity)

        assertThat(item.unitPrice.setScale(0)).isEqualTo(unitPrice)
        assertThat(item.vatRate.setScale(0)).isEqualTo(vatRate)

//        assertThat(item.description).isEqualTo(description)
    }

}
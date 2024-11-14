package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import net.codinux.invoicing.model.BankDetails
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.LineItem
import net.codinux.invoicing.model.Party
import net.codinux.invoicing.test.DataGenerator
import java.io.InputStream
import java.math.BigDecimal
import kotlin.test.Test

class EInvoiceReaderTest {

    private val underTest = EInvoiceReader()


    @Test
    fun readFromXml() {
        val result = underTest.readFromXml(getTestFile("XRechnung.xml"))

        assertInvoice(result)
    }

    @Test
    fun extractFromPdf() {
        val result = underTest.extractFromPdf(getTestFile("ZUGFeRD.pdf"))

        assertInvoice(result)
    }


    private fun getTestFile(filename: String): InputStream =
        this.javaClass.classLoader.getResourceAsStream("files/$filename")!!

    private fun assertInvoice(invoice: Invoice?) {
        assertThat(invoice).isNotNull()

        assertThat(invoice!!.invoiceNumber).isEqualTo(DataGenerator.InvoiceNumber)
        assertThat(invoice.invoicingDate).isEqualTo(DataGenerator.InvoicingDate)

        assertParty(invoice.sender, DataGenerator.SenderName, DataGenerator.SenderStreet, DataGenerator.SenderPostalCode, DataGenerator.SenderCity, DataGenerator.SenderCountry, DataGenerator.SenderVatId, DataGenerator.SenderEmail, DataGenerator.SenderPhone, DataGenerator.SenderBankDetails)

        assertParty(invoice.recipient, DataGenerator.RecipientName, DataGenerator.RecipientStreet, DataGenerator.RecipientPostalCode, DataGenerator.RecipientCity, DataGenerator.RecipientCountry, DataGenerator.RecipientVatId, DataGenerator.RecipientEmail, DataGenerator.RecipientPhone, DataGenerator.RecipientBankDetails)

        assertThat(invoice.items).hasSize(1)
        assertLineItem(invoice.items.first(), DataGenerator.ItemName, DataGenerator.ItemUnit, DataGenerator.ItemQuantity, DataGenerator.ItemPrice, DataGenerator.ItemVatPercentage, DataGenerator.ItemDescription)
    }

    private fun assertParty(party: Party, name: String, street: String, postalCode: String, city: String, country: String?, vatId: String, email: String, phone: String, bankDetails: BankDetails?) {
        assertThat(party.name).isEqualTo(name)

        assertThat(party.street).isEqualTo(street)
        assertThat(party.postalCode).isEqualTo(postalCode)
        assertThat(party.city).isEqualTo(city)
        assertThat(party.countryIsoCode).isEqualTo(country)

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

    private fun assertLineItem(item: LineItem, name: String, unit: String, quantity: BigDecimal, price: BigDecimal, vatPercentage: BigDecimal, description: String?) {
        assertThat(item.name).isEqualTo(name)

        assertThat(item.unit).isEqualTo(unit)
        assertThat(item.quantity).isEqualTo(quantity.setScale(4))

        assertThat(item.price).isEqualTo(price.setScale(4))
        assertThat(item.vatPercentage).isEqualTo(vatPercentage.setScale(2))

//        assertThat(item.description).isEqualTo(description)
    }

}
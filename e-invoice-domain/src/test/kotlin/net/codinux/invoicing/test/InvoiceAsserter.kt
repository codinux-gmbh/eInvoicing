package net.codinux.invoicing.test

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.model.BankDetails
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.InvoiceItem
import net.codinux.invoicing.model.Party
import java.math.BigDecimal

object InvoiceAsserter {

    fun assertInvoiceXml(xml: String) {
        assertThat(xml).isNotEmpty()

        val asserter = XPathAsserter(xml)

        asserter.xpathHasValue("//rsm:ExchangedDocument/ram:ID", DataGenerator.InvoiceNumber)
        asserter.xpathHasValue("//rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString", DataGenerator.InvoicingDate.toString().replace("-", ""))

        val senderXPath = "//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty"
        assertParty(asserter, senderXPath, DataGenerator.SenderName, DataGenerator.SenderStreet, DataGenerator.SenderPostalCode, DataGenerator.SenderCity, DataGenerator.SenderVatId, DataGenerator.SenderEmail, DataGenerator.SenderPhone)

        val receiverXPath = "//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty"
        assertParty(asserter, receiverXPath, DataGenerator.RecipientName, DataGenerator.RecipientStreet, DataGenerator.RecipientPostalCode, DataGenerator.RecipientCity, DataGenerator.RecipientVatId, DataGenerator.RecipientEmail, DataGenerator.RecipientPhone)

        val lineItemXPath = "//rsm:SupplyChainTradeTransaction/ram:IncludedSupplyChainTradeLineItem"
        assertLineItem(asserter, lineItemXPath, DataGenerator.ItemName, DataGenerator.ItemQuantity, DataGenerator.ItemUnit, DataGenerator.ItemUnitPrice, DataGenerator.ItemVatRate, DataGenerator.ItemDescription)
    }

    private fun assertParty(asserter: XPathAsserter, partyXPath: String, name: String, street: String, postalCode: String, city: String, vatId: String, email: String, phone: String) {
        asserter.xpathHasValue("$partyXPath/ram:Name", name)

        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:LineOne", street)
        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:PostcodeCode", postalCode)
        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:CityName", city)

        asserter.xpathHasValue("$partyXPath/ram:SpecifiedTaxRegistration/ram:ID", vatId)

        asserter.xpathHasValue("$partyXPath/ram:URIUniversalCommunication/ram:URIID", email)
        asserter.xpathHasValue("$partyXPath/ram:DefinedTradeContact/ram:EmailURIUniversalCommunication/ram:URIID", email)
        asserter.xpathHasValue("$partyXPath/ram:DefinedTradeContact/ram:TelephoneUniversalCommunication/ram:CompleteNumber", phone)
    }

    private fun assertLineItem(asserter: XPathAsserter, itemXPath: String, name: String, quantity: BigDecimal, unit: String, unitPrice: BigDecimal, vatRate: BigDecimal, description: String?) {
        asserter.xpathHasValue("$itemXPath/ram:SpecifiedTradeProduct/ram:Name", name)

        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeDelivery/ram:BilledQuantity/@unitCode", unit)
        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeDelivery/ram:BilledQuantity", quantity, 4)

        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeSettlement/ram:SpecifiedTradeSettlementLineMonetarySummation/ram:LineTotalAmount", unitPrice, 2)
        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeSettlement/ram:ApplicableTradeTax/ram:RateApplicablePercent",
            vatRate, 2)

//        asserter.xpathHasValue("$partyXPath/ram:URIUniversalCommunication/ram:URIID", description)
    }


    fun assertInvoice(invoice: Invoice?) {
        assertThat(invoice).isNotNull()

        assertThat(invoice!!.invoiceNumber).isEqualTo(DataGenerator.InvoiceNumber)
        assertThat(invoice.invoicingDate).isEqualTo(DataGenerator.InvoicingDate)

        assertParty(invoice.sender, DataGenerator.SenderName, DataGenerator.SenderStreet, DataGenerator.SenderPostalCode, DataGenerator.SenderCity, DataGenerator.SenderCountry, DataGenerator.SenderVatId, DataGenerator.SenderEmail, DataGenerator.SenderPhone, DataGenerator.SenderBankDetails)

        assertParty(invoice.recipient, DataGenerator.RecipientName, DataGenerator.RecipientStreet, DataGenerator.RecipientPostalCode, DataGenerator.RecipientCity, DataGenerator.RecipientCountry, DataGenerator.RecipientVatId, DataGenerator.RecipientEmail, DataGenerator.RecipientPhone, DataGenerator.RecipientBankDetails)

        assertThat(invoice.items).hasSize(1)
        assertLineItem(invoice.items.first(), DataGenerator.ItemName, DataGenerator.ItemQuantity, DataGenerator.ItemUnit, DataGenerator.ItemUnitPrice, DataGenerator.ItemVatRate, DataGenerator.ItemDescription)
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

    private fun assertLineItem(item: InvoiceItem, name: String, quantity: BigDecimal, unit: String, unitPrice: BigDecimal, vatRate: BigDecimal, description: String?) {
        assertThat(item.name).isEqualTo(name)

        assertThat(item.unit).isEqualTo(unit)
        assertThat(item.quantity).isEqualTo(quantity.setScale(4))

        assertThat(item.unitPrice).isEqualTo(unitPrice.setScale(4))
        assertThat(item.vatRate).isEqualTo(vatRate.setScale(2))

//        assertThat(item.description).isEqualTo(description)
    }

}
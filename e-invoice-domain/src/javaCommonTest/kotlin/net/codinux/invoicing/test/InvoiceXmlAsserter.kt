package net.codinux.invoicing.test

import assertk.assertThat
import assertk.assertions.*
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.UnitOfMeasure

object InvoiceXmlAsserter {

    fun assertInvoiceXml(xml: String?) {
        assertThat(xml).isNotNull()
        assertThat(xml!!).isNotEmpty()

        val asserter = XPathAsserter(xml)

        asserter.xpathHasValue("//rsm:ExchangedDocument/ram:ID", DataGenerator.InvoiceNumber)
        asserter.xpathHasValue("//rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString", DataGenerator.InvoiceDate.toString().replace("-", ""))

        val supplierXPath = "//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty"
        assertParty(asserter, supplierXPath, DataGenerator.SupplierName, DataGenerator.SupplierAddress, DataGenerator.SupplierPostalCode, DataGenerator.SupplierCity, DataGenerator.SupplierVatId, DataGenerator.SupplierEmail, DataGenerator.SupplierPhone)

        val customerXPath = "//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty"
        assertParty(asserter, customerXPath, DataGenerator.CustomerName, DataGenerator.CustomerAddress, DataGenerator.CustomerPostalCode, DataGenerator.CustomerCity, DataGenerator.CustomerVatId, DataGenerator.CustomerEmail, DataGenerator.CustomerPhone)

        val lineItemXPath = "//rsm:SupplyChainTradeTransaction/ram:IncludedSupplyChainTradeLineItem"
        assertLineItem(asserter, lineItemXPath, DataGenerator.ItemName, DataGenerator.ItemQuantity, DataGenerator.ItemUnit, DataGenerator.ItemUnitPrice, DataGenerator.ItemVatRate, DataGenerator.ItemDescription)
    }

    private fun assertParty(asserter: XPathAsserter, partyXPath: String, name: String, address: String, postalCode: String, city: String, vatId: String, email: String, phone: String) {
        asserter.xpathHasValue("$partyXPath/ram:Name", name)

        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:LineOne", address)
        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:PostcodeCode", postalCode)
        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:CityName", city)

        asserter.xpathHasValue("$partyXPath/ram:SpecifiedTaxRegistration/ram:ID", vatId)

        asserter.xpathHasValue("$partyXPath/ram:URIUniversalCommunication/ram:URIID", email)
        asserter.xpathHasValue("$partyXPath/ram:DefinedTradeContact/ram:EmailURIUniversalCommunication/ram:URIID", email)
        asserter.xpathHasValue("$partyXPath/ram:DefinedTradeContact/ram:TelephoneUniversalCommunication/ram:CompleteNumber", phone)
    }

    private fun assertLineItem(asserter: XPathAsserter, itemXPath: String, name: String, quantity: BigDecimal, unit: UnitOfMeasure, unitPrice: BigDecimal, vatRate: BigDecimal, description: String?) {
        asserter.xpathHasValue("$itemXPath/ram:SpecifiedTradeProduct/ram:Name", name)

        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeDelivery/ram:BilledQuantity/@unitCode", unit.code)
        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeDelivery/ram:BilledQuantity", quantity, 4)

        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeSettlement/ram:SpecifiedTradeSettlementLineMonetarySummation/ram:LineTotalAmount", unitPrice, 2)
        asserter.xpathHasValue("$itemXPath/ram:SpecifiedLineTradeSettlement/ram:ApplicableTradeTax/ram:RateApplicablePercent",
            vatRate, 2)

//        asserter.xpathHasValue("$partyXPath/ram:URIUniversalCommunication/ram:URIID", description)
    }

}
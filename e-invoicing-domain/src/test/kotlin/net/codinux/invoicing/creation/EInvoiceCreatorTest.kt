package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isNotEmpty
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.XPathAsserter
import java.math.BigDecimal
import kotlin.test.Test

class EInvoiceCreatorTest {

    private val underTest = EInvoiceCreator()


    @Test
    fun createXRechnung() {
        val invoice = createInvoice()

        val result = underTest.createXRechnungXml(invoice)

        assertInvoiceXml(result)
    }

    @Test
    fun createZugferdXml() {
        val invoice = createInvoice()

        val result = underTest.createZugferdXml(invoice)

        assertInvoiceXml(result)
    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private fun assertInvoiceXml(xml: String) {
        assertThat(xml).isNotEmpty()

        val asserter = XPathAsserter(xml)

        asserter.xpathHasValue("//rsm:ExchangedDocument/ram:ID", DataGenerator.InvoiceNumber)
        asserter.xpathHasValue("//rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString", DataGenerator.InvoicingDate.toString().replace("-", ""))

        val senderXPath = "//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty"
        assertParty(asserter, senderXPath, DataGenerator.SenderName, DataGenerator.SenderStreet, DataGenerator.SenderPostalCode, DataGenerator.SenderCity, DataGenerator.SenderVatId, DataGenerator.SenderEmail)

        val receiverXPath = "//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty"
        assertParty(asserter, receiverXPath, DataGenerator.RecipientName, DataGenerator.RecipientStreet, DataGenerator.RecipientPostalCode, DataGenerator.RecipientCity, DataGenerator.RecipientVatId, DataGenerator.RecipientEmail)

        val lineItemXPath = "//rsm:SupplyChainTradeTransaction/ram:IncludedSupplyChainTradeLineItem"
        assertLineItem(asserter, lineItemXPath, DataGenerator.ItemName, DataGenerator.ItemUnit, DataGenerator.ItemQuantity, DataGenerator.ItemPrice, DataGenerator.ItemVat, DataGenerator.ItemDescription)
    }

    private fun assertParty(asserter: XPathAsserter, partyXPath: String, name: String, street: String, postalCode: String, city: String, vatId: String, email: String) {
        asserter.xpathHasValue("$partyXPath/ram:Name", name)

        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:LineOne", street)
        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:PostcodeCode", postalCode)
        asserter.xpathHasValue("$partyXPath/ram:PostalTradeAddress/ram:CityName", city)

        asserter.xpathHasValue("$partyXPath/ram:SpecifiedTaxRegistration/ram:ID", vatId)

        asserter.xpathHasValue("$partyXPath/ram:URIUniversalCommunication/ram:URIID", email)
    }

    private fun assertLineItem(asserter: XPathAsserter, partyXPath: String, name: String, unit: String, quantity: BigDecimal, price: BigDecimal, vatPercentage: BigDecimal, description: String?) {
        asserter.xpathHasValue("$partyXPath/ram:SpecifiedTradeProduct/ram:Name", name)

        asserter.xpathHasValue("$partyXPath/ram:SpecifiedLineTradeDelivery/ram:BilledQuantity/@unitCode", unit)
        asserter.xpathHasValue("$partyXPath/ram:SpecifiedLineTradeDelivery/ram:BilledQuantity", quantity, 4)

        asserter.xpathHasValue("$partyXPath/ram:SpecifiedLineTradeSettlement/ram:SpecifiedTradeSettlementLineMonetarySummation/ram:LineTotalAmount", price, 2)
        asserter.xpathHasValue("$partyXPath/ram:SpecifiedLineTradeSettlement/ram:ApplicableTradeTax/ram:RateApplicablePercent", vatPercentage, 2)

//        asserter.xpathHasValue("$partyXPath/ram:URIUniversalCommunication/ram:URIID", description)
    }

}
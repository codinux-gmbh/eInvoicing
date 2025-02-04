package net.codinux.invoicing.model.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoicingStandard
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.ServiceDate
import net.codinux.invoicing.model.cii.lenient.*
import kotlin.test.Test

class CiiMapperTest {

    companion object {
        private val EN16931Profile = EInvoiceFormatDetectionResult(EInvoicingStandard.CII, EInvoiceFormat.FacturX, profile = FacturXProfile.EN16931)
    }

    private val underTest = CiiMapper()


    @Test
    fun mapServicePeriod() {
        val startDate = LocalDate(2025, 2, 1)
        val endDate = LocalDate(2025, 2, 28)
        val invoice = createCiiInvoice(servicePeriodStart = "20250201", servicePeriodEnd = "20250228")

        val result = underTest.map(invoice, EN16931Profile)

        assertThat(result.invoice?.invoice).isNotNull()
        val createdInvoice = result.invoice!!.invoice

        assertThat(createdInvoice.details.serviceDate is ServiceDate.ServicePeriod).isTrue()
        assertThat(createdInvoice.details.serviceDate!!.asServicePeriod()!!.startDate).isEqualTo(startDate)
        assertThat(createdInvoice.details.serviceDate!!.asServicePeriod()!!.endDate).isEqualTo(endDate)
    }

    @Test
    fun mapDeliveryDate() {
        val deliveryDate = LocalDate(2025, 2, 17)
        val invoice = createCiiInvoice(deliveryDate = "20250217")

        val result = underTest.map(invoice, EN16931Profile)

        assertThat(result.invoice?.invoice).isNotNull()
        val createdInvoice = result.invoice!!.invoice

        assertThat(createdInvoice.details.serviceDate is ServiceDate.DeliveryDate).isTrue()
        assertThat(createdInvoice.details.serviceDate!!.asDeliveryDate()!!.deliveryDate).isEqualTo(deliveryDate)
    }


    private fun createCiiInvoice(
        invoiceNumber: String = "RE-12345",
        invoiceDate: String = "20151021",
        supplier: TradeParty = TradeParty(name = Text("Supplier"), postalTradeAddress = TradeAddress(countryID = CountryID("DE"))),
        customer: TradeParty = TradeParty(name = Text("Customer"), postalTradeAddress = TradeAddress(countryID = CountryID("DE"))),
        deliveryDate: String? = null,
        servicePeriodStart: String? = null,
        servicePeriodEnd: String? = null,
    ) = CrossIndustryInvoice(
        ExchangedDocumentContext(),
        ExchangedDocument(ID(invoiceNumber), typeCode = DocumentCode("380"), issueDateTime = mapDate(invoiceDate)),
        SupplyChainTradeTransaction(
            applicableHeaderTradeDelivery = HeaderTradeDelivery(actualDeliverySupplyChainEvent = SupplyChainEvent(occurrenceDateTime = deliveryDate?.let { mapDate(it) })),
            applicableHeaderTradeSettlement = HeaderTradeSettlement(
                billingSpecifiedPeriod = SpecifiedPeriod(startDateTime = servicePeriodStart?.let { mapDate(it) }, endDateTime =  servicePeriodEnd?.let { mapDate(it) })
            ),
            applicableHeaderTradeAgreement = HeaderTradeAgreement(
                sellerTradeParty = supplier,
                buyerTradeParty = customer,
            ),
        )
    )

    private fun mapDate(dateTimeString: String) = DateTime(DateTimeString(dateTimeString, "102"))
}
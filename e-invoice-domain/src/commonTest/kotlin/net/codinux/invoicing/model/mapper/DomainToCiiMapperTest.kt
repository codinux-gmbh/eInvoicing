package net.codinux.invoicing.model.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.ServiceDate
import net.codinux.invoicing.model.toIsoDate
import net.codinux.invoicing.test.DataGenerator
import kotlin.test.Test

class DomainToCiiMapperTest {

    private val underTest = DomainToCiiMapper()


    @Test
    fun mapServicePeriod() {
        val startDate = LocalDate(2025, 2, 1)
        val endDate = LocalDate(2025, 2, 28)
        val invoice = DataGenerator.createInvoice(serviceDate = ServiceDate.ServicePeriod(startDate, endDate))

        val result = underTest.mapInvoice(invoice)

        assertThat(result.supplyChainTradeTransaction.applicableHeaderTradeDelivery.actualDeliverySupplyChainEvent?.occurrenceDateTime).isNull() // deliveryDate may not be set
        val billingPeriod = result.supplyChainTradeTransaction.applicableHeaderTradeSettlement.billingSpecifiedPeriod
        assertThat(billingPeriod?.startDateTime?.dateTimeString?.value).isEqualTo(startDate.toIsoDate().replace("-", ""))
        assertThat(billingPeriod?.endDateTime?.dateTimeString?.value).isEqualTo(endDate.toIsoDate().replace("-", ""))
    }

    @Test
    fun mapDeliveryDate() {
        val deliveryDate = LocalDate(2025, 2, 17)
        val invoice = DataGenerator.createInvoice(serviceDate = ServiceDate.DeliveryDate(deliveryDate))

        val result = underTest.mapInvoice(invoice)

        val occurrenceDate = result.supplyChainTradeTransaction.applicableHeaderTradeDelivery.actualDeliverySupplyChainEvent?.occurrenceDateTime
        assertThat(occurrenceDate?.dateTimeString?.value).isEqualTo(deliveryDate.toIsoDate().replace("-", ""))
        val billingPeriod = result.supplyChainTradeTransaction.applicableHeaderTradeSettlement.billingSpecifiedPeriod
        assertThat(billingPeriod?.startDateTime?.dateTimeString).isNull() // servicePeriod may not be set
        assertThat(billingPeriod?.endDateTime?.dateTimeString).isNull()
    }

}
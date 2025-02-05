package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.ServiceDate
import kotlin.test.Test

class ServiceDateSerializerTest {

    companion object {
        const val ExpectedDeliveryDateJson = """{"type":"net.codinux.invoicing.model.ServiceDate.DeliveryDate","deliveryDate":{"year":2015,"month":10,"dayOfMonth":21}}"""
        const val ExpectedServicePeriodJson = """{"type":"net.codinux.invoicing.model.ServiceDate.ServicePeriod","startDate":{"year":2015,"month":10,"dayOfMonth":1},"endDate":{"year":2015,"month":10,"dayOfMonth":31}}"""
    }


    @Test
    fun serializeDeliveryDate() {
        val result = Json.encodeToString(ServiceDate.DeliveryDate(LocalDate(2015, 10, 21)) as ServiceDate)

        assertThat(result).isEqualTo(ExpectedDeliveryDateJson)
    }

    @Test
    fun deserializeDeliveryDate() {
        val result = Json.decodeFromString<ServiceDate>(ExpectedDeliveryDateJson)

        assertThat(result is ServiceDate.DeliveryDate).isTrue()
        assertThat(result.asDeliveryDate()!!.deliveryDate).isEqualTo(LocalDate(2015, 10, 21))
    }


    @Test
    fun serializeServicePeriod() {
        val result = Json.encodeToString(ServiceDate.ServicePeriod(LocalDate(2015, 10, 1), LocalDate(2015, 10, 31)) as ServiceDate)

        assertThat(result).isEqualTo(ExpectedServicePeriodJson)
    }

    @Test
    fun deserializeServicePeriod() {
        val result = Json.decodeFromString<ServiceDate>(ExpectedServicePeriodJson)

        assertThat(result is ServiceDate.ServicePeriod).isTrue()
        assertThat(result.asServicePeriod()!!.startDate).isEqualTo(LocalDate(2015, 10, 1))
        assertThat(result.asServicePeriod()!!.endDate).isEqualTo(LocalDate(2015, 10, 31))
    }

}
package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.fasterxml.jackson.module.kotlin.readValue
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.ServiceDate
import net.codinux.invoicing.serialization.ServiceDateSerializerTest.Companion.ExpectedDeliveryDateJson
import net.codinux.invoicing.serialization.ServiceDateSerializerTest.Companion.ExpectedServicePeriodJson
import net.codinux.invoicing.test.TestInstances
import kotlin.test.Test

class ServiceDateSerializerTestJvm {

    private val objectMapper = TestInstances.objectMapper


    @Test
    fun serializeDeliveryDate() {
        val result = objectMapper.writeValueAsString(ServiceDate.DeliveryDate(LocalDate(2015, 10, 21)) as ServiceDate)

        assertThat(result).isEqualTo(ExpectedDeliveryDateJson)
    }

    @Test
    fun deserializeDeliveryDate() {
        val result = objectMapper.readValue<ServiceDate>(ExpectedDeliveryDateJson)

        assertThat(result is ServiceDate.DeliveryDate).isTrue()
        assertThat(result.asDeliveryDate()!!.deliveryDate).isEqualTo(LocalDate(2015, 10, 21))
    }


    @Test
    fun serializeServicePeriod() {
        val result = objectMapper.writeValueAsString(ServiceDate.ServicePeriod(LocalDate(2015, 10, 1), LocalDate(2015, 10, 31)) as ServiceDate)

        assertThat(result).isEqualTo(ExpectedServicePeriodJson)
    }

    @Test
    fun deserializeServicePeriod() {
        val result = objectMapper.readValue<ServiceDate>(ExpectedServicePeriodJson)

        assertThat(result is ServiceDate.ServicePeriod).isTrue()
        assertThat(result.asServicePeriod()!!.startDate).isEqualTo(LocalDate(2015, 10, 1))
        assertThat(result.asServicePeriod()!!.endDate).isEqualTo(LocalDate(2015, 10, 31))
    }

}
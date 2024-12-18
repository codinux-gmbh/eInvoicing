package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.fasterxml.jackson.module.kotlin.readValue
import net.codinux.invoicing.model.Instant
import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.toEInvoicingInstant
import net.codinux.invoicing.test.TestInstances
import kotlin.test.Test

class InstantSerializationTestJvm {

    companion object {
        private val ExpectedInstant = LocalDate(2015, 10, 22).toJvmInstantAtSystemDefaultZone().toEInvoicingInstant()

        private val ExpectedSerializedJson = """{"value":1445464800.000000000}"""
    }


    private val objectMapper = TestInstances.objectMapper


    @Test
    fun serialize() {
        val result = objectMapper.writeValueAsString(ExpectedInstant)

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = objectMapper.readValue<Instant>(ExpectedSerializedJson)

        assertThat(result).isEqualTo(ExpectedInstant)
    }

}
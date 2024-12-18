package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import net.codinux.invoicing.model.BigDecimal
import kotlin.test.Test

class BigDecimalSerializationTestJvm {

    companion object {
        private val ExpectedBigDecimal = BigDecimal(123)

        private val ExpectedSerializedJson = """{"value":123}"""
    }


    private val objectMapper = ObjectMapper().apply {
        findAndRegisterModules()

        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }


    @Test
    fun serialize() {
        val result = objectMapper.writeValueAsString(ExpectedBigDecimal)

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = objectMapper.readValue<BigDecimal>(ExpectedSerializedJson)

        assertThat(result).isEqualTo(ExpectedBigDecimal)
    }

}
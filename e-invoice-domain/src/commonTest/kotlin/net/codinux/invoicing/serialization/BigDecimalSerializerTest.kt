package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codinux.invoicing.model.BigDecimal
import kotlin.test.Test

class BigDecimalSerializerTest {

    companion object {
        private val ExpectedBigDecimal = BigDecimal(123)

        private val ExpectedSerializedJson = """{"value":"123"}"""
    }


    @Test
    fun serialize() {
        val result = Json.encodeToString(ExpectedBigDecimal)

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = Json.decodeFromString<BigDecimal>(ExpectedSerializedJson)

        assertThat(result).isEqualTo(ExpectedBigDecimal)
    }

}
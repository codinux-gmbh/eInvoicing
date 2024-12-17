package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codinux.invoicing.model.LocalDate
import kotlin.test.Test

class LocalDateSerializerTest {

    companion object {
        private val ExpectedLocalDate = LocalDate(2015, 10, 22)

        private val ExpectedSerializedJson = """{"year":2015,"month":10,"dayOfMonth":22}"""
    }


    @Test
    fun serialize() {
        val result = Json.encodeToString(ExpectedLocalDate)

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = Json.decodeFromString<LocalDate>(ExpectedSerializedJson)

        assertThat(result).isEqualTo(ExpectedLocalDate)
    }

}
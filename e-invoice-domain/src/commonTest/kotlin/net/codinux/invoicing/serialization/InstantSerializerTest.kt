package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codinux.invoicing.model.Instant
import kotlin.test.Test

class InstantSerializerTest {

    companion object {
        private val ExpectedInstant = Instant(47, 11)

        private val ExpectedSerializedJson = """{"epochSeconds":47,"nanosecondsOfSecond":11}"""
    }


    @Test
    fun serialize() {
        val result = Json.encodeToString(ExpectedInstant)

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = Json.decodeFromString<Instant>(ExpectedSerializedJson)

        assertThat(result).isEqualTo(ExpectedInstant)
    }

}
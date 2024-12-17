package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceAsserter
import kotlin.test.Test

class InvoiceSerializerTest {

    companion object {
        private val ExpectedSerializedJson = DataGenerator.SerializedInvoiceJson
    }

    @Test
    fun serialize() {
        val result = Json.encodeToString(DataGenerator.createInvoice())

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = Json.decodeFromString<Invoice>(ExpectedSerializedJson)

        InvoiceAsserter.assertInvoice(result)
    }

}
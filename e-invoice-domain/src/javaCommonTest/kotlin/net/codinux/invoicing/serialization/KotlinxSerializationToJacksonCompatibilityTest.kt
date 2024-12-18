package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.InvoiceItem
import net.codinux.invoicing.model.codes.UnitOfMeasure
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestInstances
import kotlin.test.Test

/**
 * Ensures that objects serialized with Jackson as e.g. done by the REST API can be deserialized by kotlinx-serialization
 */
class KotlinxSerializationToJacksonCompatibilityTest {

    private val objectMapper = TestInstances.objectMapper

    private val json = TestInstances.json


    @Test
    fun defaultInvoice() {
        val invoice = DataGenerator.createInvoice().apply {
            this.totals = AmountsCalculator().calculateTotalAmounts(this)
        }


        val encodedWithJackson = objectMapper.writeValueAsString(invoice)

        val decodedFromKotlinxSerialization = json.decodeFromString<Invoice>(encodedWithJackson)


        InvoiceAsserter.assertInvoice(decodedFromKotlinxSerialization) // decoding worked without exception and all values are correct
    }

    @Test
    fun nichtDarstellbarFuerDoubles() {
        val invoice = DataGenerator.createInvoice(items = listOf(InvoiceItem("", BigDecimal("6.9"), UnitOfMeasure.HUR, BigDecimal("33.33"), BigDecimal(10)))).apply {
            this.totals = AmountsCalculator().calculateTotalAmounts(this)
        }


        val encodedWithJackson = objectMapper.writeValueAsString(invoice)

        val decodedFromKotlinxSerialization = json.decodeFromString<Invoice>(encodedWithJackson)


        assertThat(decodedFromKotlinxSerialization).isNotNull()
        assertThat(decodedFromKotlinxSerialization.items).hasSize(1)

        val lineItem = decodedFromKotlinxSerialization.items.first()
        assertThat(lineItem.unitPrice.toPlainString()).isEqualTo("33.33")
    }

}
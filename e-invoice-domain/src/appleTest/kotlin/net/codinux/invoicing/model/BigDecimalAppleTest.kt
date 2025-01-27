package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import platform.Foundation.NSRoundingMode
import kotlin.test.Test

class BigDecimalAppleTest {

    @Test
    fun power() {
        val result = BigDecimal("0.11").pow(2)

        assertEquals(result, "0.0121")
    }

    @Test
    fun round_Up() {
        val result = BigDecimal("123.456").round(2, NSRoundingMode.NSRoundUp)

        assertEquals(result, "123.46")
    }

    @Test
    fun round_Down() {
        val result = BigDecimal("123.456").round(2, NSRoundingMode.NSRoundDown)

        assertEquals(result, "123.45")
    }

    @Test
    fun scale() {
        val result = BigDecimal("123.456789").setScale(2)

        assertEquals(result, "123.46")
    }

    @Test
    fun toFixed() {
        val result = BigDecimal("1.23456789e4").toFixed(2)

        assertThat(result).isEqualTo("12345.68")
    }


    private fun assertEquals(actual: BigDecimal, expected: String) {
        assertEquals(actual, BigDecimal(expected))
    }

    private fun assertEquals(actual: BigDecimal, expected: BigDecimal) {
        assertThat(actual).isEqualTo(expected)
        assertThat(actual.toPlainString()).isEqualTo(expected.toPlainString())
    }

}
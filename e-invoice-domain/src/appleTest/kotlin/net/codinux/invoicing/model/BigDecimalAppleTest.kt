package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import platform.Foundation.NSRoundingMode
import kotlin.test.Test

class BigDecimalAppleTest {

    @Test
    fun plus() {
        val result = BigDecimal("0.33").plus(BigDecimal("0.66"))

        assertEquals(result, "0.99")
    }

    @Test
    fun minus() {
        val result = BigDecimal("0.66").minus(BigDecimal("0.33"))

        assertEquals(result, "0.33")
    }

    @Test
    fun times() {
        val result = BigDecimal("0.33").times(BigDecimal("3"))

        assertEquals(result, "0.99")
    }

    @Test
    fun divide() {
        val result = BigDecimal("0.66").divide(BigDecimal("2"))

        assertEquals(result, "0.33")
    }

    @Test
    fun modulo() {
        val result = BigDecimal("3").modulo(BigDecimal("2"))

        assertEquals(result, "1")
    }

    @Test
    fun isNegative_PositiveNumber() {
        val result = BigDecimal("0.33").isNegative

        assertThat(result).isFalse()
    }

    @Test
    fun isNegative_NegativeNumber() {
        val result = BigDecimal("-0.33").isNegative

        assertThat(result).isTrue()
    }

    @Test
    fun negated() {
        val result = BigDecimal("0.33").negated()

        assertEquals(result, "-0.33")
    }

    @Test
    fun absolute() {
        val result = BigDecimal("-0.33").abs()

        assertEquals(result, "0.33")
    }

    @Test
    fun power() {
        val result = BigDecimal("0.11").pow(2)

        assertEquals(result, "0.0121")
    }

    @Test
    fun toInt() {
        val result = BigDecimal("123.456").toInt()

        assertThat(result).isEqualTo(123)
    }

    @Test
    fun toDouble() {
        val result = BigDecimal("123.5").toDouble()

        assertThat(result).isEqualTo(123.5)
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
    fun toPlainString() {
        val result = BigDecimal("1.23456789e4").toPlainString()

        assertThat(result).isEqualTo("12345.6789")
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
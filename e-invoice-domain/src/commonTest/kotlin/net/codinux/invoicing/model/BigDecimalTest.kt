package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import kotlin.test.Test

class BigDecimalTest {

    @Test
    fun plus() {
        val result = BigDecimal("0.33") + BigDecimal("0.66")

        assertEquals(result, "0.99")
    }

    @Test
    fun minus() {
        val result = BigDecimal("0.66") - BigDecimal("0.33")

        assertEquals(result, "0.33")
    }

    @Test
    fun times() {
        val result = BigDecimal("0.33") * BigDecimal("3")

        assertEquals(result, "0.99")
    }

    @Test
    fun divide() {
        val result = BigDecimal("0.66") / BigDecimal("2")

        assertEquals(result, "0.33")
    }

    @Test
    fun modulo() {
        val result = BigDecimal("3") % 2

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
    fun toPlainString() {
        val result = BigDecimal("1.23456789e4").toPlainString()

        assertThat(result).isEqualTo("12345.6789")
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
    fun compareNonBigDecimalZeroToBigDecimalZero() {
        val underTest = BigDecimal("1")

        assertThat(underTest != BigDecimal.Zero).isTrue()
    }

    @Test
    fun compareBigDecimalZeroToBigDecimalZero() {
        val underTest = BigDecimal("0")

        assertThat(underTest == BigDecimal.Zero).isTrue()
    }


    @Test
    fun setScale_NewScaleLessThanCurrentScale() {
        val underTest = BigDecimal("1.00")

        val result = underTest.setScale(1)

        assertThat(result).isEqualTo(BigDecimal("1.0"))
    }

    @Test
    fun setScale_NewScaleZero() {
        val underTest = BigDecimal("1.00")

        val result = underTest.setScale(0)

        assertThat(result).isEqualTo(BigDecimal("1"))
    }

    @Test
    fun setScale_NewScaleGreaterThanCurrentScale() {
        val underTest = BigDecimal("1.00")

        val result = underTest.setScale(4)

        assertThat(result).isEqualTo(BigDecimal("1.0000"))
    }

    @Test
    fun setScale_BigDecimalWithoutFractionalDigits() {
        val underTest = BigDecimal("1")

        val result = underTest.setScale(2)

        assertThat(result).isEqualTo(BigDecimal("1.00"))
    }

    @Test
    fun setScale_BigDecimalWithoutFractionalDigits_SetScaleToZero() {
        val underTest = BigDecimal("1")

        val result = underTest.setScale(0)

        assertThat(result).isEqualTo(BigDecimal("1"))
    }


    private fun assertEquals(actual: BigDecimal, expected: String) {
        assertEquals(actual, BigDecimal(expected))
    }

    private fun assertEquals(actual: BigDecimal, expected: BigDecimal) {
        assertThat(actual).isEqualTo(expected)
        assertThat(actual.toPlainString()).isEqualTo(expected.toPlainString())
    }

}
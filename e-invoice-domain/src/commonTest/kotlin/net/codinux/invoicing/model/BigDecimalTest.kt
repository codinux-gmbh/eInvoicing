package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import kotlin.test.Test

class BigDecimalTest {

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

}
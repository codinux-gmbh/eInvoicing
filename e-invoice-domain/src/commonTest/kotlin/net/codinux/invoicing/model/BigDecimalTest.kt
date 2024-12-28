package net.codinux.invoicing.model

import assertk.assertThat
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

}
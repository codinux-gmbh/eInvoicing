package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import kotlin.test.Test

class BigJsTest {

    @Test
    fun components() {
        val result = BigJs("12345.6789")

        assertThat(result.s).isEqualTo(1) // positive
        assertThat(result.e).isEqualTo(4) // 1x10^4
//        assertThat(result.c.toIntArray()).isEqualTo(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    @Test
    fun minus() {
        val result = BigJs("0.3").minus(BigJs("0.1"))

        assertEquals(result, "0.2")
    }

    @Test
    fun round() {
        val decimal = BigJs("123.456789")

        assertEquals(decimal.round(2), "123.46")
    }

    @Test
    fun precision() {
        val decimal = BigJs("123.456789")

        assertEquals(decimal.prec(2), "120")
    }

    @Test
    fun toFixed() {
        val exponentialForm = BigJs("1.23456789e4")

        assertThat(exponentialForm.toFixed(2)).isEqualTo("12345.68")
    }

    @Test
    fun valueOf() {
        val exponentialForm = BigJs("1.23456789e4")

       assertThat(exponentialForm.valueOf()).isEqualTo("12345.6789")
    }


    private fun assertEquals(actual: BigJs, expected: String) {
        assertEquals(actual, BigJs(expected))
    }

    private fun assertEquals(actual: BigJs, expected: BigJs) {
        assertThat(actual.eq(expected)).isTrue()
    }

}
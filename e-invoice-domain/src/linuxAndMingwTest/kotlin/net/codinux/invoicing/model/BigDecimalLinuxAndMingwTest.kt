package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class BigDecimalLinuxAndMingwTest {

    @Test
    fun trimTrailingZeros() {
        val value = BigDecimal(123.0)

        val result = value.toString()

        assertThat(result).isEqualTo("123")
    }


}
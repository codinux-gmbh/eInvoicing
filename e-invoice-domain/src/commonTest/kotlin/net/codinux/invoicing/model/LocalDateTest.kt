package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isGreaterThanOrEqualTo
import assertk.assertions.isNotEqualTo
import kotlin.test.Test

class LocalDateTest {

    @Test
    fun now() {
        val result = LocalDate.now()

        assertThat(result.year).isGreaterThanOrEqualTo(2025)
        assertThat(result.month).isGreaterThanOrEqualTo(1)
        assertThat(result.dayOfMonth).isGreaterThanOrEqualTo(1)

        assertThat(result.toIsoDate()).isNotEqualTo("2025-01-01")
    }

}
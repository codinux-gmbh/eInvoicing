package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isEqualTo
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

    @Test
    fun dayOfWeek() {
        val result = LocalDate(2025, 1, 1).dayOfWeek

        assertThat(result).isEqualTo(2) // wednesday
    }

}
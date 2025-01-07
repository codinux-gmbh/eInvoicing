package net.codinux.invoicing.model

import assertk.assertThat
import assertk.assertions.isGreaterThan
import kotlin.test.Test

class InstantTest {

    @Test
    fun now() {
        val result = Instant.now()

        assertThat(result.epochSeconds).isGreaterThan(1_736_000_000)
    }

}
package net.codinux.invoicing.test

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import net.codinux.invoicing.model.Result

object Asserts {

    fun <T> assertSuccess(result: Result<T>): T {
        assertThat(result.error).isNull()
        assertThat(result.value).isNotNull()

        return result.value!!
    }

}
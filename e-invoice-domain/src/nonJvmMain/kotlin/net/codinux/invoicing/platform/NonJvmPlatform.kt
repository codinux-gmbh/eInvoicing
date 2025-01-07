package net.codinux.invoicing.platform

import net.codinux.invoicing.model.Instant
import net.codinux.invoicing.model.LocalDate

internal expect object NonJvmPlatform {

    fun getInstantNow(): Instant

    fun getLocalDateNow(): LocalDate

}
package net.codinux.invoicing.platform

import net.codinux.invoicing.model.LocalDate

internal expect object NonJvmPlatform {

    fun getLocalDateNow(): LocalDate

}
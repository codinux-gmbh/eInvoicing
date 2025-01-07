package net.codinux.invoicing.platform

import net.codinux.invoicing.model.LocalDate
import kotlin.js.Date

internal actual object NonJvmPlatform {

    actual fun getLocalDateNow(): LocalDate {
        val now = Date(Date.now())

        // getMonth() = month as number (0 - 11) -> + 1; getDate() = day as number (1 - 31), getDay() = weekday (0 - 6)
        return LocalDate(now.getFullYear(), now.getMonth() + 1, now.getDate())
    }

}
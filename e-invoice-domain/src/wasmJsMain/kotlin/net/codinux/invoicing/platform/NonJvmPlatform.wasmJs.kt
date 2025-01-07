package net.codinux.invoicing.platform

import net.codinux.invoicing.model.Instant
import net.codinux.invoicing.model.LocalDate

internal fun getDateNow(): JsDate =
    js("new Date(Date.now())")

internal fun getMillisSinceEpoch(): Double =
    js("Date.now()")

internal actual object NonJvmPlatform {

    actual fun getInstantNow(): Instant {
        val millisSinceEpoch = getMillisSinceEpoch()

        return Instant((millisSinceEpoch / 1_000).toLong(), (millisSinceEpoch % 1_000).toInt() * 1_000_000)
    }

    actual fun getLocalDateNow(): LocalDate {
        val now = getDateNow()

        // getMonth() = month as number (0 - 11) -> + 1; getDate() = day as number (1 - 31), getDay() = weekday (0 - 6)
        return LocalDate(now.getFullYear(), now.getMonth() + 1, now.getDate())
    }

}
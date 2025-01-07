package net.codinux.invoicing.platform

import kotlinx.cinterop.UnsafeNumber
import net.codinux.invoicing.model.Instant
import platform.Foundation.*
import net.codinux.invoicing.model.LocalDate

internal actual object NonJvmPlatform {

    actual fun getInstantNow(): Instant {
        val secondsSinceEpoch = NSDate().timeIntervalSince1970

        val nanosString = secondsSinceEpoch.toString().substringAfter('.').let {
            if (it.length > 9) it.substring(0, 9)
            else it.padEnd(9, '0')
        }

        return Instant(secondsSinceEpoch.toLong(), nanosString.toInt())
    }

    @OptIn(UnsafeNumber::class)
    actual fun getLocalDateNow(): LocalDate {
        val currentDate = NSDate()

        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(
            NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay,
            fromDate = currentDate
        )

        return LocalDate(components.year.toInt(), components.month.toInt(), components.day.toInt())
    }

}
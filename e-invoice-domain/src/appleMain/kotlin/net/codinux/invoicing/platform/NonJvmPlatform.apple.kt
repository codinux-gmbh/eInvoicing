package net.codinux.invoicing.platform

import kotlinx.cinterop.UnsafeNumber
import net.codinux.invoicing.model.Instant
import platform.Foundation.*
import net.codinux.invoicing.model.LocalDate

@OptIn(UnsafeNumber::class)
internal actual object NonJvmPlatform {

    actual fun getInstantNow(): Instant {
        val secondsSinceEpoch = NSDate().timeIntervalSince1970

        val nanosString = secondsSinceEpoch.toString().substringAfter('.').let {
            if (it.length > 9) it.substring(0, 9)
            else it.padEnd(9, '0')
        }

        return Instant(secondsSinceEpoch.toLong(), nanosString.toInt())
    }

    actual fun getLocalDateNow(): LocalDate {
        val currentDate = NSDate()

        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(
            NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay,
            fromDate = currentDate
        )

        return LocalDate(components.year.toInt(), components.month.toInt(), components.day.toInt())
    }

    actual fun getDayOfWeek(date: LocalDate): Int? {
        val components = NSDateComponents().apply {
            this.year = date.year.toLong()
            this.month = date.month.toLong()
            this.day = date.dayOfMonth.toLong()
        }

        val calendar = NSCalendar.currentCalendar
        val nsDate = calendar.dateFromComponents(components) ?: return null

        // The weekday units are the numbers 1 through N (where for the Gregorian calendar N=7 and 1 is Sunday).
        val weekDay = calendar.component(NSCalendarUnitWeekday, nsDate).toInt()
        // NSCalendarUnitWeekdayOrdinal == week of month

        return if (weekDay == 1) 6 else weekDay - 2
    }

}
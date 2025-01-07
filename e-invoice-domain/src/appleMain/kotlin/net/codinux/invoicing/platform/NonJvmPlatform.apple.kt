package net.codinux.invoicing.platform

import platform.Foundation.*
import net.codinux.invoicing.model.LocalDate

internal actual object NonJvmPlatform {

    actual fun getLocalDateNow(): LocalDate {
        val currentDate = NSDate()

        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(
            NSCalendar.UnitYear or NSCalendar.UnitMonth or NSCalendar.UnitDay,
            fromDate = currentDate
        )

        return LocalDate(components.year, components.month, components.day)
    }

}
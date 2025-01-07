package net.codinux.invoicing.platform

import kotlinx.cinterop.*
import platform.posix.*
import net.codinux.invoicing.model.LocalDate

internal actual object NonJvmPlatform {

    @OptIn(ExperimentalForeignApi::class)
    actual fun getLocalDateNow(): LocalDate {
        val localTime = memScoped {
            // Get the current time as seconds since Unix epoch
            val currentTime = alloc<time_tVar>()
            time(currentTime.ptr) // Get current time and store it in currentTime

            // Convert to local time
            localtime(currentTime.ptr)
        }?.pointed ?: return LocalDate(0, 1, 1)

        // Extract components
        val year = localTime.tm_year + 1900  // tm_year is years since 1900
        val month = localTime.tm_mon + 1   // tm_mon is 0-based
        val day = localTime.tm_mday

        return LocalDate(year, month, day)
    }

}
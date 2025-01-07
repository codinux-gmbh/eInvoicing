package net.codinux.invoicing.platform

import kotlinx.cinterop.*
import net.codinux.invoicing.model.Instant
import platform.posix.*
import net.codinux.invoicing.model.LocalDate

@OptIn(ExperimentalForeignApi::class)
internal actual object NonJvmPlatform {

    @OptIn(UnsafeNumber::class)
    actual fun getInstantNow(): Instant {
        val (seconds, nanos) = memScoped {
            val time = alloc<timespec>()
            clock_gettime(CLOCK_REALTIME, time.ptr)

            time.tv_sec to time.tv_nsec.toInt()
        }

        return Instant(seconds, nanos)
    }

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
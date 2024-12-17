package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.InstantSerializer

@Serializable(with = InstantSerializer::class)
actual class Instant actual constructor(actual val epochSeconds: Long, actual val nanosecondsOfSecond: Int) : Comparable<Instant> {

    actual companion object {
        actual fun now(): Instant = Instant(0, 0) // TODO
    }


    actual override fun compareTo(other: Instant): Int {
        val secondsCompare = epochSeconds.compareTo(other.epochSeconds)
        if (secondsCompare != 0) {
            return secondsCompare
        }

        return nanosecondsOfSecond.compareTo(other.nanosecondsOfSecond)
    }

    actual fun toLocalDateAtSystemDefaultZone(): LocalDate = LocalDate(0, 0, 0) // TODO


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Instant) return false

        if (epochSeconds != other.epochSeconds) return false
        if (nanosecondsOfSecond != other.nanosecondsOfSecond) return false

        return true
    }

    override fun hashCode(): Int {
        var result = epochSeconds.hashCode()
        result = 31 * result + nanosecondsOfSecond
        return result
    }

}
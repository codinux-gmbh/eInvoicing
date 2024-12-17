package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.LocalDateSerializer
import java.time.Instant
import java.time.ZoneId

@Serializable(with = LocalDateSerializer::class)
actual class LocalDate actual constructor(actual val year: Int, actual val month: Int, actual val dayOfMonth: Int): Comparable<LocalDate> {

    actual companion object {
        actual fun now() = java.time.LocalDate.now().toEInvoicingDate()
    }


    fun toJvmDate() = java.time.LocalDate.of(year, month, dayOfMonth)

    fun toJvmInstantAtSystemDefaultZone(): Instant = toJvmDate().atStartOfDay(ZoneId.systemDefault()).toInstant()

    actual override fun compareTo(other: LocalDate): Int =
        toJvmDate().compareTo(other.toJvmDate())


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LocalDate) return false

        if (year != other.year) return false
        if (month != other.month) return false
        if (dayOfMonth != other.dayOfMonth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + dayOfMonth
        return result
    }

    override fun toString() = this.toIsoDate()

}

fun java.time.LocalDate.toEInvoicingDate() = LocalDate(this.year, this.monthValue, this.dayOfMonth)
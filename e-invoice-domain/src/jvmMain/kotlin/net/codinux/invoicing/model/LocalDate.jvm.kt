package net.codinux.invoicing.model

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

actual class LocalDate actual constructor(actual val year: Int, actual val month: Int, actual val dayOfMonth: Int) {

    actual companion object {
        fun now() = fromJvmDate(LocalDate.now())

        fun fromJvmDate(date: java.time.LocalDate) = LocalDate(date.year, date.monthValue, date.dayOfMonth)
    }


    fun toJvmDate() = java.time.LocalDate.of(year, month, dayOfMonth)

    fun toInstantAtSystemDefaultZone(): Instant = toJvmDate().atStartOfDay(ZoneId.systemDefault()).toInstant()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is net.codinux.invoicing.model.LocalDate) return false

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

    override fun toString() = "$year-${month.toString().padStart(2, '0')}-${dayOfMonth.toString().padStart(2, '0')}"

}

fun java.time.LocalDate.toEInvoicingDate() = LocalDate(this.year, this.monthValue, this.dayOfMonth)
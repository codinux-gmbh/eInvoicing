package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.LocalDateSerializer

/**
 * I didn't want to use kotlinx-datetime as it has incompatibilities between 0.5 and 0.6 which leads to compile errors
 * when both are on the class path, and doesn't compile with GraalVM.
 */
@Serializable(with = LocalDateSerializer::class)
actual class LocalDate actual constructor(actual val year: Int, actual val month: Int, actual val dayOfMonth: Int): Comparable<LocalDate> {

    actual companion object {
        actual fun now() = LocalDate(0, 0, 0) // TODO
    }


    actual override fun compareTo(other: LocalDate): Int {
        val yearCompare = year.compareTo(other.year)
        if (yearCompare != 0) return yearCompare

        val monthCompare = month.compareTo(other.month)
        if (monthCompare != 0) return monthCompare

        return dayOfMonth.compareTo(other.dayOfMonth)
    }

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

    override fun toString() = this.toIsoDate()

}
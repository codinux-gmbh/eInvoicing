package net.codinux.invoicing.model

/**
 * I didn't want to use kotlinx-datetime as it has incompatibilities between 0.5 and 0.6 which leads to compile errors
 * when both are on the class path, and doesn't compile with GraalVM.
 */
actual class LocalDate actual constructor(actual val year: Int, actual val month: Int, actual val dayOfMonth: Int) {

    actual companion object {
        actual fun now() = LocalDate(0, 0, 0) // TODO
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

    override fun toString() = "$year-${month.toString().padStart(2, '0')}-${dayOfMonth.toString().padStart(2, '0')}"

}
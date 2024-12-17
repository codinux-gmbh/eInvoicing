package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.LocalDateSerializer

/**
 * I didn't want to use kotlinx-datetime as it has incompatibilities between 0.5 and 0.6 which leads to compile errors
 * when both are on the class path, and doesn't compile with GraalVM.
 */
@Serializable(with = LocalDateSerializer::class)
expect class LocalDate(year: Int, month: Int, dayOfMonth: Int) : Comparable<LocalDate> {

    companion object {
        fun now(): LocalDate
    }


    val dayOfMonth: Int
    val month: Int
    val year: Int


    override fun compareTo(other: LocalDate): Int

}


fun LocalDate.toIsoDate() =
    "$year-${month.toString().padStart(2, '0')}-${dayOfMonth.toString().padStart(2, '0')}"

fun LocalDate.toDotSeparatedIsoDate() =
    "$year.${month.toString().padStart(2, '0')}.${dayOfMonth.toString().padStart(2, '0')}"
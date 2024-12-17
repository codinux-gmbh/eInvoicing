package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.InstantSerializer

@Serializable(with = InstantSerializer::class)
expect class Instant(epochSeconds: Long, nanosecondsOfSecond: Int) : Comparable<Instant> {

    companion object {
        fun now(): Instant
    }


    val epochSeconds: Long

    val nanosecondsOfSecond: Int


    override fun compareTo(other: Instant): Int

    fun toLocalDateAtSystemDefaultZone(): LocalDate

}
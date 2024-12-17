package net.codinux.invoicing.model

expect class Instant(epochSeconds: Long, nanosecondsOfSecond: Int) : Comparable<Instant> {

    companion object {
        fun now(): Instant
    }


    val epochSeconds: Long

    val nanosecondsOfSecond: Int


    override fun compareTo(other: Instant): Int

    fun toLocalDateAtSystemDefaultZone(): LocalDate

}
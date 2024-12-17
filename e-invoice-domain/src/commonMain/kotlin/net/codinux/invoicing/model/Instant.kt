package net.codinux.invoicing.model

expect class Instant : Comparable<Instant> {

    companion object {
        fun now(): Instant
    }


    override fun compareTo(other: Instant): Int

    fun toLocalDateAtSystemDefaultZone(): LocalDate

}
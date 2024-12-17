package net.codinux.invoicing.model

expect class Instant {

    companion object {
        fun now(): Instant
    }


    fun toLocalDateAtSystemDefaultZone(): LocalDate

}
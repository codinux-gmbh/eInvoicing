package net.codinux.invoicing.model

expect class Instant {

    fun toLocalDateAtSystemDefaultZone(): LocalDate

}
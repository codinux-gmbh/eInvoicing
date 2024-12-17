package net.codinux.invoicing.model

actual class Instant {

    actual fun toLocalDateAtSystemDefaultZone(): LocalDate  = LocalDate(0, 0, 0) // TODO

}
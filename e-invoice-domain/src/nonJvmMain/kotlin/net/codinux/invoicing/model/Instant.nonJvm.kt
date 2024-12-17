package net.codinux.invoicing.model

actual class Instant {

    actual companion object {
        actual fun now(): Instant = Instant() // TODO
    }


    actual fun toLocalDateAtSystemDefaultZone(): LocalDate = LocalDate(0, 0, 0) // TODO

}
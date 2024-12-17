package net.codinux.invoicing.model

actual class Instant : Comparable<Instant> {

    actual companion object {
        actual fun now(): Instant = Instant() // TODO
    }


    actual override fun compareTo(other: Instant): Int {
        return 0 // TODO
    }

    actual fun toLocalDateAtSystemDefaultZone(): LocalDate = LocalDate(0, 0, 0) // TODO

}
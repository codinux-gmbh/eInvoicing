package net.codinux.invoicing.model

import java.time.ZoneId

actual class Instant(private val jvmInstant: java.time.Instant) {

    actual fun toLocalDateAtSystemDefaultZone(): LocalDate =
        jvmInstant.atZone(ZoneId.systemDefault()).toLocalDate().toEInvoicingDate()

    fun toJvmInstant(): java.time.Instant = jvmInstant

}


fun java.time.Instant.toEInvoicingInstant() = Instant(this)
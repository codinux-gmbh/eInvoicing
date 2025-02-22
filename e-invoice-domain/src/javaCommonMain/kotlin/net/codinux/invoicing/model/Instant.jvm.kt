package net.codinux.invoicing.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.codinux.invoicing.serialization.InstantSerializer
import java.time.ZoneId

@Serializable(with = InstantSerializer::class)
actual class Instant(
    @JsonProperty("value", access = JsonProperty.Access.READ_WRITE) @Transient private val jvmInstant: java.time.Instant = java.time.Instant.now()
) : Comparable<Instant> {

    actual companion object {
        actual fun now(): Instant = Instant(java.time.Instant.now())
    }


    actual constructor(epochSeconds: Long, nanosecondsOfSecond: Int) :
            this(java.time.Instant.ofEpochSecond(epochSeconds, nanosecondsOfSecond.toLong()))


    @JsonIgnore
    actual val epochSeconds: Long = jvmInstant.epochSecond

    @JsonIgnore
    actual val nanosecondsOfSecond: Int = jvmInstant.nano


    actual override fun compareTo(other: Instant): Int =
        toJvmInstant().compareTo(other.toJvmInstant())

    actual fun toLocalDateAtSystemDefaultZone(): LocalDate =
        jvmInstant.atZone(ZoneId.systemDefault()).toLocalDate().toEInvoicingDate()

    fun toJvmInstant(): java.time.Instant = jvmInstant


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Instant) return false

        if (jvmInstant != other.jvmInstant) return false

        return true
    }

    override fun hashCode(): Int {
        return jvmInstant.hashCode()
    }

    override fun toString() = jvmInstant.toString()

}


fun java.time.Instant.toEInvoicingInstant() = Instant(this)
package net.codinux.invoicing.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.codinux.invoicing.serialization.BigDecimalSerializer
import java.math.RoundingMode

@Serializable(with = BigDecimalSerializer::class)
actual class BigDecimal(
    @JsonProperty(access = JsonProperty.Access.READ_WRITE) // use the value: BigDecimal private field for Jackson serialization and deserialization
    @JsonFormat(shape = JsonFormat.Shape.STRING) // for compatibility with kotlinx-serialization, so that it's enable to deserialize BigDecimals serialized by Jackson, write BigDecimals as string
    @Transient // for kotlinx-serialization we have an extra serializer, so ignore kotlinx-serialization's default which encodes all constructor properties
    private val value: java.math.BigDecimal = java.math.BigDecimal.ZERO
) : Comparable<BigDecimal> {

    actual companion object {
        actual val Zero = BigDecimal(java.math.BigDecimal.ZERO)
    }


    actual constructor(value: String) : this(java.math.BigDecimal(value))

    actual constructor(value: Int) : this(java.math.BigDecimal(value))


    actual fun setScale(newScale: Int) = BigDecimal(value.setScale(newScale))

    actual fun toPlainString(): String = value.toPlainString()

    actual override fun compareTo(other: BigDecimal): Int = value.compareTo(other.value)

    fun setScale(newScale: Int, roundingMode: RoundingMode = RoundingMode.UNNECESSARY) =
        BigDecimal(value.setScale(newScale, roundingMode))

    fun toJvmBigDecimal() = value

    operator fun plus(other: BigDecimal): BigDecimal = BigDecimal(value.add(other.value))


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = value.toString()

}


fun java.math.BigDecimal.toEInvoicingBigDecimal() = BigDecimal(this)
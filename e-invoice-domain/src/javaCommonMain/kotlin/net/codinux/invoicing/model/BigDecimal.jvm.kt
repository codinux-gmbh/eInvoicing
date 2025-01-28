package net.codinux.invoicing.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
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


    actual operator fun plus(other: BigDecimal): BigDecimal = BigDecimal(value.plus(other.value))

    actual operator fun minus(other: BigDecimal): BigDecimal = BigDecimal(value.minus(other.value))

    actual operator fun times(other: BigDecimal): BigDecimal = BigDecimal(value.times(other.value))

    actual operator fun div(other: BigDecimal): BigDecimal = BigDecimal(value.divide(other.value)) // don't us .div(), it uses the scale of the divisor which may is zero, effectively removing all decimal places then

    actual operator fun rem(other: Int): BigDecimal = BigDecimal(value.rem(java.math.BigDecimal(other)))

    actual operator fun unaryMinus(): BigDecimal = negated()


    @get:JsonIgnore
    actual val isNegative: Boolean by lazy { this < Zero }

    actual fun negated(): BigDecimal = BigDecimal(this.value.negate())

    actual fun abs(): BigDecimal = BigDecimal(this.value.abs())


    actual fun toInt(): Int = value.toInt()

    actual fun toDouble(): Double = value.toDouble()

    actual fun setScale(newScale: Int) = BigDecimal(value.setScale(newScale, RoundingMode.HALF_UP))

    actual fun toPlainString(): String = value.toPlainString()

    actual override fun compareTo(other: BigDecimal): Int = value.compareTo(other.value)

    fun setScale(newScale: Int, roundingMode: RoundingMode = RoundingMode.UNNECESSARY) =
        BigDecimal(value.setScale(newScale, roundingMode))

    fun toJvmBigDecimal() = value


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false

        // One exception is java.math.BigDecimal, whose natural ordering equates BigDecimal objects with equal numerical
        // values and different representations (such as 4.0 and 4.00). For BigDecimal.equals() to return true, the
        // representation and numerical value of the two BigDecimal objects must be the same.
        return compareTo(other) == 0 // -> use compareTo() instead of value.equals(other.value)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = value.toString()

}


fun java.math.BigDecimal.toEInvoicingBigDecimal() = BigDecimal(this)
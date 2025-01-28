package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.BigDecimalSerializer

@Serializable(with = BigDecimalSerializer::class)
actual class BigDecimal(private val value: BigJs) : Comparable<BigDecimal> {

    actual companion object {
        actual val Zero = BigDecimal("0")
    }


    actual constructor(value: String) : this(BigJs(value))

    actual constructor(value: Int) : this(value.toString())


    actual operator fun plus(other: BigDecimal): BigDecimal = BigDecimal(value.plus(other.value))

    actual operator fun minus(other: BigDecimal): BigDecimal = BigDecimal(value.minus(other.value))

    actual operator fun times(other: BigDecimal): BigDecimal = BigDecimal(value.times(other.value))

    actual operator fun div(other: BigDecimal): BigDecimal = divide(other, 2)

    actual operator fun rem(other: Int): BigDecimal = BigDecimal(value.mod(other))

    actual operator fun unaryMinus(): BigDecimal = BigDecimal(value.neg())


    actual fun divide(divisor: BigDecimal, scale: Int): BigDecimal = BigDecimal(value.div(divisor.value).round(scale, 1)) // 1 = HALF_UP


    actual val isNegative: Boolean by lazy { this < Zero }

    actual fun negated(): BigDecimal = BigDecimal(this.value.neg())

    actual fun abs(): BigDecimal = BigDecimal(this.value.abs())


    actual fun toInt(): Int = value.toNumber().toInt()

    actual fun toDouble(): Double = value.toNumber().toDouble()

    actual fun setScale(newScale: Int): BigDecimal = BigDecimal(value.round(newScale))

    actual fun toPlainString(): String = value.valueOf()

    actual override fun compareTo(other: BigDecimal): Int = value.cmp(other.value)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false

        return compareTo(other) == 0
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = value.valueOf()

}
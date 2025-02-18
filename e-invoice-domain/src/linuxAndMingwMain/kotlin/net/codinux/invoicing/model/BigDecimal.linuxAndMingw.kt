package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.BigDecimalSerializer
import kotlin.math.absoluteValue

// we ignore Linux and Mingw for now, there's no frontend for it, later e.g. use ionspin BigNum or korlibs BigNum
@Serializable(with = BigDecimalSerializer::class)
actual class BigDecimal(private val value: Double) : Comparable<BigDecimal> {

    actual companion object {
        actual val Zero = BigDecimal("0")
    }


    actual constructor(value: Int) : this(value.toDouble())

    actual constructor(value: String) : this(value.toDouble())


    actual operator fun plus(other: BigDecimal): BigDecimal = BigDecimal(value.plus(other.value))

    actual operator fun minus(other: BigDecimal): BigDecimal = BigDecimal(value.minus(other.value))

    actual operator fun times(other: BigDecimal): BigDecimal = BigDecimal(value.times(other.value))

    actual operator fun div(other: BigDecimal): BigDecimal = divide(other, 2)

    actual operator fun rem(other: Int): BigDecimal = BigDecimal(value.rem(other))

    actual operator fun unaryMinus(): BigDecimal = negated()


    actual fun divide(divisor: BigDecimal, scale: Int): BigDecimal = BigDecimal(value.div(divisor.value)).setScale(scale)


    actual val isNegative: Boolean by lazy { this < Zero }

    actual fun negated(): BigDecimal = BigDecimal(this.value.unaryMinus())

    actual fun abs(): BigDecimal = BigDecimal(this.value.absoluteValue)


    actual fun toInt(): Int = value.toInt()

    actual fun toDouble(): Double = value

    actual fun setScale(newScale: Int): BigDecimal {
        val valueAsString = value.toString()
        val indexOfDot = valueAsString.lastIndexOf('.')
        if (indexOfDot != -1) {
            val countFractionDigits = valueAsString.length - indexOfDot - 1
            if (countFractionDigits != newScale) {
                val diff = newScale - countFractionDigits

                val newValue = if (newScale == 0) {
                    valueAsString.substring(0, indexOfDot)
                } else if (diff < 0) {
                    valueAsString.substring(0, indexOfDot + newScale + 1)
                } else {
                    valueAsString.padEnd(indexOfDot + newScale + 1, '0')
                }

                return BigDecimal(newValue)
            }
        } else if (newScale != 0) {
            return BigDecimal(valueAsString + "." + "".padEnd(newScale, '0'))
        }

        return this
    }

    actual fun toPlainString(): String = value.toString().let {
        var trimmed = it
        while (trimmed.lastOrNull() == '0') {
            trimmed = trimmed.substring(0, trimmed.length - 1)
        }

        if (trimmed.endsWith('.')) {
            trimmed = trimmed.substring(0, trimmed.length - 1)
        }

        trimmed
    }

    actual fun toPlainStringWithoutTrailingZeros(): String = toPlainString()

    actual override fun compareTo(other: BigDecimal): Int = value.compareTo(other.value)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false

        return value.equals(other.value)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = toPlainString()

}
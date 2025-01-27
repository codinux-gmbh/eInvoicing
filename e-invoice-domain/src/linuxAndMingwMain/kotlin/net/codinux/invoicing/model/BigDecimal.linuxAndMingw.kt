package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.BigDecimalSerializer

@Serializable(with = BigDecimalSerializer::class)
actual class BigDecimal actual constructor(private val value: String) : Comparable<BigDecimal> {

    actual companion object {
        actual val Zero = BigDecimal("0")
    }


    actual constructor(value: Int) : this(value.toString())


    actual fun toInt(): Int = value.split(".").first().toInt()

    actual fun toDouble(): Double = value.toDouble()

    actual fun setScale(newScale: Int): BigDecimal {
        val indexOfDot = value.lastIndexOf('.')
        if (indexOfDot != -1) {
            val countFractionDigits = value.length - indexOfDot - 1
            if (countFractionDigits != newScale) {
                val diff = newScale - countFractionDigits

                val newValue = if (newScale == 0) {
                    value.substring(0, indexOfDot)
                } else if (diff < 0) {
                    value.substring(0, indexOfDot + newScale + 1)
                } else {
                    value.padEnd(indexOfDot + newScale + 1, '0')
                }

                return BigDecimal(newValue)
            }
        } else if (newScale != 0) {
            return BigDecimal(value + "." + "".padEnd(newScale, '0'))
        }

        return this
    }

    actual fun toPlainString(): String = value

    actual override fun compareTo(other: BigDecimal): Int = value.compareTo(other.value) // TODO


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false

        return value.equals(other.value)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = value

}
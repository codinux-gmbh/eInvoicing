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

    override fun toString() = value.toString()

}
package net.codinux.invoicing.model

import java.math.RoundingMode

actual class BigDecimal(private val value: java.math.BigDecimal) : Comparable<BigDecimal> {

    actual companion object {
        actual val Zero = BigDecimal(java.math.BigDecimal.ZERO)
    }


    actual constructor(value: String) : this(java.math.BigDecimal(value))

    actual constructor(value: Int) : this(java.math.BigDecimal(value))


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
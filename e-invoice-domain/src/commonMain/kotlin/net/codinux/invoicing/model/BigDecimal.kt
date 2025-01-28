package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.BigDecimalSerializer

@Serializable(with = BigDecimalSerializer::class)
expect class BigDecimal(value: String) : Comparable<BigDecimal> {

    companion object {
        val Zero: BigDecimal
    }


    // initializing with a Double can lead to incorrect results, e.g. BigDecimal(0.1) -> 0.100000000000000005551115123125782707118, so don't offer it
    constructor(value: Int)


    operator fun plus(other: BigDecimal): BigDecimal

    operator fun minus(other: BigDecimal): BigDecimal

    operator fun times(other: BigDecimal): BigDecimal

    operator fun div(other: BigDecimal): BigDecimal

    operator fun rem(other: Int): BigDecimal

    operator fun unaryMinus(): BigDecimal


    fun divide(divisor: BigDecimal, scale: Int = 2): BigDecimal


    val isNegative: Boolean

    fun negated(): BigDecimal

    fun abs(): BigDecimal


    fun toInt(): Int

    fun toDouble(): Double

    fun setScale(newScale: Int): BigDecimal

    fun toPlainString(): String

    override fun compareTo(other: BigDecimal): Int

}
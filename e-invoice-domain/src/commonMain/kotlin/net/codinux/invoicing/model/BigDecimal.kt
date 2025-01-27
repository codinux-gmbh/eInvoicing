package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.BigDecimalSerializer

@Serializable(with = BigDecimalSerializer::class)
expect class BigDecimal(value: String) : Comparable<BigDecimal> {

    companion object {
        val Zero: BigDecimal
    }


    constructor(value: Int)


    operator fun plus(other: BigDecimal): BigDecimal

    operator fun minus(other: BigDecimal): BigDecimal

    operator fun times(other: BigDecimal): BigDecimal

    operator fun div(other: BigDecimal): BigDecimal

    operator fun rem(other: Int): BigDecimal

    operator fun unaryMinus(): BigDecimal


    val isNegative: Boolean

    fun negated(): BigDecimal

    fun abs(): BigDecimal


    fun toInt(): Int

    fun toDouble(): Double

    fun setScale(newScale: Int): BigDecimal

    fun toPlainString(): String

    override fun compareTo(other: BigDecimal): Int

}
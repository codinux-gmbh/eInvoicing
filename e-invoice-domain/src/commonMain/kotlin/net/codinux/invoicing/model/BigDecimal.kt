package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.BigDecimalSerializer

@Serializable(with = BigDecimalSerializer::class)
expect class BigDecimal(value: String) : Comparable<BigDecimal> {

    companion object {
        val Zero: BigDecimal
    }


    constructor(value: Int)


    fun toInt(): Int

    fun toDouble(): Double

    fun setScale(newScale: Int): BigDecimal

    fun toPlainString(): String

    override fun compareTo(other: BigDecimal): Int

}
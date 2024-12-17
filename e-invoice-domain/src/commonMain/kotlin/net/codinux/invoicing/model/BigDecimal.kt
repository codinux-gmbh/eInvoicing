package net.codinux.invoicing.model

expect class BigDecimal(value: String) : Comparable<BigDecimal> {

    companion object {
        val Zero: BigDecimal
    }


    constructor(value: Int)


    fun toPlainString(): String

    override fun compareTo(other: BigDecimal): Int

}
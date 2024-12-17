package net.codinux.invoicing.model

expect class BigDecimal(value: String) {

    companion object {
        val Zero: BigDecimal
    }


    constructor(value: Int)


    fun toPlainString(): String

}
package net.codinux.invoicing.model

import java.math.BigDecimal

class LineItem(
    val name: String,
    val unit: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val vatPercentage: BigDecimal,
    val description: String? = null,
) {
    override fun toString() = "$name, $quantity x $price, $vatPercentage %"
}
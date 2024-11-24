package net.codinux.invoicing.model

import java.math.BigDecimal

class InvoiceItem(
    val name: String,
    val quantity: BigDecimal,
    val unit: String,
    val unitPrice: BigDecimal,
    val vatRate: BigDecimal,
    val description: String? = null,
) {
    override fun toString() = "$name, $quantity x $unitPrice, $vatRate %"
}
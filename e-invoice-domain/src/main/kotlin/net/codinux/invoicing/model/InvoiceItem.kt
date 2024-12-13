package net.codinux.invoicing.model

import net.codinux.invoicing.model.codes.UnitOfMeasure
import java.math.BigDecimal

class InvoiceItem(
    val name: String,
    val quantity: BigDecimal,
    val unit: UnitOfMeasure,
    val unitPrice: BigDecimal,
    val vatRate: BigDecimal,
    val articleNumber: String? = null,
    val description: String? = null,
) {
    override fun toString() = "$name, $quantity x $unitPrice, $vatRate %"
}
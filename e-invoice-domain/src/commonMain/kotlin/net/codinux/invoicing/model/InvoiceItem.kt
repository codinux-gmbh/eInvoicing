package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.codes.UnitOfMeasure

@Serializable
class InvoiceItem(
    val name: String,
    val quantity: BigDecimal,
    val unit: UnitOfMeasure,
    val unitPrice: BigDecimal,
    val vatRate: BigDecimal,
    val articleNumber: String? = null,
    val description: String? = null,
    var monetarySummation: LineMonetarySummation? = null,
) {
    override fun toString() = "$name, $quantity x $unitPrice, $vatRate %"
}
package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.codes.UnitOfMeasure

@Serializable
data class InvoiceItem(
    val name: String,
    val quantity: BigDecimal,
    val unit: UnitOfMeasure,
    val unitPrice: BigDecimal,
    val vatRate: BigDecimal,
    val articleNumber: String? = null,
    val description: String? = null,
    var amounts: LineAmounts? = null,
) {
    override fun toString() = "$name, ${quantity.toPlainString()} x ${unitPrice.toPlainString()}, $vatRate %"
}
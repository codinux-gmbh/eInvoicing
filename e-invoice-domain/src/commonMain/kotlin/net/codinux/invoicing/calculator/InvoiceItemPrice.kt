package net.codinux.invoicing.calculator

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal

@Serializable
data class InvoiceItemPrice(
    val unitPrice: BigDecimal,
    val quantity: BigDecimal,
    val vatRate: BigDecimal
) {
    override fun toString() = "$unitPrice * $quantity of: $vatRate"
}
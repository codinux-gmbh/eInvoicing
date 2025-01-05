package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal

@Serializable
class AmountOfMoney(
    val amount: BigDecimal,
    val currency: String,
    val amountWithCurrency: String = "$amount $currency"
) {
    override fun toString() = amountWithCurrency
}
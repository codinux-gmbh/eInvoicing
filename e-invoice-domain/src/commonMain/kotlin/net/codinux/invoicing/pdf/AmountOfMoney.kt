package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.BigDecimal

class AmountOfMoney(
    val amount: BigDecimal,
    val currency: String,
    val amountWithCurrency: String = "$amount $currency"
) {
    override fun toString() = amountWithCurrency
}
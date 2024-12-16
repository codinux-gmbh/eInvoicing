package net.codinux.invoicing.pdf

import java.math.BigDecimal

class AmountOfMoney(
    val amount: BigDecimal,
    val currency: String,
    val amountWithCurrency: String = "$amount $currency"
) {
    override fun toString() = amountWithCurrency
}
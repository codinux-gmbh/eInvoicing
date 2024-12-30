package net.codinux.invoicing.calculator

import net.codinux.invoicing.model.TotalAmounts

expect class AmountsCalculator constructor() {

    suspend fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>): TotalAmounts?

}
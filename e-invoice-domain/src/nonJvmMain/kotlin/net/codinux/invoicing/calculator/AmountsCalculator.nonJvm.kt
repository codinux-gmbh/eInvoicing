package net.codinux.invoicing.calculator

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.TotalAmounts
import net.codinux.invoicing.web.WebClient

actual open class AmountsCalculator(protected open val calculator: WebServiceAmountsCalculator = WebServiceAmountsCalculator()) {

    constructor(webClient: WebClient) : this(WebServiceAmountsCalculator(webClient))

    actual constructor() : this(DI.DefaultWebClient)


    actual open suspend fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>): TotalAmounts? =
        calculator.calculateTotalAmounts(itemPrices)

}
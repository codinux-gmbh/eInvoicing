package net.codinux.invoicing.calculator

import net.codinux.invoicing.config.DI
import net.codinux.invoicing.model.TotalAmounts
import net.codinux.invoicing.web.ContentTypes
import net.codinux.invoicing.web.RequestParameters
import net.codinux.invoicing.web.WebClient

open class WebServiceAmountsCalculator(
    protected open val webClient: WebClient = DI.DefaultWebClient,
) {

    open suspend fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>): TotalAmounts? {
        val response = webClient.postAsync(RequestParameters("calculateTotalAmounts", TotalAmounts::class, itemPrices, ContentTypes.JSON, ContentTypes.JSON))

        if (response.successful) {
            return response.body
        }

        return null
    }

}
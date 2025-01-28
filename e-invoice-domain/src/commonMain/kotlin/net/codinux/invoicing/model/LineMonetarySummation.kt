package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
data class LineMonetarySummation(
    /**
     * Only set if it differs from [netPrice].
     */
    val grossPrice: BigDecimal? = null,

    val netPrice: BigDecimal,

    /**
     * Set, even if it's zero. // TODO: sensful? Or set to null if item contains no tax / VAT?
     */
    val taxAmount: BigDecimal,

    val lineTotalAmount: BigDecimal
) {
    override fun toString() = "$netPrice + $taxAmount = $lineTotalAmount"
}

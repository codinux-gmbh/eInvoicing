package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
data class LineAmounts(
    /**
     * The unit price, exclusive of VAT, before subtracting Item price discount.
     *
     * Only set if it differs from [netPrice].
     */
    val grossPrice: BigDecimal? = null,

    /**
     * The price of an item, exclusive of VAT, after subtracting item price discount.
     */
    val netPrice: BigDecimal, // kann im Allgemeinen null sein, Basic - Extended jedoch nicht

    val charges: List<ItemChargeOrAllowance> = emptyList(),
    val allowances: List<ItemChargeOrAllowance> = emptyList(),

    /**
     * The total amount of the Invoice line.
     *
     * The amount is “net” without VAT, i.e. inclusive of line level allowances and charges as well as other relevant
     * taxes.
     */
    val lineTotalAmount: BigDecimal, // kann im Allgemeinen null sein, Basic - Extended jedoch nicht

    /**
     * Set, even if it's zero. // TODO: sensful? Or set to null if item contains no tax / VAT?
     */
    val lineTotalTaxAmount: BigDecimal = BigDecimal.Zero,
) {
    override fun toString() = "$netPrice + $lineTotalTaxAmount = $lineTotalAmount"
}

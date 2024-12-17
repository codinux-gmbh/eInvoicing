package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class AmountAdjustments(
    /**
     * Vorauszahlungen.
     */
    val prepaidAmounts: BigDecimal = BigDecimal.Zero,

    /**
     * Zusätzliche Gebühren.
     */
    val charges: List<ChargeOrAllowance> = emptyList(),

    /**
     * Abzüge / Nachlässe.
     */
    val allowances: List<ChargeOrAllowance> = emptyList()
) {
    override fun toString() = "${prepaidAmounts.toPlainString()} prepaid, ${charges.size} charges, ${allowances.size} allowances"
}
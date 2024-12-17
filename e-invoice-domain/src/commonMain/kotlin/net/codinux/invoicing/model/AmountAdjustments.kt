package net.codinux.invoicing.model

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
package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class ItemChargeOrAllowance(
    /**
     * Gesamtbetrag der Gebühr oder des Nachlasses.
     * Evtl. berechnet aus [basisAmount] und [calculationPercent].
     */
    val actualAmount: BigDecimal,

    /**
     * Der Ausgangsbetrag auf den die Gebühr oder der Nachlass angewendet wird.
     */
    val basisAmount: BigDecimal? = null,
    /**
     * Der auf [basisAmount] anwendbare Prozentsatz der Gebühr oder des Nachlasses.
     */
    val calculationPercent: BigDecimal? = null,

    /**
     * Menschenlesbare Beschreibung der Gebühr oder des Nachlasses.
     */
    val reason: String? = null,
    /**
     * Code für Begründung der Gebühr oder des Nachlasses aus [UNTDID 5189](https://unece.org/fileadmin/DAM/trade/untdid/d16b/tred/tred5189.htm).
     */
    val reasonCode: String? = null,
) {
    override fun toString() = "${reason?.let { "$it: " } ?: ""}${actualAmount.toPlainString()}"
}
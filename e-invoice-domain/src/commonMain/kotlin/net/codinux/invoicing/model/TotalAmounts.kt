package net.codinux.invoicing.model

class TotalAmounts(
    /**
     * Gesamtbetrag einer einzelnen Rechnungsposition.
     */
    val lineTotalAmount: BigDecimal,

    val chargeTotalAmount: BigDecimal = BigDecimal.Zero,

    /**
     * Gesamtbetrag aller gewährten Rabatte, Abzüge oder Nachlässe.
     */
    val allowanceTotalAmount: BigDecimal = BigDecimal.Zero,

    /**
     * Der Gesamtbetrag, der als Grundlage für die Steuerberechnung dient, nach Abzug von Rabatten (Allowance) und vor
     * Hinzufügen der Steuerbeträge.
     */
    val taxBasisTotalAmount: BigDecimal,

    /**
     * Die Gesamtsumme der auf der Rechnung anfallenden Steuern. Dies umfasst in der Regel alle Steuerarten, die auf den
     * Tax Basis Total Amount angewendet werden (z. B. Mehrwertsteuer oder Umsatzsteuer).
     */
    val taxTotalAmount: BigDecimal,

    /**
     * Der Gesamtbetrag der Rechnung nach Berücksichtigung aller Kosten, Abzüge und Steuern. Dies ist der Betrag, der
     * vor eventuellen Vorauszahlungen oder Gutschriften fällig wäre.
     */
    val grandTotalAmount: BigDecimal,

    /**
     * Der Betrag, der bereits im Voraus bezahlt wurde. Dieser Betrag wird vom Grand Total Amount abgezogen, um den
     * verbleibenden Zahlungsbetrag zu ermitteln.
     */
    val totalPrepaidAmount: BigDecimal,

    /**
     * Der noch zu zahlende Betrag, den der Kunde begleichen muss. Er ergibt sich aus dem Grand Total Amount abzüglich
     * des Total Prepaid Amount und eventueller weiterer Gutschriften oder Anpassungen.
     */
    val duePayableAmount: BigDecimal
) {
    override fun toString() = "${duePayableAmount.toPlainString()} (net: ${taxBasisTotalAmount.toPlainString()}, tax: ${taxTotalAmount.toPlainString()})"
}
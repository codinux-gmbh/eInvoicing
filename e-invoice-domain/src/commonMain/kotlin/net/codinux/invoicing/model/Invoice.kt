package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class Invoice(
    val details: InvoiceDetails,
    val supplier: Party,
    val customer: Party,
    val items: List<InvoiceItem>,

    /**
     * An identifier assigned by the Buyer used for internal routing purposes.
     *
     * The identifier is defined by the Buyer (e.g. contact ID, department, office id, project code), but provided by the
     * Seller in the Invoice.
     *
     * In XRechnung mandatory for invoices to government agencies in Germany (B2G and G2G) to set the Leitweg-ID here.
     *
     * From XRechnung specification:
     * "Anmerkung: Im Rahmen des Steuerungsprojekts eRechnung ist mit der so genannten Leitweg-ID eine Zuord-
     * nungsmöglichkeit entwickelt worden, deren verbindliche Nutzung von Bund und mehreren Ländern vorgegeben
     * wird. Die Leitweg-ID ist prinzipiell für Bund, Länder und Kommunen einsetzbar (B2G, G2G). Für die Darstellung
     * der Leitweg-ID wird das in XRechnung verpflichtende Feld Buyer Reference benutzt."
     */
    val customerReferenceNumber: String? = null,

    val amountAdjustments: AmountAdjustments? = null,

    /**
     * The total amounts of the invoice.
     *
     * For outgoing invoices: You don't have to calculate them, we do this for you. This ensures that all total amounts
     * are in accordance to other data of the invoice like the invoice item amounts and amount adjustments.
     */
    var totals: TotalAmounts? = null
) {
    override fun toString() = "$details to $customer ${totals?.duePayableAmount?.let { " (${it.toPlainString()})" } ?: ""}"
}
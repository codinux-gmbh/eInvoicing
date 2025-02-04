package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.codes.Currency

@Serializable
class InvoiceDetails(
    val invoiceNumber: String,

    /**
     * The date when the Invoice was issued.
     *
     * Rechnungsdatum.
     * Das Datum, an dem die Rechnung ausgestellt wurde.
     */
    val invoiceDate: LocalDate,

    val currency: Currency = Currency.Euro,

    val serviceDate: ServiceDate? = null,

//    val orderNumber: String? = null,
//    val orderDate: LocalDate? = null,

    /**
     * The date when the payment is due.
     *
     * Fälligkeitsdatum.
     * Der Termin, zu dem die Zahlung fällig ist.
     * Das Fälligkeitsdatum der Zahlung spiegelt das Fälligkeitsdatum der Nettozahlung wider. Bei Teilzahlungen gibt
     * dies das erste Fälligkeitsdatum einer Nettozahlung an. Die entsprechende Beschreibung von komplexeren
     * Zahlungsbedingungen kann in BT-20 angegeben werden.
     */
    val dueDate: LocalDate? = null,
    val paymentDescription: String? = null,
) {
    override fun toString() = "$invoiceDate $invoiceNumber"
}
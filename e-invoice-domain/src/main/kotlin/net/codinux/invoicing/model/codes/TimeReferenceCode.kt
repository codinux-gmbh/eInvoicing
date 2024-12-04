package net.codinux.invoicing.model.codes

/**
 * Subset of UNTDID 2005/ UNTDID 2475 — Event time code for field BT-8.
 */
enum class TimeReferenceCode(val code: Int, val description: String, val germanDescription: String) {

    // UNTDID 2005

    InvoiceDocumentIssueDate(3, "Invoice document issue date time", "Ausstellungsdatum des Rechnungsdokuments"),

    DeliveryDate(35, "Delivery date/time, actual", "tatsächliches Lieferdatum"),

    PaidToDate(432, "Paid to date", "Datum der Zahlung"),

    // the specification tricks me here: While XRechnung only mentions the values from UNTDID 2005,
    // ZUGFeRD mixes in his spec above values with these from UNTDID 2475:

//    DateOfInvoice(5, "Date of invoice"),
//
//    DateOfDelivery(29, "Date of delivery of goods to establishments/domicile/site"),
//
//    PaymentDate(72, "Payment date"),

}
package net.codinux.invoicing.format

/**
 * The specific format of an e-Invoice.
 *
 * The basic standards CII and UBL can be used for many different (regional) specific e-Invoice formats like
 * Factur-X (based on CII) in France and Germany, XRechnung (based on CII or UBL) in Germany, Facturae in Spain,
 * fattura PA in Italy, Finvoice in Finnland, ...
 */
enum class EInvoiceFormat {
    FacturX,
    Zugferd,
    XRechnung
}
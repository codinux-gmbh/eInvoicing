package net.codinux.invoicing.format

/**
 * Basic e-Invoicing Standards. Only [CII] and [UBL] are accepted by EU 16931.
 */
enum class EInvoicingStandard {
    CII,
    UBL,
    Other
}
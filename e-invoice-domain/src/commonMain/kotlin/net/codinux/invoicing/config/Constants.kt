package net.codinux.invoicing.config

import net.codinux.invoicing.format.EInvoiceFormat

object Constants {

    const val FacturXProfileName = "EN16931" // available values: MINIMUM, BASICWL, BASIC, CIUS, EN16931, EXTENDED, XRECHNUNG

    const val XRechnungProfileName = "XRECHNUNG"


    const val FacturXFilename = "factur-x.xml"

    const val ZugferdFilename = "zugferd-invoice.xml"

    const val XRechnungFilename = "xrechnung.xml"

    val KnownEInvoiceXmlAttachmentNames = listOf(
        FacturXFilename, ZugferdFilename, XRechnungFilename // also "ZUGFeRD-invoice.xml" is found but we make compare case insensitive anyway
    )

    fun isKnownEInvoiceXmlAttachmentName(filename: String): Boolean =
        filename.lowercase() in KnownEInvoiceXmlAttachmentNames


    fun getProfileNameForFormat(format: EInvoiceFormat) = when (format) {
        EInvoiceFormat.FacturX, EInvoiceFormat.Zugferd -> FacturXProfileName
        EInvoiceFormat.XRechnung -> XRechnungProfileName
    }

    fun getFilenameForFormat(format: EInvoiceFormat) = when (format) {
        EInvoiceFormat.FacturX, EInvoiceFormat.Zugferd -> FacturXFilename // as this method is only used for adding attachments, we don't support Zugferd attachment name, use that of Factur-X
        EInvoiceFormat.XRechnung -> XRechnungFilename
        // other available values: "zugferd-invoice.xml" (ZF v2), "ZUGFeRD-invoice.xml" (ZF v1) ("order-x.xml", "cida.xml")
    }

}
package net.codinux.invoicing.config

import net.codinux.invoicing.model.EInvoiceXmlFormat

object Constants {

    val KnownEInvoiceXmlAttachmentNames = listOf(
        "factur-x.xml", "zugferd-invoice.xml", "xrechnung.xml" // also "ZUGFeRD-invoice.xml" is found but we make compare case insensitive anyway
    )


    fun getProfileNameForFormat(format: EInvoiceXmlFormat) = when (format) {
        EInvoiceXmlFormat.FacturX -> "EN16931" // available values: MINIMUM, BASICWL, BASIC, CIUS, EN16931, EXTENDED, XRECHNUNG
        EInvoiceXmlFormat.XRechnung -> "XRECHNUNG"
    }

    fun getFilenameForFormat(format: EInvoiceXmlFormat) = when (format) {
        EInvoiceXmlFormat.FacturX -> "factur-x.xml"
        EInvoiceXmlFormat.XRechnung -> "xrechnung.xml"
        // other available values: "zugferd-invoice.xml" (ZF v2), "ZUGFeRD-invoice.xml" (ZF v1) ("order-x.xml", "cida.xml")
    }

}
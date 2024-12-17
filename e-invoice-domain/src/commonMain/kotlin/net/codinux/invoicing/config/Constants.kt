package net.codinux.invoicing.config

import net.codinux.invoicing.model.EInvoiceXmlFormat

object Constants {

    const val FacturXProfileName = "EN16931" // available values: MINIMUM, BASICWL, BASIC, CIUS, EN16931, EXTENDED, XRECHNUNG

    const val XRechnungProfileName = "XRECHNUNG"


    const val FacturXFilename = "factur-x.xml"

    const val ZugferdFilename = "zugferd-invoice.xml"

    const val XRechnungFilename = "xrechnung.xml"

    val KnownEInvoiceXmlAttachmentNames = listOf(
        FacturXFilename, ZugferdFilename, XRechnungFilename // also "ZUGFeRD-invoice.xml" is found but we make compare case insensitive anyway
    )


    fun getProfileNameForFormat(format: EInvoiceXmlFormat) = when (format) {
        EInvoiceXmlFormat.FacturX -> FacturXProfileName
        EInvoiceXmlFormat.XRechnung -> XRechnungProfileName
    }

    fun getFilenameForFormat(format: EInvoiceXmlFormat) = when (format) {
        EInvoiceXmlFormat.FacturX -> FacturXFilename
        EInvoiceXmlFormat.XRechnung -> XRechnungFilename
        // other available values: "zugferd-invoice.xml" (ZF v2), "ZUGFeRD-invoice.xml" (ZF v1) ("order-x.xml", "cida.xml")
    }

}
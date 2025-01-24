package net.codinux.invoicing.format

import net.codinux.log.logger
import nl.adaptivity.xmlutil.EventType
import nl.adaptivity.xmlutil.XmlReader
import nl.adaptivity.xmlutil.xmlStreaming

open class EInvoiceFormatDetector {

    private val log by logger()


    open fun detectFormat(xml: String): EInvoiceFormatDetectionResult? =
        try {
            val reader = xmlStreaming.newReader(xml)

            if (reader.hasNext() == false) {
                null
            } else {
                val event = reader.nextTag()
                if (event != EventType.START_ELEMENT) {
                    null
                } else {
                    val standard = detectEInvoiceStandard(reader.name.localPart, reader.name.namespaceURI)

                    if (standard == EInvoicingStandard.CII && xml.contains("GuidelineSpecifiedDocumentContextParameter>")) {
                        detectCiiFormat(reader)
                    } else if (standard == EInvoicingStandard.UBL && xml.contains("CustomizationID>")) {
                        detectUblFormat(reader)
                    } else {
                        EInvoiceFormatDetectionResult(standard)
                    }
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not detect invoice format from XML file ${xml.substring(0, 250)}" }
            null
        }

    open fun detectEInvoiceStandard(rootElementName: String, rootElementNamespaceUri: String): EInvoicingStandard =
        // TODO: is it possible that it does not end with ":100"?
        if (rootElementNamespaceUri.startsWith("urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:")) {
            EInvoicingStandard.CII
        }
        // TODO: is it possible that is does not end with :xsd:Invoice-2? UBL 1?
        else if (rootElementNamespaceUri.startsWith("urn:oasis:names:specification:ubl:schema:xsd:Invoice")) {
            EInvoicingStandard.UBL
        } else {
            EInvoicingStandard.Other
        }

    protected open fun detectCiiFormat(reader: XmlReader): EInvoiceFormatDetectionResult? {
        while (reader.hasNext()) {
            var event = reader.next() // .nextTag() throws an exception on TEXT events

            if (event == EventType.START_ELEMENT) {
                if (reader.localName == "GuidelineSpecifiedDocumentContextParameter") {
                    // not get in its content the <ram:ID> -> TEXT node
                    while (reader.hasNext()) {
                        event = reader.next()
                        if (event == EventType.TEXT) {
                            return detectCiiFormat(reader.text)
                        } else if (event == EventType.END_ELEMENT) {
                            return null
                        }
                    }
                }
            }
        }

        return null
    }

    protected open fun detectCiiFormat(formatId: String): EInvoiceFormatDetectionResult? =
        when (formatId) {
            // for a list of CII and UBL formats see: https://peppol.helger.com/public/locale-en_US/menuitem-validation-ws2
            "urn:factur-x.eu:1p0:minimum" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
            "urn:factur-x.eu:1p0:basicwl" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
            "urn:cen.eu:en16931:2017#compliant#urn:factur-x.eu:1p0:basic" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
            "urn:cen.eu:en16931:2017" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
            "urn:cen.eu:en16931:2017#conformant#urn:factur-x.eu:1p0:extended" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)

            // XRechnung
            "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_1.2",
            "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_2.1",
            "urn:cen.eu:en16931:2017#compliant#urn:xeinkauf.de:kosit:xrechnung_3.0" -> ciiResult(EInvoiceFormat.FacturX, "2", FacturXProfile.XRechnung) // TODO: or use extra EInvoiceFormat?

            // Zugferd 2.0
            "urn:zugferd.de:2p0:minimum" -> ciiResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Minimum)
            "urn:cen.eu:en16931:2017#compliant#urn:zugferd.de:2p0:basic" -> ciiResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Basic)
            "urn:cen.eu:en16931:2017#conformant#urn:zugferd.de:2p0:extended" -> ciiResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Extended)

            else -> null
        }

    protected open fun ciiResult(format: EInvoiceFormat, formatVersion: String, profile: FacturXProfile) =
        EInvoiceFormatDetectionResult(EInvoicingStandard.CII, format, formatVersion, profile)

    protected open fun detectUblFormat(reader: XmlReader): EInvoiceFormatDetectionResult? {
        return null // TODO
    }

    protected open fun detectUblFormat(formatId: String): EInvoiceFormatDetectionResult? =
        when (formatId) {
            // XRechnung
            "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_1.2",
            "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_2.1",
            "urn:cen.eu:en16931:2017#compliant#urn:xeinkauf.de:kosit:xrechnung_3.0" ->
                EInvoiceFormatDetectionResult(EInvoicingStandard.UBL, EInvoiceFormat.FacturX, "2", FacturXProfile.XRechnung) // TODO: or use extra EInvoiceFormat?

            else -> null
        }

}
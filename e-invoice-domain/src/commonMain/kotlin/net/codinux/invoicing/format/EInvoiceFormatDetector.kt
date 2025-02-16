package net.codinux.invoicing.format

import net.codinux.invoicing.reader.fixXmlForReading
import net.codinux.invoicing.reader.readToNextStartTag
import net.codinux.invoicing.reader.readToNextText
import net.codinux.kotlin.extensions.substringAfterLastOrNull
import net.codinux.log.logger
import nl.adaptivity.xmlutil.EventType
import nl.adaptivity.xmlutil.Namespace
import nl.adaptivity.xmlutil.XmlReader
import nl.adaptivity.xmlutil.xmlStreaming

open class EInvoiceFormatDetector {

    private val log by logger()


    open fun detectFormat(xml: String): EInvoiceFormatDetectionResult? =
        try {
            val reader = xmlStreaming.newReader(fixXmlForReading(xml)) // a simple non-breaking space before first '<' makes XmlReader crash

            if (reader.readToNextStartTag() == false) {
                null
            } else {
                val standard = detectEInvoiceStandard(reader.localName, reader.namespaceURI, reader.namespaceDecls)

                if (standard == EInvoicingStandard.CII && xml.contains("GuidelineSpecifiedDocumentContextParameter>")) {
                    detectCiiFormat(reader)
                } else if (standard == EInvoicingStandard.UBL) {
                    detectUblFormat(xml, reader)
                } else {
                    EInvoiceFormatDetectionResult(standard)
                }
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not detect invoice format from XML file ${xml.substring(0, 250)}" }
            null
        }

    open fun detectEInvoiceStandard(rootElementName: String, rootElementNamespaceUri: String, namespaceDecls: List<Namespace> = emptyList()): EInvoicingStandard =
        // TODO: is it possible that it does not end with ":100"?
        if (rootElementNamespaceUri.startsWith("urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:")) {
            EInvoicingStandard.CII
        } else if (rootElementName == "CrossIndustryInvoice" &&
            namespaceDecls.any { it.namespaceURI.startsWith("urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:") }) {
            EInvoicingStandard.CII
        }
        // TODO: is it possible that is does not end with :xsd:Invoice-2? UBL 1?
        else if (rootElementNamespaceUri.startsWith("urn:oasis:names:specification:ubl:schema:xsd:Invoice")) {
            EInvoicingStandard.UBL
        } else {
            EInvoicingStandard.Other
        }

    protected open fun detectCiiFormat(reader: XmlReader): EInvoiceFormatDetectionResult? {
        while (reader.readToNextStartTag()) {
            if (reader.localName == "GuidelineSpecifiedDocumentContextParameter") {
                // now get in its content the <ram:ID> -> TEXT node
                return if (reader.readToNextStartTag() && reader.readToNextText()) {
                    detectCiiFormat(reader.text)
                } else {
                    EInvoiceFormatDetectionResult.CII
                }
            }
        }

        return EInvoiceFormatDetectionResult.CII
    }

    protected open fun detectCiiFormat(formatId: String): EInvoiceFormatDetectionResult? =
        when (formatId) {
            /*
                Laut ChatGPT:

                CII (Cross Industry Invoice):
                    Namespace: "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice"
                    Root Element: <rsm:CrossIndustryInvoice>

                UBL (Universal Business Language):
                    Namespace: "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"
                    Root Element: <Invoice>

                XRechnung (a UBL customization for Germany):
                    Namespace: "urn:eu:factur-x:ubl:invoice:2p1" (for Factur-X UBL version)
                    Additional elements might include <cbc:CustomizationID> containing "urn:cen.eu:en16931:2017"

                ZUGFeRD 1.x:
                    Namespace: "urn:ferd:CrossIndustryDocument:invoice:1p0"
                    Root Element: <rsm:CrossIndustryDocument>

                Profiles:

                - Zugferd 1
                            "urn:factur-x.eu:1p0:basic" → Basic
                            "urn:factur-x.eu:1p0:basicwl" → Basic Without Lines
                            "urn:factur-x.eu:1p0:en16931:extended" → Extended
                            "urn:factur-x.eu:1p0:en16931:minimum" → Minimum

                - XRechnung:
                    <cbc:CustomizationID> with "urn:cen.eu:en16931:2017"

                - UBL:
                    <cbc:ProfileID> or <cbc:CustomizationID> can provide profile-specific information.
             */
            // for a list of CII and UBL formats see: https://peppol.helger.com/public/locale-en_US/menuitem-validation-ws2
            "urn:factur-x.eu:1p0:minimum" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
            "urn:factur-x.eu:1p0:basicwl" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
            "urn:cen.eu:en16931:2017#compliant#urn:factur-x.eu:1p0:basic" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
            "urn:cen.eu:en16931:2017" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
            "urn:cen.eu:en16931:2017#conformant#urn:factur-x.eu:1p0:extended" -> ciiResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)

            // Zugferd 2.0
            "urn:zugferd.de:2p0:minimum" -> ciiResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Minimum)
            "urn:cen.eu:en16931:2017#compliant#urn:zugferd.de:2p0:basic" -> ciiResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Basic)
            "urn:cen.eu:en16931:2017#conformant#urn:zugferd.de:2p0:extended" -> ciiResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Extended)

            else -> {
                if (isXRechnungFormatId(formatId)) {
                    EInvoiceFormatDetectionResult(EInvoicingStandard.CII, EInvoiceFormat.XRechnung, getXRechnungVersion(formatId))
                } else {
                    EInvoiceFormatDetectionResult.CII
                }
            }
        }

    protected open fun ciiResult(format: EInvoiceFormat, formatVersion: String, profile: FacturXProfile) =
        EInvoiceFormatDetectionResult(EInvoicingStandard.CII, format, formatVersion, profile)

    protected open fun detectUblFormat(xml: String, reader: XmlReader): EInvoiceFormatDetectionResult? {
        val containsUblVersion = xml.contains("UBLVersionID>")
        val containsCustomizationId = xml.contains("CustomizationID>")
        val containsProfileId = xml.contains("ProfileID>")

        return if (containsUblVersion || containsCustomizationId || containsProfileId) {
            detectUblFormat(reader, containsUblVersion, containsCustomizationId, containsProfileId)
        } else {
            EInvoiceFormatDetectionResult.UBL
        }
    }

    protected open fun detectUblFormat(reader: XmlReader, containsUblVersion: Boolean, containsCustomizationId: Boolean,
                                       containsProfileId: Boolean): EInvoiceFormatDetectionResult? {
        var ublVersion: String? = null
        var customizationId: String? = null
        var profileId: String? = null

        while (reader.hasNext()) {
            var event = reader.next() // .nextTag() throws an exception on TEXT events
            if (event == EventType.START_ELEMENT) {
                if (reader.localName == "UBLVersionID") {
                    event = reader.next()
                    if (event == EventType.TEXT) {
                        ublVersion = reader.text
                    }
                } else if (reader.localName == "CustomizationID") {
                    event = reader.next()
                    if (event == EventType.TEXT) {
                        customizationId = reader.text
                    }
                } else if (reader.localName == "ProfileID") {
                    event = reader.next()
                    if (event == EventType.TEXT) {
                        profileId = reader.text
                    }
                }

                if ((containsUblVersion == false || ublVersion != null) && (containsCustomizationId == false || customizationId != null)
                    && (containsProfileId == false || profileId != null)) {
                    val (format, version) = if (customizationId == null) null to null else detectUblFormatAndVersion(customizationId)
                    return EInvoiceFormatDetectionResult(EInvoicingStandard.UBL, format, version, null, profileId, ublVersion)
                }
            }
        }

        return EInvoiceFormatDetectionResult.UBL
    }

    protected open fun detectUblFormatAndVersion(formatId: String): Pair<EInvoiceFormat?, String?> =
        if (isXRechnungFormatId(formatId)) {
            EInvoiceFormat.XRechnung to getXRechnungVersion(formatId)
        } else {
            null to null
        }

    protected open fun isXRechnungFormatId(formatId: String): Boolean =
        formatId.contains("urn:xoev-de:kosit:standard:xrechnung") || formatId.contains("urn:xeinkauf.de:kosit:xrechnung")

    protected open fun getXRechnungVersion(formatId: String): String? {
        // XRechnung format IDs look like:
        // "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_1.2",
        // "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_2.1",
        // "urn:cen.eu:en16931:2017#compliant#urn:xeinkauf.de:kosit:xrechnung_3.0"
        return formatId.substringAfterLastOrNull(":xrechnung_")
    }

}
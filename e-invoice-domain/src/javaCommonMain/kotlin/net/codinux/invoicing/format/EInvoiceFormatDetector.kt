package net.codinux.invoicing.format

import net.codinux.invoicing.extension.getElementOrNull
import net.codinux.invoicing.extension.getNamespaceDeclarations
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.platform.JavaPlatform
import net.codinux.kotlin.extensions.substringBeforeOrNull
import net.codinux.log.logger
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.ByteArrayInputStream
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.extension
import kotlin.io.path.inputStream
import kotlin.io.path.readText

open class EInvoiceFormatDetector(
    protected open val pdfAttachmentReader: PdfAttachmentReader = JavaPlatform.pdfAttachmentReader,
) {

    protected val documentBuilderFactory = DocumentBuilderFactory.newInstance()

    private val log by logger()


    open fun detectFormat(file: Path): EInvoiceFormatDetectionResult? =
        // TODO: improve file detection
        if (file.extension.lowercase() == "pdf") {
            pdfAttachmentReader.getFileAttachments(file.inputStream()).invoiceXml?.let { xml ->
                detectFormat(xml)
            }
        } else if (file.extension.lowercase() == "xml") {
            detectFormat(file.readText())
        } else {
            null
        }

    open fun detectFormat(xml: String): EInvoiceFormatDetectionResult? =
        try {
            detectFormat(documentBuilderFactory.newDocumentBuilder().parse(ByteArrayInputStream(xml.encodeToByteArray())))
        } catch (e: Throwable) {
            log.error(e) { "Could not detect invoice format from XML file ${xml.substring(0, 250)}" }
            null
        }

    open fun detectFormat(xmlDocument: Document): EInvoiceFormatDetectionResult? {
        val root = xmlDocument.documentElement
        val namespace = getRootElementNamespace(root)
        if (namespace != null) {
            val formatFromRootNamespace = when (namespace) {
                "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100" -> EInvoiceFormat.CII
                else -> null
            }

            if (formatFromRootNamespace == EInvoiceFormat.CII) {
                return detectFormatOfCiiFile(root)
                    ?: EInvoiceFormatDetectionResult(formatFromRootNamespace)
            }
        }

        return null
    }

    protected open fun getRootElementNamespace(root: Element): String? {
        val namespacePrefix = root.tagName.substringBeforeOrNull(':')

        return if (namespacePrefix != null) {
            val namespaceDeclarations = root.getNamespaceDeclarations()
            namespaceDeclarations[namespacePrefix]
        } else {
            root.getAttribute("xmlns").takeUnless { it.isBlank() }
        }
    }

    protected open fun detectFormatOfCiiFile(root: Element): EInvoiceFormatDetectionResult? {
        val ciiFormatId = (root.getElementOrNull("rsm:ExchangedDocumentContext") ?: root.getElementOrNull("ExchangedDocumentContext")) // Zugferd 2.0 uses "ExchangedDocumentContext:
            ?.getElementOrNull("ram:GuidelineSpecifiedDocumentContextParameter")
            ?.getElementOrNull("ram:ID")
            ?.textContent

        return if (ciiFormatId == null) {
            null
        } else {
            when (ciiFormatId) {
                // for a list of CII and UBL formats see: https://peppol.helger.com/public/locale-en_US/menuitem-validation-ws2
                "urn:factur-x.eu:1p0:minimum" -> EInvoiceFormatDetectionResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Minimum)
                "urn:factur-x.eu:1p0:basicwl" -> EInvoiceFormatDetectionResult(EInvoiceFormat.FacturX, "1", FacturXProfile.BasicWL)
                "urn:cen.eu:en16931:2017#compliant#urn:factur-x.eu:1p0:basic" -> EInvoiceFormatDetectionResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Basic)
                "urn:cen.eu:en16931:2017" -> EInvoiceFormatDetectionResult(EInvoiceFormat.FacturX, "1", FacturXProfile.EN16931)
                "urn:cen.eu:en16931:2017#conformant#urn:factur-x.eu:1p0:extended" -> EInvoiceFormatDetectionResult(EInvoiceFormat.FacturX, "1", FacturXProfile.Extended)

                // XRechnung
                "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_1.2",
                "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_2.1",
                "urn:cen.eu:en16931:2017#compliant#urn:xeinkauf.de:kosit:xrechnung_3.0" -> EInvoiceFormatDetectionResult(EInvoiceFormat.FacturX, "2", FacturXProfile.XRechnung) // TODO: or use extra EInvoiceFormat?

                // Zugferd 2.0
                "urn:zugferd.de:2p0:minimum" -> EInvoiceFormatDetectionResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Minimum)
                "urn:cen.eu:en16931:2017#compliant#urn:zugferd.de:2p0:basic" -> EInvoiceFormatDetectionResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Basic)
                "urn:cen.eu:en16931:2017#conformant#urn:zugferd.de:2p0:extended" -> EInvoiceFormatDetectionResult(EInvoiceFormat.Zugferd, "2", FacturXProfile.Extended)

                else -> null
            }
        }
    }

}
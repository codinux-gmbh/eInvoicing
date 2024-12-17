package net.codinux.invoicing.converter

import net.codinux.invoicing.creation.EInvoiceXmlCreator
import net.codinux.invoicing.model.Invoice
import org.mustangproject.CII.CIIToUBL
import org.mustangproject.ZUGFeRD.ZUGFeRDVisualizer
import java.io.File

open class EInvoiceConverter {

    open fun convertInvoiceToHtml(invoice: Invoice, outputFile: File, language: ZUGFeRDVisualizer.Language = ZUGFeRDVisualizer.Language.DE) =
        convertInvoiceToHtml(createXRechnungXml(invoice), outputFile, language)

    open fun convertInvoiceToHtml(invoiceXml: String, outputFile: File, language: ZUGFeRDVisualizer.Language = ZUGFeRDVisualizer.Language.DE): String {
        val xmlFile = File.createTempFile("Zugferd", ".xml")
            .also { it.writeText(invoiceXml) }

        val visualizer = ZUGFeRDVisualizer()

        val html = visualizer.visualize(xmlFile.absolutePath, language)

        outputFile.writeText(html)
        copyResource("xrechnung-viewer.css", outputFile, ".css")
        copyResource("xrechnung-viewer.js", outputFile, ".js")

        xmlFile.delete()

        return html
    }


    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a ZUGFeRD or Factur-X invoice, to UBL (Universal Business Language).
     */
    open fun convertCiiToUbl(invoice: Invoice) = convertCiiToUbl(createXRechnungXml(invoice))

    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a ZUGFeRD or Factur-X invoice, to UBL (Universal Business Language).
     */
    open fun convertCiiToUbl(invoiceXml: String): String {
        // TODO: extract a common method for this
        val xmlFile = File.createTempFile("Zugferd", ".xml")
            .also { it.writeText(invoiceXml) }
        val ublFile = File(xmlFile.parentFile, xmlFile.nameWithoutExtension + "-ubl.xml")

        convertCiiToUbl(xmlFile, ublFile)

        val ubl = ublFile.readText()

        xmlFile.delete()
        ublFile.delete()

        return ubl
    }

    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a ZUGFeRD or Factur-X invoice, to UBL (Universal Business Language).
     */
    open fun convertCiiToUbl(xmlFile: File, outputFile: File) {
        val cii2Ubl = CIIToUBL()
        cii2Ubl.convert(xmlFile, outputFile)
    }


    protected open fun createXRechnungXml(invoice: Invoice): String = EInvoiceXmlCreator().createXRechnungXml(invoice)

    protected open fun copyResource(resourceName: String, outputFile: File, outputFileExtension: String) {
        javaClass.classLoader.getResourceAsStream(resourceName).use { inputStream ->
            File(outputFile.parentFile, outputFile.nameWithoutExtension + outputFileExtension).outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
        }
    }

}
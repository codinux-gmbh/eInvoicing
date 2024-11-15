package net.codinux.invoicing.converter

import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.model.Invoice
import org.mustangproject.CII.CIIToUBL
import org.mustangproject.ZUGFeRD.ZUGFeRDVisualizer
import java.io.File

class EInvoiceConverter {

    fun convertInvoiceToHtml(invoice: Invoice, outputFile: File, language: ZUGFeRDVisualizer.Language = ZUGFeRDVisualizer.Language.DE) =
        convertInvoiceToHtml(createXRechnungXml(invoice), outputFile, language)

    fun convertInvoiceToHtml(invoiceXml: String, outputFile: File, language: ZUGFeRDVisualizer.Language = ZUGFeRDVisualizer.Language.DE): String {
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
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a Zugferd or Factur-X invoice, to UBL (Universal Business Language).
     */
    fun convertCiiToUbl(invoice: Invoice) = convertCiiToUbl(createXRechnungXml(invoice))

    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a Zugferd or Factur-X invoice, to UBL (Universal Business Language).
     */
    fun convertCiiToUbl(invoiceXml: String): String {
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
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a Zugferd or Factur-X invoice, to UBL (Universal Business Language).
     */
    fun convertCiiToUbl(xmlFile: File, outputFile: File) {
        val cii2Ubl = CIIToUBL()
        cii2Ubl.convert(xmlFile, outputFile)
    }


    private fun createXRechnungXml(invoice: Invoice): String = EInvoiceCreator().createXRechnungXml(invoice)

    private fun copyResource(resourceName: String, outputFile: File, outputFileExtension: String) {
        javaClass.classLoader.getResourceAsStream(resourceName).use {
            it?.copyTo(File(outputFile.parentFile, outputFile.nameWithoutExtension + outputFileExtension).outputStream())
        }
    }

}
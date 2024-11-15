package net.codinux.invoicing.creation

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*
import java.io.File

class EInvoiceCreator(
    private val mapper: MustangMapper = MustangMapper()
) {

    fun createXRechnungXml(invoice: Invoice): String {
        val provider = ZUGFeRD2PullProvider()
        provider.profile = Profiles.getByName("XRechnung")

        return createXml(provider, invoice)
    }

    fun createZugferdXml(invoice: Invoice): String {
        val exporter = ZUGFeRDExporterFromA3()
            .setProfile("EN16931") // required for XML?

        return createXml(exporter.provider, invoice)
    }

    fun createZugferdPdf(invoice: Invoice, outputFile: File) {
        val xml = createZugferdXml(invoice)
        val xmlFile = File.createTempFile(outputFile.nameWithoutExtension, ".xml")
            .also { it.writeText(xml) }
        val pdfFile = File(xmlFile.parentFile, xmlFile.nameWithoutExtension + ".pdf")

        val visualizer = ZUGFeRDVisualizer()
        visualizer.toPDF(xmlFile.absolutePath, pdfFile.absolutePath)

        combinePdfAndXml(pdfFile, xml, outputFile)

        xmlFile.delete()
        pdfFile.delete()
    }

    fun combinePdfAndXml(pdfFile: File, xml: String, outputFile: File) {
        val exporter = ZUGFeRDExporterFromA3()
            .setZUGFeRDVersion(2)
            .setProfile("EN16931") // available values: MINIMUM, BASICWL, BASIC, CIUS, EN16931, EXTENDED, XRECHNUNG
//            .disableFacturX()
            .setProducer("danki die geile Sau")
            .setCreator(System.getProperty("user.name"))

        exporter.load(pdfFile.inputStream())
        exporter.setXML(xml.toByteArray())

        exporter.export(outputFile.outputStream())
    }

    // extract to EInvoiceConverter?
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

    private fun copyResource(resourceName: String, outputFile: File, outputFileExtension: String) {
        javaClass.classLoader.getResourceAsStream(resourceName).use {
            it?.copyTo(File(outputFile.parentFile, outputFile.nameWithoutExtension + outputFileExtension).outputStream())
        }
    }


    private fun createXml(provider: IXMLProvider, invoice: Invoice): String {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return String(provider.xml, Charsets.UTF_8)
    }

}
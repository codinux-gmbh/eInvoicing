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

        combinePdfAndInvoiceXml(xml, pdfFile, outputFile)

        xmlFile.delete()
        pdfFile.delete()
    }

    fun combinePdfAndInvoiceXml(invoice: Invoice, pdfFile: File, outputFile: File) =
        combinePdfAndInvoiceXml(createZugferdXml(invoice), pdfFile, outputFile)

    fun combinePdfAndInvoiceXml(invoiceXml: String, pdfFile: File, outputFile: File) {
        val exporter = ZUGFeRDExporterFromA3()
            .setZUGFeRDVersion(2)
            .setProfile("EN16931") // available values: MINIMUM, BASICWL, BASIC, CIUS, EN16931, EXTENDED, XRECHNUNG
//            .disableFacturX()
            .setProducer("danki die geile Sau")
            .setCreator(System.getProperty("user.name"))

        exporter.load(pdfFile.inputStream())
        exporter.setXML(invoiceXml.toByteArray())

        exporter.export(outputFile.outputStream())
    }


    private fun createXml(provider: IXMLProvider, invoice: Invoice): String {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return String(provider.xml, Charsets.UTF_8)
    }

}
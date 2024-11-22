package net.codinux.invoicing.creation

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*
import java.io.File

open class EInvoiceCreator(
    protected open val mapper: MustangMapper = MustangMapper()
) {

    open fun createXRechnungXml(invoice: Invoice): String {
        val provider = ZUGFeRD2PullProvider()
        provider.profile = Profiles.getByName("XRechnung")

        return createXml(provider, invoice)
    }


    /**
     * Synonym for [createFacturXXml] (ZUGFeRD 2 is a synonym for Factur-X).
     */
    open fun createZugferdXml(invoice: Invoice) = createFacturXXml(invoice)

    open fun createFacturXXml(invoice: Invoice): String {
        val exporter = ZUGFeRDExporterFromA3()
            .setProfile("EN16931") // required for XML?

        return createXml(exporter.provider, invoice)
    }

    /**
     * Synonym for [createFacturXPdf] (ZUGFeRD 2 is a synonym for Factur-X).
     */
    open fun createZugferdPdf(invoice: Invoice, outputFile: File) = createFacturXPdf(invoice, outputFile)

    open fun createFacturXPdf(invoice: Invoice, outputFile: File) {
        val xml = createFacturXXml(invoice)
        val xmlFile = File.createTempFile(outputFile.nameWithoutExtension, ".xml")
            .also { it.writeText(xml) }
        val pdfFile = File(xmlFile.parentFile, xmlFile.nameWithoutExtension + ".pdf")

        val visualizer = ZUGFeRDVisualizer()
        visualizer.toPDF(xmlFile.absolutePath, pdfFile.absolutePath)

        attachInvoiceXmlToPdf(xml, pdfFile, outputFile)

        xmlFile.delete()
        pdfFile.delete()
    }


    open fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: File, outputFile: File) =
        attachInvoiceXmlToPdf(createFacturXXml(invoice), pdfFile, outputFile)

    open fun attachInvoiceXmlToPdf(invoiceXml: String, pdfFile: File, outputFile: File) {
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


    protected open fun createXml(provider: IXMLProvider, invoice: Invoice): String {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return String(provider.xml, Charsets.UTF_8)
    }

}
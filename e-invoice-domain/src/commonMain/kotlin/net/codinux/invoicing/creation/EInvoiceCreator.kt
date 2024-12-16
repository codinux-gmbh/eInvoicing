package net.codinux.invoicing.creation

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*
import java.io.File
import java.io.InputStream
import java.io.OutputStream

open class EInvoiceCreator(
    protected open val mapper: MustangMapper = MustangMapper()
) {

    open fun createXRechnungXml(invoice: Invoice) = createXml(invoice, EInvoiceXmlFormat.XRechnung)

    /**
     * Synonym for [createFacturXXml] (ZUGFeRD 2 is a synonym for Factur-X).
     */
    open fun createZugferdXml(invoice: Invoice) = createFacturXXml(invoice)

    open fun createFacturXXml(invoice: Invoice) = createXml(invoice, EInvoiceXmlFormat.FacturX)

    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): String {
        val exporter = ZUGFeRDExporterFromA3()
            .setProfile(getProfileNameForFormat(format))

        return createXml(exporter.provider, invoice)
    }

    protected open fun createXml(provider: IXMLProvider, invoice: Invoice): String {
        val transaction = mapper.mapToTransaction(invoice)

        provider.generateXML(transaction)

        return String(provider.xml, Charsets.UTF_8)
    }


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    @JvmOverloads
    open fun createPdfWithAttachedXml(invoice: Invoice, outputFile: File, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX) {
        val xml = createXml(invoice, format)

        createPdfWithAttachedXml(xml, format, outputFile)
    }

    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: File) {
        outputFile.outputStream().use { outputStream ->
            createPdfWithAttachedXml(invoiceXml, format, outputStream)
        }
    }

    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: OutputStream) {
        val xmlFile = File.createTempFile("${format.name}-invoice", ".xml")
            .also { it.writeText(invoiceXml) }
        val pdfFile = File(xmlFile.parentFile, xmlFile.nameWithoutExtension + ".pdf")

        val visualizer = ZUGFeRDVisualizer()
        visualizer.toPDF(xmlFile.absolutePath, pdfFile.absolutePath)

        pdfFile.inputStream().use { inputStream ->
            attachInvoiceXmlToPdf(invoiceXml, format, inputStream, outputFile)
        }

        xmlFile.delete()
        pdfFile.delete()
    }


    @JvmOverloads
    open fun attachInvoiceXmlToPdf(invoice: Invoice, pdfFile: File, outputFile: File, format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX) =
        attachInvoiceXmlToPdf(createXml(invoice, format), format, pdfFile, outputFile)

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: File, outputFile: File) {
        pdfFile.inputStream().use { inputStream ->
            outputFile.outputStream().use { outputStream ->
                attachInvoiceXmlToPdf(invoiceXml, format, inputStream, outputStream)
            }
        }
    }

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: InputStream, outputFile: OutputStream) =
        attachInvoiceXmlToPdf(invoiceXml, format, pdfFile.readAllBytes(), outputFile)

    open fun attachInvoiceXmlToPdf(invoiceXml: String, format: EInvoiceXmlFormat, pdfFile: ByteArray, outputFile: OutputStream) {
        val exporter = ZUGFeRDExporterFromA3()
            .setZUGFeRDVersion(2)
            .setProfile(getProfileNameForFormat(format))
//            .disableFacturX()
            .setProducer("danki die geile Sau")
            .setCreator(System.getProperty("user.name"))
            .setCreatorTool("Unglaublich geiles eInvoicing Tool von codinux")

        exporter.load(pdfFile)
        exporter.setXML(invoiceXml.toByteArray())

        exporter.export(outputFile)
    }


    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) = when (format) {
        EInvoiceXmlFormat.FacturX -> "EN16931" // available values: MINIMUM, BASICWL, BASIC, CIUS, EN16931, EXTENDED, XRECHNUNG
        EInvoiceXmlFormat.XRechnung -> "XRECHNUNG"
    }

    protected open fun getFilenameForFormat(format: EInvoiceXmlFormat) = when (format) {
        EInvoiceXmlFormat.FacturX -> "factur-x.xml"
        EInvoiceXmlFormat.XRechnung -> "xrechnung.xml"
        // other available values: "zugferd-invoice.xml" (ZF v2), "ZUGFeRD-invoice.xml" (ZF v1) ("order-x.xml", "cida.xml")
    }

}
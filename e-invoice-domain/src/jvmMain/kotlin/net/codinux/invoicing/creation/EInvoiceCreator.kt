package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*
import java.io.File
import java.io.InputStream
import java.io.OutputStream

open class EInvoiceCreator(
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator()
) {

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


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): String =
        xmlCreator.createXml(invoice, format)

    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        xmlCreator.getProfileNameForFormat(format)

}
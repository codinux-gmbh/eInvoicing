package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.*
import java.io.File
import java.io.OutputStream

open class EInvoicePdfCreator(
    protected open val attacher: EInvoiceXmlToPdfAttacher = EInvoiceXmlToPdfAttacher(),
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
            attacher.attachInvoiceXmlToPdf(invoiceXml, format, inputStream, outputFile)
        }

        xmlFile.delete()
        pdfFile.delete()
    }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): String =
        xmlCreator.createXml(invoice, format)

}
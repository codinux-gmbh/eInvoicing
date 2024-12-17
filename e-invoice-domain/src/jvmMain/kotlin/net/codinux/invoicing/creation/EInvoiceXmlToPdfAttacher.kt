package net.codinux.invoicing.creation

import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.ZUGFeRDExporterFromA3
import java.io.File
import java.io.InputStream
import java.io.OutputStream

open class EInvoiceXmlToPdfAttacher(
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator()
) {

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
            .setProducer("codinux GmbH & Co. KG")
            .setCreator(System.getProperty("user.name"))
            .setCreatorTool("eInvoicing von codinux GmbH & Co. KG")

        exporter.load(pdfFile)
        exporter.setXML(invoiceXml.toByteArray())

        exporter.export(outputFile)
    }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): String =
        xmlCreator.createXml(invoice, format)

    protected open fun getProfileNameForFormat(format: EInvoiceXmlFormat) =
        xmlCreator.getProfileNameForFormat(format)

}
package net.codinux.invoicing.creation

import net.codinux.invoicing.config.DIJava
import net.codinux.invoicing.filesystem.FilesystemService
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.Result
import org.mustangproject.ZUGFeRD.*
import java.io.File
import java.io.OutputStream
import java.nio.file.Path
import kotlin.io.path.outputStream

actual open class EInvoicePdfCreator(
    protected open val attacher: EInvoiceXmlToPdfAttacher = EInvoiceXmlToPdfAttacher(),
    protected open val xmlCreator: EInvoiceXmlCreator = EInvoiceXmlCreator(),
    protected open val filesystem: FilesystemService = DIJava.Filesystem
) {

    actual constructor() : this(EInvoiceXmlToPdfAttacher(), EInvoiceXmlCreator())

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoice: Invoice, format: EInvoiceXmlFormat): ByteArray? {
        val destinationFile = filesystem.createTempPdfFile()

        createPdfWithAttachedXml(invoice, format, destinationFile)

        return destinationFile.readBytes()
    }

    /**
     * Creates a hybrid PDF that also contains provided Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    actual open suspend fun createFacturXPdf(invoiceXml: String, format: EInvoiceXmlFormat): ByteArray? {
        val destinationFile = filesystem.createTempPdfFile()

        createPdfWithAttachedXml(invoiceXml, format, destinationFile)

        return destinationFile.readBytes()
    }


    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, outputFile: File) =
        createPdfWithAttachedXml(invoice, EInvoiceXmlFormat.FacturX, outputFile)

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, format: EInvoiceXmlFormat, outputFile: File): Result<Unit> =
        createXml(invoice, format).ifSuccessful { xml ->
            Result.success(createPdfWithAttachedXml(xml, format, outputFile))
        }

    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: File) =
        createPdfWithAttachedXml(invoiceXml, format, outputFile.outputStream())

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, outputFile: Path) =
        createPdfWithAttachedXml(invoice, EInvoiceXmlFormat.FacturX, outputFile)

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     */
    open fun createPdfWithAttachedXml(invoice: Invoice, format: EInvoiceXmlFormat, outputFile: Path): Result<Unit> =
        createXml(invoice, format).ifSuccessful { xml ->
            Result.success(createPdfWithAttachedXml(xml, format, outputFile))
        }

    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: Path) =
        createPdfWithAttachedXml(invoiceXml, format, outputFile.outputStream())

    /**
     * Creates a hybrid PDF that also contains the Factur-X / ZUGFeRD or XRechnung XML as attachment.
     *
     * Closes the OutputStream of parameter [outputFile].
     */
    open fun createPdfWithAttachedXml(invoiceXml: String, format: EInvoiceXmlFormat, outputFile: OutputStream) {
        val xmlFile = File.createTempFile("${format.name}-invoice", ".xml")
            .also { it.writeText(invoiceXml) }
        val pdfFile = File(xmlFile.parentFile, xmlFile.nameWithoutExtension + ".pdf")

        val visualizer = ZUGFeRDVisualizer()
        visualizer.toPDF(xmlFile.absolutePath, pdfFile.absolutePath)

        pdfFile.inputStream().use { inputStream ->
            attacher.attachInvoiceXmlToPdf(invoiceXml, format, inputStream, outputFile)
        }

        outputFile.close()

        xmlFile.delete()
        pdfFile.delete()
    }


    protected open fun createXml(invoice: Invoice, format: EInvoiceXmlFormat): Result<String> =
        xmlCreator.createInvoiceXmlJvm(invoice, format)

}
package net.codinux.invoicing.reader

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.PdfBoxPdfAttachmentReader
import net.codinux.log.logger
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream

open class EInvoiceReader(
    protected open val pdfAttachmentReader: PdfAttachmentReader = PdfBoxPdfAttachmentReader(),
    protected open val mapper: MustangMapper = MustangMapper()
) {

    companion object {
        val KnownEInvoiceXmlAttachmentNames = listOf(
            "factur-x.xml", "zugferd-invoice.xml", "xrechnung.xml" // also "ZUGFeRD-invoice.xml" is found but we make compare case insensitive anyway
        )
    }

    private val log by logger()


    open fun extractFromXmlOrNull(xmlFile: File) = orNull { extractFromXml(xmlFile) }

    open fun extractFromXml(xmlFile: File) = xmlFile.inputStream().use { extractFromXml(it) }

    open fun extractFromXmlOrNull(stream: InputStream) = orNull { extractFromXml(stream) }

    open fun extractFromXml(stream: InputStream) = extractFromXml(stream.reader().readText())

    open fun extractFromXmlOrNull(xml: String) = orNull { extractFromXml(xml) }

    open fun extractFromXml(xml: String): Invoice {
        val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to an Invoice object
        importer.fromXML(xml)

        return extractInvoice(importer)
    }


    open fun extractFromPdfOrNull(pdfFile: File) = orNull { extractFromPdf(pdfFile) }

    open fun extractFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractFromPdf(it) }

    open fun extractFromPdfOrNull(stream: InputStream) = orNull { extractFromPdf(stream) }

    open fun extractFromPdf(stream: InputStream): Invoice {
        return extractFromXml(extractXmlFromPdf(stream))
    }


    open fun extractXmlFromPdfOrNull(pdfFile: File) = orNull { extractXmlFromPdf(pdfFile) }

    open fun extractXmlFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractXmlFromPdf(it) }

    open fun extractXmlFromPdfOrNull(stream: InputStream) = orNull { extractXmlFromPdf(stream) }

    open fun extractXmlFromPdf(stream: InputStream): String {
        val attachments = pdfAttachmentReader.getFileAttachments(stream)

        return attachments.attachments.first { it.isXmlFile }.xml!! // we add error handling soon
    }


    protected open fun extractInvoice(importer: ZUGFeRDInvoiceImporter): Invoice {
        val invoice = importer.extractInvoice()

        // TODO: the values LineTotalAmount, ChargeTotalAmount, AllowanceTotalAmount, TaxBasisTotalAmount, TaxTotalAmount,
        //  GrandTotalAmount, TotalPrepaidAmount adn DuePayableAmount are not extracted from XML document
        // we could use TransactionCalculator to manually calculate these values - Importer also does this and asserts
        // that its calculated value matches XML doc's GrandTotalAmount value. But then we would have to make some
        // methods of TransactionCalculator public
        // Another option would be to manually extract these values from XML document.

        return mapper.mapToInvoice(invoice)
    }


    protected open fun <T> orNull(action: () -> T): T? =
        try {
            action()
        } catch (e: Throwable) {
            log.debug(e) { "Action caused an exception, but orNull() was called" }
            null
        }

}
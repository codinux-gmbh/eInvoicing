package net.codinux.invoicing.reader

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResult
import net.codinux.invoicing.pdf.PdfAttachmentReader
import net.codinux.invoicing.pdf.PdfBoxPdfAttachmentReader
import net.codinux.invoicing.pdf.PdfEInvoiceExtractionResult
import net.codinux.log.logger
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream
import kotlin.io.path.Path
import kotlin.io.path.extension

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

    open fun extractFromXml(xml: String): ReadEInvoiceXmlResult {
        return try {
            val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to an Invoice object
            try {
                importer.fromXML(xml)
            } catch (e: Throwable) {
                log.error(e) { "Invoice XML seems not to be a valid XML:\n$xml" }
                return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, null, e)
            }

            ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.Success, extractInvoice(importer), null)
        } catch (e: Throwable) {
            log.error(e) { "Could not extract invoice from XML:\n$xml" }
            return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidInvoiceData, null, e)
        }
    }


    open fun extractFromPdfOrNull(pdfFile: File) = orNull { extractFromPdf(pdfFile) }

    open fun extractFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractFromPdf(it) }

    open fun extractFromPdfOrNull(stream: InputStream) = orNull { extractFromPdf(stream) }

    open fun extractFromPdf(stream: InputStream): PdfEInvoiceExtractionResult {
        val attachmentsResult = extractXmlFromPdf(stream)
        val invoiceXml = attachmentsResult.invoiceXml
        if (invoiceXml == null) {
            return PdfEInvoiceExtractionResult(null, attachmentsResult)
        }

        return PdfEInvoiceExtractionResult(extractFromXml(invoiceXml), attachmentsResult)
    }


    open fun extractXmlFromPdfOrNull(pdfFile: File) = orNull { extractXmlFromPdf(pdfFile) }

    open fun extractXmlFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractXmlFromPdf(it) }

    open fun extractXmlFromPdfOrNull(stream: InputStream) = orNull { extractXmlFromPdf(stream) }

    open fun extractXmlFromPdf(stream: InputStream): PdfAttachmentExtractionResult {
        return pdfAttachmentReader.getFileAttachments(stream)
    }


    open fun extractFromFile(inputStream: InputStream, filename: String, directory: String? = null, mediaType: String? = null): FileEInvoiceExtractionResult = try {
        val extension = Path(filename).extension.lowercase()

        if (extension == "pdf" || mediaType == "application/pdf" || mediaType == "application/octet-stream") {
            inputStream.use {
                FileEInvoiceExtractionResult(filename, directory, extractFromPdf(inputStream), null)
            }
        } else if (extension == "xml" || mediaType == "application/xml" || mediaType == "text/xml") {
            inputStream.use {
                FileEInvoiceExtractionResult(filename, directory, null, extractFromXml(inputStream))
            }
        } else {
            FileEInvoiceExtractionResult(filename, directory, null, null)
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from ${directory?.let { "$it/" } ?: ""}$filename" }
        FileEInvoiceExtractionResult(filename, directory, null, null)
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
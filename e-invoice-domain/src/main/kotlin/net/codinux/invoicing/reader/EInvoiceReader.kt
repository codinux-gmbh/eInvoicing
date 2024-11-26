package net.codinux.invoicing.reader

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream

open class EInvoiceReader(
    protected open val mapper: MustangMapper = MustangMapper()
) {

    companion object {
        val KnownEInvoiceXmlAttachmentNames = listOf(
            "factur-x.xml", "zugferd-invoice.xml", "xrechnung.xml" // also "ZUGFeRD-invoice.xml" is found but we make compare case insensitive anyway
        )
    }


    open fun extractFromXml(xmlFile: File) = xmlFile.inputStream().use { extractFromXml(it) }

    open fun extractFromXml(stream: InputStream) = extractFromXml(stream.reader().readText())

    open fun extractFromXml(xml: String): Invoice {
        val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to a Invoice object
        importer.fromXML(xml)

        return extractInvoice(importer)
    }

    open fun extractFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractFromPdf(it) }

    open fun extractFromPdf(stream: InputStream): Invoice {
        val importer = ZUGFeRDInvoiceImporter(stream)

        return extractInvoice(importer)
    }

    open fun extractXmlFromPdf(pdfFile: File) = pdfFile.inputStream().use { extractXmlFromPdf(it) }

    open fun extractXmlFromPdf(stream: InputStream): String {
        val importer = ZUGFeRDInvoiceImporter(stream)

        return String(importer.rawXML, Charsets.UTF_8)
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

}
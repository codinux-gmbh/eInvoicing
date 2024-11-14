package net.codinux.invoicing.reader

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream

class EInvoiceReader(
    private val mapper: MustangMapper = MustangMapper()
) {

    fun readFromXml(xmlFile: File) = readFromXml(xmlFile.inputStream())

    fun readFromXml(stream: InputStream) = readFromXml(stream.reader().readText())

    fun readFromXml(xml: String): Invoice {
        val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to a Invoice object
        importer.fromXML(xml)

        return extractInvoice(importer)
    }

    fun extractFromPdf(pdfFile: File) = extractFromPdf(pdfFile.inputStream())

    fun extractFromPdf(stream: InputStream): Invoice {
        val importer = ZUGFeRDInvoiceImporter(stream)

        return extractInvoice(importer)
    }

    fun extractXmlFromPdf(pdfFile: File) = extractXmlFromPdf(pdfFile.inputStream())

    fun extractXmlFromPdf(stream: InputStream): String {
        val importer = ZUGFeRDInvoiceImporter(stream)

        return String(importer.rawXML, Charsets.UTF_8)
    }


    private fun extractInvoice(importer: ZUGFeRDInvoiceImporter): Invoice {
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
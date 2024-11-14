package net.codinux.invoicing.reader

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import org.mustangproject.ZUGFeRD.ZUGFeRDInvoiceImporter
import java.io.File
import java.io.InputStream

class EInvoiceReader(
    private val mapper: MustangMapper = MustangMapper()
) {

    fun readFromXml(file: File) = readFromXml(file.inputStream())

    fun readFromXml(stream: InputStream): Invoice? {
        val importer = ZUGFeRDInvoiceImporter() // XRechnungImporter only reads properties but not to a Invoice object
        importer.fromXML(stream.reader().readText())

        return extractInvoice(importer)
    }

    fun extractFromPdf(file: File) = extractFromPdf(file.inputStream())

    fun extractFromPdf(stream: InputStream): Invoice? {
        val importer = ZUGFeRDInvoiceImporter(stream)

        return extractInvoice(importer)
    }

    private fun extractInvoice(importer: ZUGFeRDInvoiceImporter): Invoice? {
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
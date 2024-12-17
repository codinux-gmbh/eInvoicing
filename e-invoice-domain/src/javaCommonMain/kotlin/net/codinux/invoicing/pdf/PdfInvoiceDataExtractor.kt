package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.LocalDate
import net.codinux.invoicing.model.toEInvoicingBigDecimal
import net.codinux.invoicing.platform.JavaPlatform
import net.dankito.text.extraction.info.invoice.InvoiceDataExtractor
import net.dankito.text.extraction.info.model.InvoiceData
import java.io.File

/**
 * PDFs contain only unstructured data, so it's way harder to get invoice data from PDFs then from structured XML eInvoice files.
 *
 * But for validation purposes or PDFs without attached eInvoice XML we also try to extract unstructured invoice data from PDFs.
 */
open class PdfInvoiceDataExtractor(
    protected open val textExtractor: PdfTextExtractor = JavaPlatform.pdfTextExtractor,
    protected open val invoiceDataExtractor: InvoiceDataExtractor = InvoiceDataExtractor()
) {

    open fun tryToExtractInvoiceData(file: File): PdfInvoiceDataExtractionResult {
        val textExtractionResult = extractTextFromPdf(file)
        if (textExtractionResult.error != null || textExtractionResult.text == null) {
            return PdfInvoiceDataExtractionResult(textExtractionResult.error, null)
        }

        val pdfText = textExtractionResult.text
        val result = invoiceDataExtractor.extractInvoiceData(pdfText)

        return if (result.error != null) {
            PdfInvoiceDataExtractionResult(result.error, null)
        } else if (result.potentialTotalAmount == null) {
            PdfInvoiceDataExtractionResult(IllegalStateException("Could not find total amount of invoice in PDF $file"), null)
        } else {
            PdfInvoiceDataExtractionResult(null, mapInvoiceData(result, pdfText))
        }
    }

    protected open fun extractTextFromPdf(file: File): PdfTextExtractorResult =
        textExtractor.extractTextFromPdf(file)


    protected open fun mapInvoiceData(result: InvoiceData, pdfText: String) = PdfInvoiceData(
        mapAmount(result.potentialTotalAmount)!!, mapAmount(result.potentialNetAmount),
        mapAmount(result.potentialValueAddedTax), result.potentialValueAddedTaxRate?.amount?.toEInvoicingBigDecimal(),

        result.potentialIban, result.potentialBic,

        result.allAmounts.mapNotNull { mapAmount(it) }, result.percentages.mapNotNull { mapAmount(it) },

        result.dates.map { LocalDate(it.year, it.month, it.day) },

        result.ibans.map { it.hit }, result.bics.map { it.hit },

        pdfText
    )

    protected open fun mapAmount(amount: net.dankito.text.extraction.info.model.AmountOfMoney?) =
        amount?.let { AmountOfMoney(it.amount.toEInvoicingBigDecimal(), it.currency, it.amountWithCurrency) }

}
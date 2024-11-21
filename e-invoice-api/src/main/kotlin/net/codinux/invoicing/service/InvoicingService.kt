package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import java.io.File
import java.nio.file.Path
import kotlin.io.path.extension

@Singleton
class InvoicingService {

    private val creator = EInvoiceCreator()

    private val reader = EInvoiceReader()


    fun createXRechnung(invoice: Invoice): String =
        creator.createXRechnungXml(invoice)

    fun createFacturXXml(invoice: Invoice): String =
        creator.createZugferdXml(invoice)

    fun createFacturXPdf(invoice: Invoice): File {
        val resultFile = File.createTempFile("factur-x", ".pdf").also {
            it.deleteOnExit()
        }

        creator.createZugferdPdf(invoice, resultFile)

        return resultFile
    }


    fun extractInvoiceData(invoiceFile: Path) = when (invoiceFile.extension.lowercase()) {
        "xml" -> reader.readFromXml(invoiceFile.toFile())
        "pdf" -> reader.extractFromPdf(invoiceFile.toFile())
        else -> throw IllegalArgumentException("We can only extract eInvoice data from .xml and .pdf files")
    }

}
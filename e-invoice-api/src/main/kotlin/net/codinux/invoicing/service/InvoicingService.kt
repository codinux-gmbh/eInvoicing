package net.codinux.invoicing.service

import jakarta.inject.Singleton
import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.model.Invoice
import java.io.File

@Singleton
class InvoicingService {

    private val creator = EInvoiceCreator()


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

}
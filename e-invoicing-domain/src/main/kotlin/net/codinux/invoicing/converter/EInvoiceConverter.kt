package net.codinux.invoicing.converter

import net.codinux.invoicing.creation.EInvoiceCreator
import net.codinux.invoicing.model.Invoice
import org.mustangproject.CII.CIIToUBL
import java.io.File

class EInvoiceConverter {

    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a Zugferd or Factur-X invoice, to UBL (Universal Business Language).
     */
    fun convertCiiToUbl(invoice: Invoice) = convertCiiToUbl(EInvoiceCreator().createXRechnungXml(invoice))

    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a Zugferd or Factur-X invoice, to UBL (Universal Business Language).
     */
    fun convertCiiToUbl(invoiceXml: String): String {
        // TODO: extract a common method for this
        val xmlFile = File.createTempFile("Zugferd", ".xml")
            .also { it.writeText(invoiceXml) }
        val ublFile = File(xmlFile.parentFile, xmlFile.nameWithoutExtension + "-ubl.xml")

        convertCiiToUbl(xmlFile, ublFile)

        val ubl = ublFile.readText()

        xmlFile.delete()
        ublFile.delete()

        return ubl
    }

    /**
     * Converts a CII (Cross Industry Invoice) invoice, e.g. a Zugferd or Factur-X invoice, to UBL (Universal Business Language).
     */
    fun convertCiiToUbl(xmlFile: File, outputFile: File) {
        val cii2Ubl = CIIToUBL()
        cii2Ubl.convert(xmlFile, outputFile)
    }
}
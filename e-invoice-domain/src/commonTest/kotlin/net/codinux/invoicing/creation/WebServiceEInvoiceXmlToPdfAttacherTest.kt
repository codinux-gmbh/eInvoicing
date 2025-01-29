package net.codinux.invoicing.creation

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isGreaterThanOrEqualTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.invoicing.reader.ReadEInvoicePdfResultType
import net.codinux.invoicing.test.Asserts
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceAsserter
import net.codinux.invoicing.test.TestData
import kotlin.test.Test

class WebServiceEInvoiceXmlToPdfAttacherTest {

    private val underTest = WebServiceEInvoiceXmlToPdfAttacher()

    private val invoiceReader = EInvoiceReader()


    @Test
    fun attachInvoiceToPdf() = runTest {
        val result = underTest.attachInvoiceXmlToPdf(createInvoice(), TestData.FacturXPdf, EInvoiceXmlFormat.FacturX)

        assertResponse(result)
    }

//    @Test
//    fun attachInvoiceXmlToPdf() = runTest {
//        val result = underTest.attachInvoiceXmlToPdf(TestData.FacturXXml, TestData.FacturXPdf, EInvoiceXmlFormat.FacturX)
//
//        assertResponse(result)
//    }


    private fun createInvoice() = DataGenerator.createInvoice()

    private suspend fun assertResponse(result: Result<ByteArray>) {
        val response = Asserts.assertSuccess(result)
        assertThat(response.size).isGreaterThanOrEqualTo(37_000)

        val extractionResult = invoiceReader.extractFromPdf(response)
        assertThat(extractionResult).isNotNull()
        assertThat(extractionResult!!.type).isEqualByComparingTo(ReadEInvoicePdfResultType.Success)
        InvoiceAsserter.assertInvoice(extractionResult.invoice)
    }

}
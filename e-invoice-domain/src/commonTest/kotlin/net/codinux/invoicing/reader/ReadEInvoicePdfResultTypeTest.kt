package net.codinux.invoicing.reader

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import kotlin.test.Test

class ReadEInvoicePdfResultTypeTest {

    @Test
    fun fromPdfAttachmentExtractionResultType() {
        PdfAttachmentExtractionResultType.entries.filter { it != PdfAttachmentExtractionResultType.HasXmlAttachments }
            .forEach { attachmentsResultType ->
            assertThat(ReadEInvoicePdfResultType.from(attachmentsResultType), "PdfAttachmentExtractionResultType '${attachmentsResultType.name}' should map to ReadEInvoiceXmlResultType")
                .isEqualByComparingTo(ReadEInvoicePdfResultType.valueOf(attachmentsResultType.name))
        }
    }

    @Test
    fun fromReadEInvoiceXmlResultType() {
        ReadEInvoiceXmlResultType.entries.forEach { xmlResultType ->
            assertThat(ReadEInvoicePdfResultType.from(xmlResultType), "ReadEInvoiceXmlResultType '${xmlResultType.name}' should map to ReadEInvoiceXmlResultType")
                .isEqualByComparingTo(ReadEInvoicePdfResultType.valueOf(xmlResultType.name))
        }
    }

}
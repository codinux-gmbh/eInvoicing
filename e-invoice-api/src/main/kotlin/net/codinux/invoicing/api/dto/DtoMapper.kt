package net.codinux.invoicing.api.dto

import net.codinux.invoicing.model.dto.ExtractInvoiceDataFromPdfResponseDto
import net.codinux.invoicing.model.dto.PdfExtractionResultType
import net.codinux.invoicing.pdf.PdfAttachmentExtractionResultType
import net.codinux.invoicing.pdf.PdfEInvoiceExtractionResult

class DtoMapper {

    fun mapPdfExtractionResult(result: PdfEInvoiceExtractionResult) = ExtractInvoiceDataFromPdfResponseDto(
        mapPdfExtractionResultType(result), result.invoice
    )

    private fun mapPdfExtractionResultType(result: PdfEInvoiceExtractionResult): PdfExtractionResultType =
        if (result.attachmentExtractionResult.type != PdfAttachmentExtractionResultType.HasXmlAttachments || result.readEInvoiceXmlResult == null) {
            PdfExtractionResultType.valueOf(result.attachmentExtractionResult.type.name)
        } else {
            PdfExtractionResultType.valueOf(result.readEInvoiceXmlResult!!.type.name)
        }

}
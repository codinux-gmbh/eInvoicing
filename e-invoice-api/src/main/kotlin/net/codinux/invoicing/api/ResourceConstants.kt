package net.codinux.invoicing.api

import jakarta.ws.rs.core.Response
import net.codinux.invoicing.model.Pdf
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.model.dto.SerializableException

object ResourceConstants {

    const val CurrentVersion = "v1"

    const val MediaTypePdf = "application/pdf"


    fun createPdfResponse(result: Result<Pdf>, filename: String = "invoice.pdf"): Response =
        createPdfResponse(result.value?.bytes, result.error, filename)

    fun createPdfResponse(pdfBytes: ByteArray?, error: SerializableException?, filename: String): Response =
        if (pdfBytes != null) {
            Response.ok(pdfBytes)
                .header("Content-Disposition", "attachment;filename=\"$filename.pdf\"")
                .build()
        } else {
            createErrorResponse(error)
        }

    fun <T> createErrorResponse(result: Result<T>): Response =
        createErrorResponse(result.error)

    fun createErrorResponse(error: SerializableException?): Response =
        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build()

}
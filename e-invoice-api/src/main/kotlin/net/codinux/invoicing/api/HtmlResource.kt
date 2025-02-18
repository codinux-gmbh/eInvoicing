package net.codinux.invoicing.api

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.codinux.invoicing.api.ResourceConstants.CurrentVersion
import net.codinux.invoicing.api.ResourceConstants.MediaTypePdf
import net.codinux.invoicing.service.InvoicingService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path(CurrentVersion)
class HtmlResource(
    private val service: InvoicingService
) {

    @Path("html2pdf")
    @POST
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaTypePdf)
    @Operation(summary = "Converts the provided HTML to a PDF")
    @Tag(name = "Create")
    fun createPdfFromHtml(html: String): Response {
        val pdf = service.createPdfFromHtml(html)

        return ResourceConstants.createPdfResponse(pdf, "CreatedPdf.pdf")
    }

}
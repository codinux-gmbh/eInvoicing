package net.codinux.invoicing.api

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.service.InvoicingService
import org.eclipse.microprofile.openapi.annotations.Operation

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class InvoicingResource(
    private val service: InvoicingService
) {

    @Path("xrechnung")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Operation(summary = "Create a XRechnung XML")
    fun createXRechnung(invoice: Invoice) =
        service.createXRechnung(invoice)

    @Path("facturx/xml")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML (ZUGFeRD is a synonym for Factur-X)")
    fun createFacturXXml(invoice: Invoice) =
        service.createFacturXXml(invoice)

    @Path("facturx/pdf")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML, transforms it to PDF and attaches before created XML to it")
    fun createFacturXPdf(invoice: Invoice): Response {
        val pdfFile = service.createFacturXPdf(invoice)

        return Response.ok(pdfFile)
            .header("Content-Disposition", "attachment;filename=\"Invoice.pdf\"")
            .build()
    }

}
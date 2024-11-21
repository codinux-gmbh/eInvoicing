package net.codinux.invoicing.api

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.service.InvoicingService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.jboss.resteasy.reactive.PartType
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.multipart.FileUpload

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_XML)
class InvoicingResource(
    private val service: InvoicingService
) {

    @Path("xrechnung")
    @POST
    @Operation(summary = "Create a XRechnung XML")
    fun createXRechnung(invoice: Invoice) =
        service.createXRechnung(invoice)

    @Path("facturx/xml")
    @POST
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML (ZUGFeRD is a synonym for Factur-X)")
    fun createFacturXXml(invoice: Invoice) =
        service.createFacturXXml(invoice)

    @Path("facturx/pdf")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML, transforms it to PDF and attaches before created XML to it")
    fun createFacturXPdf(invoice: Invoice): Response {
        val pdfFile = service.createFacturXPdf(invoice)

        return createPdfFileResponse(pdfFile)
    }

    @Path("attach")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Attaches the invoice data as EN 16931 XML to a PDF file, combining them to a Factur-X / ZUGFeRD hybrid PDF with XML invoice file")
    fun attachInvoiceXmlToPdf(
        @RestForm @PartType(MediaType.APPLICATION_JSON) invoice: Invoice,
        @RestForm("pdf") pdf: FileUpload
    ): Response {
        val pdfFile = service.attachInvoiceXmlToPdf(invoice, pdf.uploadedFile())

        return createPdfFileResponse(pdfFile)
    }



    @Path("extract")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Extract invoice data from a Factur-X / ZUGFeRD or XRechnung file")
    fun extractInvoiceData(invoice: FileUpload) =
        service.extractInvoiceData(invoice.uploadedFile())

    @Path("validate")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Validate a Factur-X / ZUGFeRD or XRechnung file")
    fun validateInvoiceXml(invoice: FileUpload) =
        service.validateInvoice(invoice.uploadedFile()).reportAsXml


    private fun createPdfFileResponse(pdfFile: java.nio.file.Path): Response =
        Response.ok(pdfFile)
            .header("Content-Disposition", "attachment;filename=\"Invoice.pdf\"")
            .build()

}
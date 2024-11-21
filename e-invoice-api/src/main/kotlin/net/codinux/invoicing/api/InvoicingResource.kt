package net.codinux.invoicing.api

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.service.InvoicingService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.jboss.resteasy.reactive.PartType
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.multipart.FileUpload

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_XML)
class InvoicingResource(
    private val service: InvoicingService
) {

    companion object {
        private const val MediaTypePdf = "application/pdf"
    }


    @Path("create/xrechnung")
    @POST
    @Operation(summary = "Create a XRechnung XML")
    @Tag(name = "Create")
    fun createXRechnung(invoice: Invoice) =
        service.createXRechnung(invoice)

    @Path("create/facturx/xml")
    @POST
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML (ZUGFeRD is a synonym for Factur-X)")
    @Tag(name = "Create")
    fun createFacturXXml(invoice: Invoice) =
        service.createFacturXXml(invoice)

    @Path("create/facturx/pdf")
    @POST
    @Produces(MediaTypePdf)
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML, transforms it to PDF and attaches before created XML to it")
    @Tag(name = "Create")
    fun createFacturXPdf(invoice: Invoice): Response {
        val pdfFile = service.createFacturXPdf(invoice)

        return createPdfFileResponse(pdfFile, invoice)
    }

    @Path("attach")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaTypePdf)
    @Operation(summary = "Attaches the invoice data as EN 16931 XML to a PDF file, combining them to a Factur-X / ZUGFeRD hybrid PDF with XML invoice file")
    @Tag(name = "Create - Attach")
    fun attachInvoiceXmlToPdf(
        @RestForm @PartType(MediaType.APPLICATION_JSON) invoice: Invoice,
        @RestForm("pdf") @PartType(MediaTypePdf) pdf: FileUpload
    ): Response {
        val pdfFile = service.attachInvoiceXmlToPdf(invoice, pdf.uploadedFile())

        return createPdfFileResponse(pdfFile, invoice)
    }



    @Path("extract")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Extract invoice data from a Factur-X / ZUGFeRD or XRechnung file")
    @Tag(name = "Extract")
    fun extractInvoiceData(invoice: FileUpload) =
        service.extractInvoiceData(invoice.uploadedFile())

    @Path("validate")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Validate a Factur-X / ZUGFeRD or XRechnung file")
    @Tag(name = "Validate")
    fun validateInvoiceXml(invoice: FileUpload) =
        service.validateInvoice(invoice.uploadedFile()).reportAsXml


    private fun createPdfFileResponse(pdfFile: java.nio.file.Path, invoice: Invoice): Response =
        Response.ok(pdfFile)
            .header("Content-Disposition", "attachment;filename=\"${invoice.invoicingDate.toString().replace('-', '.')} ${invoice.recipient.name} ${invoice.invoiceNumber}.pdf\"")
            .build()

}
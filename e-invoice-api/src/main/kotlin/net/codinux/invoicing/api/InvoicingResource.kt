package net.codinux.invoicing.api

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.codinux.invoicing.creation.AttachInvoiceToPdfRequest
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.service.InvoicingService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.jboss.resteasy.reactive.PartType
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.multipart.FileUpload
import kotlin.io.path.readBytes

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_XML)
class InvoicingResource(
    private val service: InvoicingService
) {

    companion object {
        private const val MediaTypePdf = "application/pdf"
    }


    @Path("create")
    @POST
    @Operation(summary = "Create an e-invoice XML in format determined by format parameter")
    @Tag(name = "Create")
    fun createEInvoiceXml(invoice: Invoice, @QueryParam("format") format: EInvoiceXmlFormat) =
        service.createInvoiceXml(invoice, format)

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
    fun createFacturXPdf(invoice: Invoice, @QueryParam("format") format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Response {
        val pdfFile = service.createFacturXPdf(invoice, format)

        return createPdfFileResponse(pdfFile, invoice)
    }

    @Path("create/facturx/pdf")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML, transforms it to PDF and attaches before created XML to it")
    @Tag(name = "Create")
    fun createFacturXPdfByteResponse(invoice: Invoice, @QueryParam("format") format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): ByteArray {
        val pdfFile = service.createFacturXPdf(invoice, format)

        return pdfFile.readBytes()
    }

    @Path("create/facturx/pdf")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Create a Factur-X / ZUGFeRD from supplied invoice XML and attaches supplied XML to it")
    @Tag(name = "Create")
    fun createFacturXPdfByteResponse(invoiceXml: String, @QueryParam("format") format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): ByteArray {
        val pdfFile = service.createFacturXPdf(invoiceXml, format)

        return pdfFile.readBytes()
    }


    @Path("attach")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaTypePdf)
    @Operation(summary = "Attaches the invoice data as EN 16931 XML to a PDF file, combining them to a Factur-X / ZUGFeRD hybrid PDF with XML invoice file")
    @Tag(name = "Create - Attach")
    fun attachInvoiceXmlToPdf(
        @RestForm @PartType(MediaType.APPLICATION_JSON) invoice: Invoice,
        @RestForm("pdf") @PartType(MediaTypePdf) pdf: FileUpload,
        @QueryParam("format") format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX
    ): Response {
        val pdfFile = service.attachInvoiceXmlToPdf(invoice, pdf.uploadedFile(), format)

        return createPdfFileResponse(pdfFile, invoice)
    }

    @Path("attach")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaTypePdf)
    @Operation(summary = "Attaches the invoice data as EN 16931 XML to a PDF file, combining them to a Factur-X / ZUGFeRD hybrid PDF with XML invoice file")
    @Tag(name = "Create - Attach")
    fun attachInvoiceToPdf(body: AttachInvoiceToPdfRequest): Response {
        val pdfFile = service.attachInvoiceXmlToPdf(body.invoice, body.pdfFile, body.format)

        return createPdfFileResponse(pdfFile, body.invoice)
    }

//    @Path("attach/xml")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaTypePdf)
//    @Operation(summary = "Attaches the invoice data as EN 16931 XML to a PDF file, combining them to a Factur-X / ZUGFeRD hybrid PDF with XML invoice file")
//    @Tag(name = "Create - Attach")
//    fun attachInvoiceXmlToPdf(body: AttachInvoiceXmlToPdfRequest): Response {
//        val pdfFile = service.attachInvoiceXmlToPdf(body.invoiceXml, body.pdfFile, body.format)
//
//        return createPdfFileResponse(pdfFile, body.invoiceXml)
//    }



    @Path("extract")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM, MediaTypePdf)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Extract invoice data from a Factur-X / ZUGFeRD or XRechnung file")
    @Tag(name = "Extract")
    fun extractInvoiceDataFromPdf(invoice: java.nio.file.Path, @QueryParam("ignoreCalculationErrors") ignoreCalculationErrors: Boolean = false) =
        service.extractInvoiceDataFromPdf(invoice, ignoreCalculationErrors)

    @Path("extract")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "extractFromXML", summary = "Extract invoice data from a Factur-X / ZUGFeRD or XRechnung XML")
    @RequestBody(
        description = "The Factur-X/ZUGFeRD or XRechnung XML",
        content = arrayOf(Content(mediaType = MediaType.APPLICATION_XML, schema = Schema(implementation = org.mustangproject.Invoice::class)))
    )
    @Tag(name = "Extract")
    fun extractInvoiceDataFromXml(invoiceXml: String, @QueryParam("ignoreCalculationErrors") ignoreCalculationErrors: Boolean = false) =
        service.extractInvoiceDataFromXml(invoiceXml, ignoreCalculationErrors)

    @Path("extractXml")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "extractXmlFromPdf", summary = "Extract invoice XML from a Factur-X / ZUGFeRD PDF")
    @RequestBody(
        description = "The Factur-X/ZUGFeRD or XRechnung XML",
        content = arrayOf(Content(mediaType = MediaType.APPLICATION_XML, schema = Schema(implementation = org.mustangproject.Invoice::class)))
    )
    @Tag(name = "Extract")
    fun extractInvoiceXmlFromPdf(pdfFile: java.nio.file.Path) =
        service.extractXmlFromPdf(pdfFile)


    @Path("validate")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Validate a Factur-X / ZUGFeRD or XRechnung file")
    @Tag(name = "Validate")
    fun validateInvoice(
        invoice: java.nio.file.Path,
        @QueryParam("disableNotices") disableNotices: Boolean = false,
        // Mustang writes the filename to validation report, so with this parameter user can control the mentioned filename // TODO: remove as soon as switching to own validation
        @QueryParam("invoiceFilename") invoiceFilename: String? = null
    ) =
        service.validateInvoice(invoice, disableNotices, invoiceFilename)


    private fun createPdfFileResponse(pdfFile: java.nio.file.Path, invoice: Invoice): Response =
        createPdfFileResponse(pdfFile, "${invoice.details.invoiceDate.toString().replace('-', '.')} ${invoice.customer.name} ${invoice.details.invoiceNumber}.pdf")

    private fun createPdfFileResponse(pdfFile: java.nio.file.Path, filename: String): Response =
        Response.ok(pdfFile)
            .header("Content-Disposition", "attachment;filename=\"$filename\"")
            .build()

}
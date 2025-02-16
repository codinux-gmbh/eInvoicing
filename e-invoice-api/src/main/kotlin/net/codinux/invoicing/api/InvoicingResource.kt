package net.codinux.invoicing.api

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.codinux.invoicing.calculator.InvoiceItemPrice
import net.codinux.invoicing.creation.AttachInvoiceToPdfRequest
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.dto.SerializableException
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

@Path("v1")
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
    fun createEInvoiceXml(invoice: Invoice, @QueryParam("format") format: EInvoiceFormat): Response =
        toResponse(service.createInvoiceXml(invoice, format))

    @Path("create/xrechnung")
    @POST
    @Operation(summary = "Create a XRechnung XML")
    @Tag(name = "Create")
    fun createXRechnung(invoice: Invoice): Response =
        toResponse(service.createXRechnung(invoice))

    @Path("create/facturx/xml")
    @POST
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML (ZUGFeRD is a synonym for Factur-X)")
    @Tag(name = "Create")
    fun createFacturXXml(invoice: Invoice): Response =
        toResponse(service.createFacturXXml(invoice))


    @Path("create/facturx/pdf")
    @POST
    @Produces(MediaTypePdf)
    @Operation(summary = "Create a Factur-X / ZUGFeRD XML, transforms it to PDF and attaches before created XML to it")
    @Tag(name = "Create")
    suspend fun createFacturXPdf(invoice: Invoice, @QueryParam("format") format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX): Response {
        val pdf = service.createFacturXPdf(invoice, format)

        return createPdfResponse(pdf, invoice)
    }

    @Path("create/facturx/pdf")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaTypePdf, MediaType.APPLICATION_OCTET_STREAM) // TODO: remove MediaType.APPLICATION_OCTET_STREAM after migrating all clients
    @Operation(summary = "Create a Factur-X / ZUGFeRD from supplied invoice XML and attaches supplied XML to it")
    @Tag(name = "Create")
    suspend fun createFacturXPdfByteResponse(
        invoiceXml: String,
        @QueryParam("format") format: EInvoiceXmlFormat = EInvoiceXmlFormat.FacturX
    ): Response {
        val pdf = service.createFacturXPdf(invoiceXml, format)

        return createPdfResponse(pdf)
    }


    @Path("create/pdf")
    @POST
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaTypePdf)
    @Operation(summary = "Create a PDF from supplied HTML")
    @Tag(name = "Create")
    fun createPdfFromHtml(html: String): Response {
        val pdf = service.createPdfFromHtml(html)

        return createPdfResponse(pdf, "CreatedPdf.pdf")
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
        val pdfFile = service.attachInvoiceXmlToPdf(invoice, pdf.uploadedFile().readBytes(), format)

        return createPdfResponse(pdfFile, invoice)
    }

    @Path("attach")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaTypePdf)
    @Operation(summary = "Attaches the invoice data as EN 16931 XML to a PDF file, combining them to a Factur-X / ZUGFeRD hybrid PDF with XML invoice file")
    @Tag(name = "Create - Attach")
    fun attachInvoiceToPdf(body: AttachInvoiceToPdfRequest): Response {
        val pdfFile = service.attachInvoiceXmlToPdf(body.invoice, body.pdfFile, body.format)

        return createPdfResponse(pdfFile, body.invoice)
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
    @Consumes(MediaTypePdf, MediaType.APPLICATION_OCTET_STREAM) // TODO: remove MediaType.APPLICATION_OCTET_STREAM after migrating all clients
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Extract invoice data from a Factur-X / ZUGFeRD or XRechnung file")
    @Tag(name = "Extract")
    suspend fun extractInvoiceDataFromPdf(pdfBytes: ByteArray) =
        service.extractInvoiceDataFromPdf(pdfBytes)

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
    fun extractInvoiceDataFromXml(invoiceXml: String) =
        service.extractInvoiceDataFromXml(invoiceXml)

    @Path("extractXml")
    @POST
    @Consumes(MediaTypePdf, MediaType.APPLICATION_OCTET_STREAM) // TODO: remove MediaType.APPLICATION_OCTET_STREAM after migrating all clients
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "extractXmlFromPdf", summary = "Extract invoice XML from a Factur-X / ZUGFeRD PDF")
    @RequestBody(
        description = "The Factur-X/ZUGFeRD or XRechnung XML",
        content = arrayOf(Content(mediaType = MediaType.APPLICATION_XML, schema = Schema(implementation = org.mustangproject.Invoice::class)))
    )
    @Tag(name = "Extract")
    suspend fun extractInvoiceXmlFromPdf(pdfBytes: ByteArray) =
        service.extractXmlFromPdf(pdfBytes)


    @Path("validate")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Validate a Factur-X / ZUGFeRD or XRechnung XML")
    @Tag(name = "Validate")
    fun validateInvoiceXml(invoiceXml: String) =
        toResponse(service.validateInvoiceXml(invoiceXml))

    @Path("validate")
    @POST
    @Consumes(MediaTypePdf, MediaType.APPLICATION_OCTET_STREAM) // TODO: remove MediaType.APPLICATION_OCTET_STREAM after migrating all clients
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Validate a Factur-X / ZUGFeRD PDF")
    @Tag(name = "Validate")
    fun validateInvoicePdf(pdfBytes: ByteArray) =
        toResponse(service.validateInvoicePdf(pdfBytes))


    @Path("calculateTotalAmounts")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Calculates invoice total amounts from invoice item prices")
    @Tag(name = "Tools - Calculate")
    fun calculateTotalAmounts(
        itemPrices: List<InvoiceItemPrice>
    ) =
        service.calculateTotalAmounts(itemPrices)


    private fun <T> toResponse(result: Result<T>): Response =
        result.value?.let { Response.ok(it).build() }
            ?: createErrorResponse(result)

    private fun <T> createErrorResponse(result: Result<T>): Response =
        createErrorResponse(result.error)

    private fun createErrorResponse(error: SerializableException?): Response =
        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build()

    private fun createPdfResponse(pdfFile: Result<Pdf>, invoice: Invoice): Response =
        createPdfResponse(pdfFile.value?.bytes, pdfFile.error, invoice.shortDescription)

    private fun createPdfResponseForPdfBytes(pdfFile: Result<ByteArray>, invoice: Invoice): Response =
        createPdfResponse(pdfFile.value, pdfFile.error, invoice.shortDescription)

    private fun createPdfResponse(result: Result<Pdf>, filename: String = "invoice.pdf"): Response =
        createPdfResponse(result.value?.bytes, result.error, filename)

    private fun createPdfResponse(pdfBytes: ByteArray?, error: SerializableException?, filename: String): Response =
        if (pdfBytes != null) {
            Response.ok(pdfBytes)
                .header("Content-Disposition", "attachment;filename=\"$filename.pdf\"")
                .build()
        } else {
            createErrorResponse(error)
        }

}
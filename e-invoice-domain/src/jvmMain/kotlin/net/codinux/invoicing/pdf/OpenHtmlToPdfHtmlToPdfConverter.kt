package net.codinux.invoicing.pdf

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import net.codinux.invoicing.extension.useAndGet
import net.codinux.invoicing.model.Pdf
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.common.PDMetadata
import org.apache.xmpbox.XMPMetadata
import org.apache.xmpbox.xml.XmpSerializer
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import org.jsoup.nodes.Document
import java.io.ByteArrayOutputStream
import java.nio.file.FileSystems
import java.util.*

open class OpenHtmlToPdfHtmlToPdfConverter : HtmlToPdfConverter {

    override fun createPdf(html: String): Pdf {
        val doc = createWellFormedHtml(html)
        return xhtmlToPdf(doc)
    }

    protected open fun xhtmlToPdf(doc: Document): Pdf {
        var pdfBytes = ByteArrayOutputStream().useAndGet { output ->
            val baseUri = FileSystems.getDefault().getPath("/src/main/resources")
                .toUri().toString()

            val builder = PdfRendererBuilder()
            builder.useFastMode()

            builder.toStream(output)
            builder.withW3cDocument(W3CDom().fromJsoup(doc), baseUri)
            builder.run()
        }

        pdfBytes = setMetadata(pdfBytes)

        return Pdf(pdfBytes)
    }

    protected open fun createWellFormedHtml(html: String): Document =
        Jsoup.parse(html, Charsets.UTF_8.name()).apply {
            this.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
        }

    protected open fun setMetadata(pdfBytes: ByteArray): ByteArray =
        Loader.loadPDF(pdfBytes).use {  pdf ->
            val info = pdf.documentInformation
            info.apply {
                title = "Invoice" // TODO: use: Invoice / Rechnung <invoice number>
                author = "codinux GmbH & Co. KG" // TODO: use: customer name
                subject = "Invoice created from eInvoice by codinux GmbH & Co. KG" // TODO: use: Invoice / Rechnung <invoice number> <supplier name> to <customer name>
                creator = "eInvoicing" // TODO: use: E-Rechnung / <Accounting App> by codinux GmbH & Co. KG, "eInvoicing" otherwise
                producer = "eInvoicing by codinux GmbH & Co. KG"
                keywords = "E-Rechnung, e-invoicing, EN16931, Factur-X, Das Leben ist schön" // TODO: replace Factur-X with XRechnung or UBL if other profile is used
                creationDate = Calendar.getInstance()
                modificationDate = Calendar.getInstance()
            }

            val metadata = XMPMetadata.createXMPMetadata()

            metadata.createAndAddAdobePDFSchema().apply {
                keywords = info.keywords
                producer = info.producer
            }

            metadata.createAndAddXMPBasicSchema().apply {
                createDate = info.creationDate
                modifyDate = info.modificationDate
                metadataDate = info.modificationDate
                creatorTool = info.creator
            }

            metadata.createAndAddDublinCoreSchema().apply {
                title = info.title
                addCreator(info.creator)
                description = info.subject
            }

            pdf.documentCatalog.metadata = PDMetadata(pdf).apply {
                ByteArrayOutputStream().use { byteStream ->
                    XmpSerializer().serialize(metadata, byteStream, true)
                    importXMPMetadata(byteStream.toByteArray())
                }
            }

            ByteArrayOutputStream().useAndGet {
                pdf.save(it)
            }
        }

}
package net.codinux.invoicing.pdf

import com.openhtmltopdf.extend.FSSupplier
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder
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
import java.io.InputStream
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

            /*      Embed fonts for PDF/A compatibility     */

            // system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Ubuntu, "Helvetica Neue", Oxygen, Cantarell, sans-serif;
            // and what about Tahoma, San Francisco, Helvetica, Trebuchet MS, Verdana?
            builder.addWithBoldFont("LiberationSans-Regular.ttf", "LiberationSans-Bold.ttf", "LiberationSans", "Liberation Sans", "liberation-sans", "system-ui", "-apple-system", "BlinkMacSystemFont", "Helvetica", "Arial", "sans-serif")

            // what about Georgia?
            builder.addWithBoldFont("LiberationSerif-Regular.ttf", "LiberationSerif-Bold.ttf", "LiberationSerif", "Liberation Serif", "liberation-serif", "Times New Roman", "Times Roman", "Times-Roman", "Times", "serif")

            builder.addWithBoldFont("LiberationMono-Regular.ttf", "LiberationSerif-Bold.ttf", "LiberationMono", "Liberation Mono", "liberation-mono", "Courier New", "Courier", "monospace")

            // fonts lookup is case sensitive, so add in title case and in lower case
            builder.addFont("Roboto-VariableFont_wdth,wght.ttf", "Roboto", "roboto")
            builder.addFont("NotoSans-VariableFont_wdth,wght.ttf", "NotoSans", "Noto Sans", "noto-sans")
            builder.addFont("OpenSans-VariableFont_wdth,wght.ttf", "OpenSans", "Open Sans", "open-sans")
            builder.addFont("SourceSans3-VariableFont_wght.ttf", "SourceSans", "Source Sans", "source-sans")
            builder.addWithBoldFont("Ubuntu-Regular.ttf", "Ubuntu-Bold.ttf", "Ubuntu", "ubuntu")

            builder.addFont("NotoSerif-VariableFont_wdth,wght.ttf", "NotoSerif", "Noto Serif", "noto-serif")
            builder.addFont("SourceSerif4-VariableFont_opsz,wght.ttf", "SourceSerif", "Source Serif", "source-serif")
            builder.addFont("EBGaramond-VariableFont_wght.ttf", "EBGaramond", "EB Garamond", "eb-garamond", "Garamond", "garamond")

            builder.addFont("NotoSansMono-VariableFont_wdth,wght.ttf", "NotoSansMono", "Noto Sans Mono", "noto-sans-mono", "noto-mono")
            builder.addFont("SourceCodePro-VariableFont_wght.ttf", "SourceCodePro", "Source Code Pro", "source-code-pro")

            // if there's e.g. a font in HTML that we didn't embed above, then PDF creation crashes. So don't call usePdfAConformance(), we ensure PDF/A-3 compatiblity ourselves
//            builder.usePdfAConformance(PdfRendererBuilder.PdfAConformance.PDFA_3_A)

            builder.toStream(output)
            builder.withW3cDocument(W3CDom().fromJsoup(doc), baseUri)
            builder.run()
        }

        pdfBytes = setMetadata(pdfBytes)

        return Pdf(pdfBytes)
    }

    protected open fun PdfRendererBuilder.addFont(regularFontName: String, vararg fontFamilies: String) =
        addWithBoldFont(regularFontName, null, *fontFamilies)

    protected open fun PdfRendererBuilder.addWithBoldFont(regularFontName: String, boldFontName: String?, vararg fontFamilies: String) = this.apply {
        fontFamilies.forEach { fontFamily ->
            useFont(getFont(regularFontName), fontFamily)

            if (boldFontName != null) {
                useFont(getFont(boldFontName), fontFamily, 700, BaseRendererBuilder.FontStyle.NORMAL, true)
            }
        }
    }

    protected open fun getFont(fontName: String): FSSupplier<InputStream> =
        FSSupplier<InputStream> { ResourceUtil.getResourceAsStream("fonts/$fontName") }

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
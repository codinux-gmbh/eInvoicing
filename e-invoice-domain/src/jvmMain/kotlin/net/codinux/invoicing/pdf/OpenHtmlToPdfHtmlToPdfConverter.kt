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
            builder.addDefaultFonts()

            // if there's e.g. a font in HTML that we didn't embed above, then PDF creation crashes. So don't call usePdfAConformance(), we ensure PDF/A-3 compatiblity ourselves
//            builder.usePdfAConformance(PdfRendererBuilder.PdfAConformance.PDFA_3_A)

            builder.toStream(output)
            builder.withW3cDocument(W3CDom().fromJsoup(doc), baseUri)
            builder.run()
        }

        pdfBytes = setMetadata(pdfBytes)

        return Pdf(pdfBytes)
    }

    protected open fun PdfRendererBuilder.addDefaultFonts() = this.apply {
        // in my eyes this is a bug in OpenHtmlToPdf: when searching for a font, "sans-serif" gets converted to "SansSerif",
        // "serif" to "Serif" and "monospace" to "Monospaced" - but the supplied font family names don't get converted this way!
        // So the supplied font with key "serif", "sans-serif", ... will never be matched, as OHtP looks for "Serif", "SansSerif", ...

        // system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Ubuntu, "Helvetica Neue", Oxygen, Cantarell, sans-serif;
        // and what about Tahoma, San Francisco, Helvetica, Trebuchet MS, Verdana?
        this.addWithBoldFont("LiberationSans-Regular.ttf", "LiberationSans-Bold.ttf", "LiberationSans", "Liberation Sans", "liberation-sans", "system-ui", "-apple-system", "BlinkMacSystemFont", "Helvetica", "Arial", "SansSerif")

        // what about Georgia?
        this.addWithBoldFont("LiberationSerif-Regular.ttf", "LiberationSerif-Bold.ttf", "LiberationSerif", "Liberation Serif", "liberation-serif", "Times New Roman", "Times Roman", "Times-Roman", "Times", "Serif")

        this.addWithBoldFont("LiberationMono-Regular.ttf", "LiberationSerif-Bold.ttf", "LiberationMono", "Liberation Mono", "liberation-mono", "Courier New", "Courier", "Monospaced")

        // fonts lookup is case sensitive, so add in title case and in lower case
        this.addFont("Roboto-VariableFont_wdth,wght.ttf", "Roboto", "roboto")
        this.addFont("NotoSans-VariableFont_wdth,wght.ttf", "NotoSans", "Noto Sans", "noto-sans")
        this.addFont("OpenSans-VariableFont_wdth,wght.ttf", "OpenSans", "Open Sans", "open-sans")
        this.addFont("SourceSans3-VariableFont_wght.ttf", "SourceSans", "Source Sans", "source-sans")
        this.addWithBoldFont("Ubuntu-Regular.ttf", "Ubuntu-Bold.ttf", "Ubuntu", "ubuntu")

        this.addFont("NotoSerif-VariableFont_wdth,wght.ttf", "NotoSerif", "Noto Serif", "noto-serif")
        this.addFont("SourceSerif4-VariableFont_opsz,wght.ttf", "SourceSerif", "Source Serif", "source-serif")
        this.addFont("EBGaramond-VariableFont_wght.ttf", "EBGaramond", "EB Garamond", "eb-garamond", "Garamond", "garamond")

        this.addFont("NotoSansMono-VariableFont_wdth,wght.ttf", "NotoSansMono", "Noto Sans Mono", "noto-sans-mono", "noto-mono")
        this.addFont("SourceCodePro-VariableFont_wght.ttf", "SourceCodePro", "Source Code Pro", "source-code-pro")
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
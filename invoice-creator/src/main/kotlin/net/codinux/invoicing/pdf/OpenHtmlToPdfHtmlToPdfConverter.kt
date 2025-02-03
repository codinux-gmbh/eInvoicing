package net.codinux.invoicing.pdf

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import net.codinux.invoicing.model.Pdf
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import org.jsoup.nodes.Document
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.FileSystems


open class OpenHtmlToPdfHtmlToPdfConverter {

    open fun renderHtml(htmlFile: File): Pdf =
        renderHtml(htmlFile.readText())

    open fun renderHtml(html: String): Pdf {
        val doc = createWellFormedHtml(html)
        return xhtmlToPdf(doc)
    }

    protected open fun xhtmlToPdf(doc: Document): Pdf {
        val pdfBytes = ByteArrayOutputStream().also { it.use { output ->
            val baseUri = FileSystems.getDefault().getPath("/src/main/resources")
                .toUri().toString()

            val builder = PdfRendererBuilder()
            builder.useFastMode()

            builder.toStream(output)
            builder.withW3cDocument(W3CDom().fromJsoup(doc), baseUri)
            builder.run()
        } }.toByteArray()

        return Pdf(pdfBytes)
    }

    protected open fun createWellFormedHtml(html: String): Document =
        Jsoup.parse(html, Charsets.UTF_8.name()).apply {
            this.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
        }

}
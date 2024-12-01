package net.codinux.invoicing.pdf

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import org.jsoup.nodes.Document
import java.io.File
import java.nio.file.FileSystems


open class OpenHtmlToPdfHtmlToPdfConverter {

    open fun renderHtml(htmlFile: File, outputFile: File) =
        renderHtml(htmlFile.readText(), outputFile)

    open fun renderHtml(html: String, outputFile: File) {
        val doc = createWellFormedHtml(html)
        xhtmlToPdf(doc, outputFile)
    }

    protected open fun xhtmlToPdf(doc: Document, outputPdf: File) {
        outputPdf.outputStream().use { output ->
            val baseUri = FileSystems.getDefault().getPath("/src/main/resources")
                .toUri().toString()

            val builder = PdfRendererBuilder()
            builder.useFastMode()

            builder.toStream(output)
            builder.withW3cDocument(W3CDom().fromJsoup(doc), baseUri)
            builder.run()
        }
    }

    protected open fun createWellFormedHtml(html: String): Document =
        Jsoup.parse(html, Charsets.UTF_8.name()).apply {
            this.outputSettings().syntax(Document.OutputSettings.Syntax.xml)
        }

}
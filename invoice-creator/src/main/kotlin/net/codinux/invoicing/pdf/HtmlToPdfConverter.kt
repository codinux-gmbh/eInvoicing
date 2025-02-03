package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.Pdf
import java.io.File
import java.nio.file.Path
import kotlin.io.path.readText

interface HtmlToPdfConverter {

    fun createPdf(htmlFile: Path): Pdf =
        createPdf(htmlFile.readText())

    fun createPdf(htmlFile: File): Pdf =
        createPdf(htmlFile.readText())

    fun createPdf(html: String): Pdf

}
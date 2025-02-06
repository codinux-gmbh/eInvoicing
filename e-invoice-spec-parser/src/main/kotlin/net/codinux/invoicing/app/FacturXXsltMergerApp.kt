package net.codinux.invoicing.app

import net.codinux.invoicing.xslt.FacturXXsltMerger
import java.io.File
import java.nio.file.Files
import kotlin.io.path.name

fun main() {
    val facturXDistributionZip = File(FacturXXsltMerger::class.java.classLoader.getResource("facturx/Factur-X-1.07.2-Zugferd-2.3.2-2024-11-15-FINAL-EN.zip").toURI()).toPath()

    var projectFolder = facturXDistributionZip.parent
    while (projectFolder.name != "e-invoice-spec-parser") {
        projectFolder = projectFolder.parent
    }

    val outputFolder = projectFolder.parent.resolve("e-invoice-domain").resolve("src/jvmMain/resources/facturx/schematron")
        .also { Files.createDirectories(it) }

    FacturXXsltMerger().mergeFacturXXsltFiles(facturXDistributionZip, outputFolder)
}
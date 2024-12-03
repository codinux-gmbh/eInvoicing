package net.codinux.invoicing.app

import net.codinux.invoicing.parser.CodeGenerator
import net.codinux.invoicing.parser.genericode.CefGenericodeCodelistsParser
import java.io.File

fun main() {
    CefGenericodeCodelistsParserApp().parseCefGenericodeLists()
}

class CefGenericodeCodelistsParserApp {

    fun parseCefGenericodeLists() {
        val zipFile = File(javaClass.classLoader.getResource("codeLists/cef-genericodes-2024-11-15.zip")!!.toURI())

        val codeLists = CefGenericodeCodelistsParser().parse(zipFile)

        var outputDirectoryBasePath = zipFile.parentFile.parentFile.absolutePath.replace("e-invoice-spec-parser", "e-invoice-domain")
        if (outputDirectoryBasePath.contains("/build/resources/main")) {
            outputDirectoryBasePath = outputDirectoryBasePath.replace("/build/resources/main", "/src/main")
        }
        val outputDirectory = File(outputDirectoryBasePath, "kotlin/net/codinux/invoicing/model/codes")

        CodeGenerator().generateCodeFiles(codeLists, outputDirectory)
    }

}
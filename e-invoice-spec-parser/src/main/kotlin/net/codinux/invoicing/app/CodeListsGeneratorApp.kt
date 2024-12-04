package net.codinux.invoicing.app

import net.codinux.invoicing.parser.CodeGenerator
import net.codinux.invoicing.parser.excel.ZugferdExcelCodeListsParser
import net.codinux.invoicing.parser.genericode.CefGenericodeCodelistsParser
import java.io.File

fun main() {
    CodeListsGeneratorApp().generateCodeLists()
}

class CodeListsGeneratorApp {

    fun generateCodeLists() {
        val zipFile = File(javaClass.classLoader.getResource("codeLists/cef-genericodes-2024-11-15.zip")!!.toURI())
        val cefCodeLists = CefGenericodeCodelistsParser().parse(zipFile)

        val xslxFile = File(javaClass.classLoader.getResource("codeLists/3. FACTUR-X 1.0.07 FR-EN.xlsx")!!.toURI())
        val zugferdCodeLists = ZugferdExcelCodeListsParser().parse(xslxFile)

        var outputDirectoryBasePath = zipFile.parentFile.parentFile.absolutePath.replace("e-invoice-spec-parser", "e-invoice-domain")
        if (outputDirectoryBasePath.contains("/build/resources/main")) {
            outputDirectoryBasePath = outputDirectoryBasePath.replace("/build/resources/main", "/src/main")
        }
        val outputDirectory = File(outputDirectoryBasePath, "kotlin/net/codinux/invoicing/model/codes").also { it.mkdirs() }

        CodeGenerator().generateCodeFiles(cefCodeLists, zugferdCodeLists, outputDirectory)
    }

}
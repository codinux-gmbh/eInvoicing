package net.codinux.invoicing.app

import net.codinux.invoicing.parser.excel.ZugferdExcelCodeListsParser
import java.io.File

fun main() {
    ZugferdExcelCodeListsParserApp().parse()
}

class ZugferdExcelCodeListsParserApp {

    fun parse() {
        val xslxFile = File(javaClass.classLoader.getResource("codeLists/3. FACTUR-X 1.0.07 FR-EN.xlsx")!!.toURI())

        val result = ZugferdExcelCodeListsParser().parse(xslxFile)
        if (result.isNotEmpty()) { }
    }
}
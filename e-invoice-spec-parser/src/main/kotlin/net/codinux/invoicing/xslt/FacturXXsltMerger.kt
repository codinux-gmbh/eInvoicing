package net.codinux.invoicing.xslt

import net.codinux.log.logger
import org.jsoup.nodes.Comment
import org.jsoup.nodes.Node
import org.jsoup.parser.Parser
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlin.io.path.writeText

class FacturXXsltMerger {

    companion object {
        private val DocumentCallsToCodedbFileRegex = Regex("""document\(('|&apos;)Factur-X[_\d.]*_(MINIMUM|BASICWL|BASIC-WL|BASIC|EN16931|EXTENDED)_codedb.xml('|&apos;)\)""", RegexOption.IGNORE_CASE)

        private val MultipleEmptyLinesRegex = Regex("""(\r?\n){2,}(\r?\n)""")
    }


    private val log by logger()


    fun mergeFacturXXsltFiles(facturXDistributionZip: Path, outputFolder: Path) {
        val zipFile = ZipFile(facturXDistributionZip.toFile())

        val entries = zipFile.entries().toList()
        val xslts = entries.filter { it.name.lowercase().endsWith(".xslt") && it.size > 250 } // size > 250: for each .xslt there's an additional 177 bytes file in __MACOS/ folder that starts with "._"
            .associateBy { it.name.substringBeforeLast("/") } // associate by path
        val codedbs = entries.filter { it.name.lowercase().endsWith("_codedb.xml") && it.name.contains("/_XSLT_") && it.size > 250 } // it.name.contains("/_XSLT_"): to filter out codedb.xml files for Schematron files
            .associateBy { it.name.substringBeforeLast("/") }

        xslts.map { it.value to codedbs[it.key] }.forEach { (xsltEntry, codedbEntry) ->
            if (codedbEntry != null) {
                try {
                    val mergedXslt = mergeCodedbIntoXslt(zipFile, xsltEntry, codedbEntry)

                    writeMergedXsltFileToDisk(mergedXslt, xsltEntry, outputFolder)
                } catch (e: Throwable) {
                    log.error(e) { "Could not merge codedb into XSLT file for ${xsltEntry.name}" }
                }
            } else {
                log.error { "Could not find codedb.xml for XSLT file ${xsltEntry.name}" }
            }
        }
    }

    private fun mergeCodedbIntoXslt(zipFile: ZipFile, xsltEntry: ZipEntry, codedbEntry: ZipEntry): String {
        val xslt = beautifyXslt(readZipEntry(zipFile, xsltEntry))

        var codedb = readZipEntry(zipFile, codedbEntry)
        val startIndex = codedb.indexOf("<codedb>")
        codedb = codedb.substring(startIndex + "<codedb>".length)
        val endIndex = codedb.indexOf("</codedb>")
        codedb = codedb.substring(0, endIndex)

        return mergeCodedbIntoXslt(xslt, codedb)
    }

    private fun beautifyXslt(xslt: String): String {
        val doc = Parser.xmlParser().parseInput(xslt, "")

        // remove useless Schematron id attributes
        doc.select("xsl|attribute[name=id]").forEach { attributeElement ->
            if (attributeElement.text().startsWith("FX-SCH-A-")) {
                attributeElement.remove()
            }
        }

        // replace XML entities in <xsl:when test="" attributes
        doc.select("xsl|when[test]").forEach { element ->
            val testAttr = element.attr("test")
            val decodedTestAttr = decodeXmlEntities(testAttr)
            element.attr("test", decodedTestAttr)
        }

        // remove the "schematron-get-full-path" methods, see below
        doc.select("xsl|template[mode]").forEach { template ->
            if (template.attr("mode").startsWith("schematron-get-full-path")) {
                template.remove()
            }
        }

        // remove comments (they are almost all useless)
        findAllComments(doc).forEach { it.remove() }

        var beautified = doc.outerHtml().replace("&#xa0;", " ")

        // collapse multiple empty lines
        beautified = beautified.replace(MultipleEmptyLinesRegex, "\r\n\r\n")

        // replace original "schematron-select-full-path" method. It's dubbed with "This mode can be used to generate an ugly though full XPath for locators"
        // and that's exactly what is does - but we don't want ugly paths, we want the good readable ones
        val startIndex = beautified.indexOf("""<xsl:template match="*" mode="schematron-select-full-path">""") + """<xsl:template match="*" mode="schematron-select-full-path">""".length
        val endIndex = beautified.indexOf("</xsl:template>", startIndex + 1)
        beautified = beautified.replaceRange(startIndex, endIndex, """
    <xsl:text>/</xsl:text>
    <xsl:for-each select="ancestor-or-self::*">
        <xsl:text>:</xsl:text>
        <xsl:value-of select="name()" />
        <xsl:text>/</xsl:text>
    </xsl:for-each>
""")

        return beautified
    }

    private fun decodeXmlEntities(input: String): String {
        return input.replace("&gt;", ">")
            .replace("&lt;", "<")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&apos;", "'")
    }

    private fun findAllComments(node: Node): List<Comment> =
        node.childNodes().filterIsInstance<Comment>() +
                node.childNodes().flatMap { findAllComments(it) }

    private fun mergeCodedbIntoXslt(xslt: String, codedb: String): String {
        // creating a Document for xslt via DocumentBuilderFactory did not work, it messed up markup, so i do it manually now (like in medieval times)

        // there's a bug in Factur-X XSLT files: the codedb.xml file is e.g. called "Factur-X_1.07.2_EN16931_codedb.xml",
        // but the .xslt files references it as "Factur-X_EN16931_codedb.xml" -> fix this
        // and there are other bugs: file is called "Factur-X_...", reference is called "FACTUR-X_...", also "BASICWL" vs. "BASIC-WL"
        // -> find the document(codedb.xml) calls via Regex, not by actual filename
        val mergedXslt = xslt.replace(DocumentCallsToCodedbFileRegex, Regex.escapeReplacement("\$codedb"))

        val index = mergedXslt.indexOf("</xsl:stylesheet>")

        return mergedXslt.substring(0, index) + """
  <!-- to not have to call external codedb.xml file, which is quite hard to also extract from .jar, i embedded external codedb.xml here (and wouldn't work anyway as the Factur-X team calls to a not existing external codedb.xml file): -->
  <xsl:variable name="codedb">
    $codedb
  </xsl:variable>
</xsl:stylesheet>"""
    }

    private fun writeMergedXsltFileToDisk(mergedXslt: String, xsltEntry: ZipEntry, outputFolder: Path) {
        val profile = xsltEntry.name.substringAfterLast("_").substringBeforeLast(".")
            .lowercase().replaceFirstChar { it.uppercase() }  // to title case
            .replace("Basicwl", "BasicWL").replace("En16931", "EN16931")

        outputFolder.resolve(profile + ".xslt").writeText(mergedXslt)
    }

    private fun readZipEntry(zipFile: ZipFile, zipEntry: ZipEntry): String =
        zipFile.getInputStream(zipEntry).use { it.reader().readText() }

}
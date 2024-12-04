package net.codinux.invoicing.parser

import net.codinux.invoicing.parser.genericode.CodeList
import net.codinux.invoicing.parser.model.CodeListType
import net.codinux.invoicing.parser.model.Column
import java.io.File

class CodeGenerator {

    fun generateCodeFiles(cefCodeLists: List<CodeList>, zugferdCodeLists: List<net.codinux.invoicing.parser.excel.CodeList>, outputDirectory: File) {
        val zugferdCodeListsByType = zugferdCodeLists.associateBy { it.type }
        val matchedCodeLists = cefCodeLists.associateBy { it.type }.mapValues { it.value to zugferdCodeListsByType[it.key] }

        matchedCodeLists.forEach { (type, codeLists) ->
            // ignore Currency and Country for now
            if (type == CodeListType.IsoCurrencyCodes || type == CodeListType.IsoCountryCodes) {
                return@forEach
            }

            // Factur-X has the better column names and often also a Description column
            val (columns, rows) = filter(if (codeLists.second !=  null) codeLists.second!!.columns to codeLists.second!!.rows
                                else codeLists.first.columns to codeLists.first.rows)

            File(outputDirectory, type.className + ".kt").bufferedWriter().use { writer ->
                writer.appendLine("package net.codinux.invoicing.model.codes")
                writer.newLine()
                writer.appendLine("enum class ${type.className}(${columns.joinToString(", ") { "val ${getPropertyName(it)}: ${getDataType(it, columns, rows)}" } }) {")

                rows.forEach { row ->
                    writer.appendLine("\t${getEnumName(columns, row)}(${row.joinToString(", ") { it?.let { "\"${it.toString().replace("\n", "").replace('"', '\'')}\"" } ?: "null" } }),")
                }
                writer.append("}")
            }
        }
    }


    // SchemeIdentifier: ignore Comment
    // dito SchemeIdentifierFacturX
    // ElectronicAddressScheme: ignore Source
    // Unit: ignore Source
    // PaymentMeansCodeFacturX: ignore Sens
    private fun filter(columnsAndRows: Pair<List<Column>, List<List<Any?>>>): Pair<List<Column>, List<List<Any?>>> {
        val (columns, rows) = columnsAndRows
        val columnToIgnore = columns.firstOrNull { it.name == "Source" || it.name == "Comment" || it.name == "Sens" }

        if (columnToIgnore == null) {
            return columnsAndRows
        }

        val indexToIgnore = columns.indexOf(columnToIgnore)
        return columns.filterIndexed { index, _ -> index != indexToIgnore } to rows.map { it.filterIndexed { index, _ -> index != indexToIgnore } }
    }


    private fun getPropertyName(column: Column): String = when (column.name) {
        "Unique code" -> "code"
        "Meaning of the code" -> "meaning"
        "Optional remark for the usage of this code" -> "optionalRemarkForTheUsageOfTheCode"
        "Alpha-2 code" -> "alpha2Code"
        "Alpha-3 code" -> "alpha3Code"
        "Code name (english)" -> "codeName"
        "Context of exemption (for definition refer to legislation)" -> "contextOfExemption"
        "ICD value" -> "code"
        "Structure of code" -> "structureOfCode"
        "Name" -> "meaning" // cannot use 'name' as property name in an Enum
        else -> column.name.replace(" ", "").let { it[0].lowercase() + it.substring(1) }
    }

    private fun getDataType(column: Column, columns: List<Column>, rows: List<List<Any?>>): String {
        val index = columns.indexOf(column)
        val containsNullValues = rows.any { it[index] == null }

        return when (column.dataType) {
            "string" -> "String" + (if (containsNullValues) "?" else "")
            else -> column.dataType[0].uppercase() + column.dataType.substring(1).replace(" ", "") + (if (containsNullValues) "?" else "")
        }
    }

    private fun getEnumName(columns: List<Column>, row: List<Any?>): String {
        val column = if (columns.first().name == "Scheme ID") row[1] else if (columns.first().name == "Country") row[2] else if (columns.first().name == "English Name") row[3] else row[0]

        val name = (column?.toString() ?: "").replace(' ', '_').replace('/', '_').replace('.', '_').replace(',', '_')
            .replace('-', '_').replace('(', '_').replace(')', '_').replace('[', '_').replace(']', '_')
            .replace('\'', '_').replace('’', '_').replace(Typography.nbsp, '_')

        return if (name.isEmpty()) "_"
        else if (name[0].isDigit()) "_" + name
        else name
    }

}
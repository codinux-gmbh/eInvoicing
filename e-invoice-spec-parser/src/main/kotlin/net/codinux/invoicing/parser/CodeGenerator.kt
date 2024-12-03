package net.codinux.invoicing.parser

import net.codinux.invoicing.parser.genericode.CodeList
import net.codinux.invoicing.parser.genericode.Column
import java.io.File

class CodeGenerator {

    fun generateCodeFiles(codeLists: List<CodeList>, outputDirectory: File) {
        codeLists.forEach { codeList ->
            File(outputDirectory, codeList.name + ".kt").bufferedWriter().use { writer ->
                writer.appendLine("package net.codinux.invoicing.model.codes")
                writer.newLine()
                writer.appendLine("enum class ${getClassName(codeList)}(${codeList.columns.joinToString(", ") { "val ${getPropertyName(it)}: ${getDataType(codeList, it)}" } }) {")

                codeList.rows.forEach { row ->
                    writer.appendLine("\t${getEnumName(codeList.columns, row)}(${row.joinToString(", ") { it?.let { "\"${it.replace("\n", "")}\"" } ?: "null" } }),")
                }
                writer.appendLine("}")
            }
        }
    }


    private fun getClassName(codeList: CodeList): String {
        val name = codeList.name
        return if (name[0].isDigit()) "_" + name
        else name
    }

    private fun getPropertyName(column: Column): String = when (column.name) {
        "Unique code" -> "uniqueCode"
        "Meaning of the code" -> "meaningOfTheCode"
        "Optional remark for the usage of this code" -> "optionalRemarkForTheUsageOfTheCode"
        else -> column.name.replace(" ", "")
    }

    private fun getDataType(codeList: CodeList, column: Column): String {
        val index = codeList.columns.indexOf(column)
        val containsNullValues = codeList.rows.any { it[index] == null }

        return when (column.dataType) {
            "string" -> "String" + (if (containsNullValues) "?" else "")
            else -> column.dataType[0].uppercase() + column.dataType.substring(1).replace(" ", "")
        }
    }

    private fun getEnumName(columns: List<Column>, row: List<String?>): String {
        val name = (row[0] ?: "").replace(' ', '_').replace('/', '_').replace('.', '_').replace('-', '_')
        return if (name[0].isDigit()) "_" + name
        else name
    }

}
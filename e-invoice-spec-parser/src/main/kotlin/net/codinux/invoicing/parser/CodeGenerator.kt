package net.codinux.invoicing.parser

import net.codinux.i18n.Region
import net.codinux.i18n.UnitAll
import net.codinux.invoicing.model.codes.InvoiceTypeUseFor
import net.codinux.invoicing.parser.genericode.CodeList
import net.codinux.invoicing.parser.model.CodeListType
import net.codinux.invoicing.parser.model.Column
import net.codinux.invoicing.parser.model.Row
import java.io.File
import java.util.Currency

class CodeGenerator {

    companion object {
        private val i18nRegionsByCode = Region.entries.associateBy { it.code }

        private val i18nCurrenciesByCode = net.codinux.i18n.Currency.entries.associateBy { it.alpha3Code }

        private val i18nUnitsByCode = UnitAll.entries.associateBy { it.code }
    }


    fun generateCodeFiles(cefCodeLists: List<CodeList>, zugferdCodeLists: List<net.codinux.invoicing.parser.excel.CodeList>, outputDirectory: File) {
        val zugferdCodeListsByType = zugferdCodeLists.associateBy { it.type }
        val matchedCodeLists = cefCodeLists.associateBy { it.type }.mapValues { it.value to zugferdCodeListsByType[it.key] }

        matchedCodeLists.forEach { (type, codeLists) ->
            val (columns, rows) = (if (type == CodeListType.IsoCountryCodes) mergeCountryData(codeLists.first, codeLists.second!!)
                    else if (type == CodeListType.IsoCurrencyCodes) mergeCurrencyData(codeLists.first, codeLists.second!!)
                    else if (type == CodeListType.Units) mergeUnitData(codeLists.first, codeLists.second!!)
                    else {
                        addFrequentlyUsedColumn(reorder(map(filter(
                            // Factur-X (= codeLists.second) has the better column names and often also a Description column
                            if (codeLists.second != null) codeLists.second!!.columns to codeLists.second!!.rows
                            else codeLists.first.columns to codeLists.first.rows
                        ))))
                    })


            File(outputDirectory, type.className + ".kt").bufferedWriter().use { writer ->
                writer.appendLine("package net.codinux.invoicing.model.codes")
                writer.newLine()
                writer.appendLine("enum class ${type.className}(${columns.joinToString(", ") { "val ${getPropertyName(it)}: ${getDataType(it, columns, rows)}" } }) {")

                rows.forEach { row ->
                    writer.appendLine("\t${getEnumName(type, columns, row)}(${row.values.joinToString(", ") { getPropertyValue(it, type !in listOf(CodeListType.IsoCountryCodes, CodeListType.IsoCurrencyCodes)) } }),")
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
    private fun filter(columnsAndRows: Pair<List<Column>, List<Row>>): Pair<List<Column>, List<Row>> {
        val (columns, rows) = columnsAndRows
        val columnToIgnore = columns.firstOrNull { it.name == "Source" || it.name == "Comment" || it.name == "Sens" || it.name == "French Name" }

        if (columnToIgnore == null) {
            return columnsAndRows
        }

        val indexToIgnore = columns.indexOf(columnToIgnore)
        return columns.filterIndexed { index, _ -> index != indexToIgnore } to rows.onEach { it.removeValueAtIndex(indexToIgnore) }
    }

    private fun map(columnsAndRows: Pair<List<Column>, List<Row>>): Pair<List<Column>, List<Row>> {
        val (columns, rows) = columnsAndRows
        val useForColumn = columns.firstOrNull { it.name == "EN16931 interpretation" }

        if (useForColumn != null) {
            val index = columns.indexOf(useForColumn)
            val modifiedColumns = columns.toMutableList().apply {
                removeAt(index)
                add(Column(columns.last().index + 1, "UseFor", "InvoiceTypeUseFor"))
            }
            val modifiedRows = rows.onEach {
                val useFor = it.removeValueAtIndex(index)?.toString()
                it.addValue(if (useFor == "Credit Note") InvoiceTypeUseFor.CreditNote else InvoiceTypeUseFor.Invoice)
            }

            return modifiedColumns to modifiedRows
        }

        return columnsAndRows
    }

    /**
     * For SchemeIdentifier move the schemeId column, which in most cases is null, to the end, so that the code is the first column.
     */
    private fun reorder(columnsAndRows: Pair<List<Column>, List<Row>>): Pair<List<Column>, List<Row>> {
        val (columns, rows) = columnsAndRows
        val reorderFirstColumn = columns.first().name in listOf("Scheme ID")

        if (reorderFirstColumn) {
            val reorderedColumns = columns.toMutableList().apply {
                val reorderedColumn = this.removeAt(0)
                this.add(reorderedColumn)
            }

            val reorderedRows = rows.onEach {
                val reorderedRow = it.removeValueAtIndex(0)
                it.addValue(reorderedRow)
            }

            return reorderedColumns to reorderedRows
        }

        return columnsAndRows
    }

    private fun mergeCountryData(cefCodeList: CodeList, zugferdCodeList: net.codinux.invoicing.parser.excel.CodeList): Pair<List<Column>, List<Row>> {
        val columns = listOf(
            Column(0, "alpha2Code", "String"),
            Column(1, "alpha3Code", "String"),
            Column(2, "numericCode", "Int"),
            Column(3, "numericCodeAsString", "String"),
            Column(4, "englishName", "String"),
        )

        val cefByIsoCode = cefCodeList.rows.associateBy { it.values[0] }
        val zugferdByIsoCode = zugferdCodeList.rows.groupBy { it.values[2] }

        val rows = cefByIsoCode.map { (isoCode, cefRow) ->
            val values = zugferdByIsoCode[isoCode]!!.first().values
            val i18nRegion = i18nRegionsByCode[isoCode]
            Row(listOf(isoCode, i18nRegion?.alpha3Code ?: values[2], i18nRegion?.numericCode, i18nRegion?.numericCodeAsString, i18nRegion?.englishName ?: values[0]), false, fixCountryName(i18nRegion?.name ?: values[0]))
        }

        return columns to rows.sortedBy { it.enumName!! } // sort by englishName
    }

    private fun mergeCurrencyData(cefCodeList: CodeList, zugferdCodeList: net.codinux.invoicing.parser.excel.CodeList): Pair<List<Column>, List<Row>> {
        val columns = listOf(
            Column(0, "alpha3Code", "String"),
            Column(1, "numericCode", "Int"),
            Column(2, "currencySymbol", "String"),
            Column(3, "englishName", "String"),
            Column(4, "countries", "Set<String>"),
            Column(Int.MAX_VALUE, "isFrequentlyUsedValue", "Boolean")
        )

        val cefByIsoCode = cefCodeList.rows.associateBy { it.values[0] }
        val zugferdByIsoCode = zugferdCodeList.rows.groupBy { it.values[2] }
        val availableCurrencies = Currency.getAvailableCurrencies().associateBy { it.currencyCode } // TODO: there are 52 currencies in availableCurrencies that are not in CEF and Zugferd list

        val rows = cefByIsoCode.map { (isoCode, cefRow) ->
            val zugferdRows = zugferdByIsoCode[isoCode] ?: emptyList()
            val i18nCurrency = i18nCurrenciesByCode[isoCode]
            // as Zugferd list only lists current currencies, there's currently no use for i18n.Currency.isCurrentCurrency and .withdrawalDate
            val isFrequentlyUsedValue = zugferdRows.any { it.isFrequentlyUsedValue }
            Row(listOf(isoCode, i18nCurrency?.numericCode, availableCurrencies[isoCode]?.symbol, cefRow.values[1], zugferdRows.map { it.values[0] }.toSet(), isFrequentlyUsedValue), isFrequentlyUsedValue, fixCurrencyName(i18nCurrency?.name ?: cefRow.values[1]))
        }

        return columns to rows.sortedBy { it.enumName!! } // sort by English name
    }

    private fun mergeUnitData(cefCodeList: CodeList, zugferdCodeList: net.codinux.invoicing.parser.excel.CodeList): Pair<List<Column>, List<Row>> {
        val columns = listOf(
            Column(0, "code", "String"),
            Column(1, "englishName", "String"),
            Column(2, "symbol", "String"),
            Column(3, "isFrequentlyUsedValue", "Boolean"),
        )

        val cefByIsoCode = cefCodeList.rows.associateBy { it.values[0] }
        val zugferdByIsoCode = zugferdCodeList.rows.groupBy { it.values[0] }

        val rows = cefByIsoCode.map { (code, cefRow) ->
            val row = zugferdByIsoCode[code]!!.first()
            val values = row.values
            val i18nUnit = i18nUnitsByCode[code]

            Row(listOf(code, values[1], i18nUnit?.symbol, row.isFrequentlyUsedValue), row.isFrequentlyUsedValue)
        }

        return columns to rows
    }

    private fun addFrequentlyUsedColumn(columnsToRows: Pair<List<Column>, List<Row>>): Pair<List<Column>, List<Row>> {
        val hasFrequentlyUsedValue = columnsToRows.second.any { it.isFrequentlyUsedValue }

        return if (hasFrequentlyUsedValue) {
            (columnsToRows.first + Column(Int.MAX_VALUE, "isFrequentlyUsedValue", "Boolean")) to
                    columnsToRows.second.onEach { it.addIsFrequentlyUsedValueCell() }
        } else {
            columnsToRows
        }
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

    private fun getPropertyValue(value: Any?, writeNumbersAsString: Boolean = true): CharSequence {
        if (value == null) {
            return "null"
        }

        if (value is Boolean) {
            return "$value"
        }

        if (value is Number && writeNumbersAsString == false) {
            return "$value"
        }

        if (value is InvoiceTypeUseFor) {
            return "InvoiceTypeUseFor.$value"
        }

        if (value is Set<*>) {
            return if (value.isEmpty()) "emptySet()" else "setOf(${value.joinToString(", ") { getPropertyValue(it) } })"
        }

        return "\"${value.toString().replace("\n", "").replace('"', '\'')}\""
    }

    private fun getDataType(column: Column, columns: List<Column>, rows: List<Row>): String {
        val index = columns.indexOf(column)
        val containsNullValues = rows.any { it.values[index] == null }

        return when (column.dataType) {
            "string" -> "String" + (if (containsNullValues) "?" else "")
            else -> column.dataType[0].uppercase() + column.dataType.substring(1).replace(" ", "") + (if (containsNullValues) "?" else "")
        }
    }

    private fun getEnumName(type: CodeListType, columns: List<Column>, row: Row): String {
        val values = row.values
        val firstColumn = values[0]

        // Mime types
        if (firstColumn == "application/pdf") return "PDF"
        else if (firstColumn == "image/png") return "PNG"
        else if (firstColumn == "image/jpeg") return "JPEG"
        else if (firstColumn == "text/csv") return "CSV"
        else if (firstColumn == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") return "ExcelSpreadsheet"
        else if (firstColumn == "application/vnd.oasis.opendocument.spreadsheet") return "OpenDocumentSpreadsheet"

        val column = if (row.enumName != null) row.enumName
                    else if (columns.first().name == "Scheme ID") values[1] // ISO 6523 Scheme Identifier codes
                    else firstColumn // default case: the code is in the first column

        val name = (column?.toString() ?: "").replace(' ', '_').replace('/', '_').replace('.', '_').replace(',', '_')
            .replace('-', '_').replace('(', '_').replace(')', '_').replace('[', '_').replace(']', '_')
            .replace('\'', '_').replace('’', '_').replace(Typography.nbsp, '_')

        return if (name.isEmpty()) "_"
        else if (name[0].isDigit()) "_" + name
        else name
    }

    private fun fixCountryName(countryName: Any?): String = when (countryName) {
        "United Kingdom (Northern Ireland)" -> "NorthernIreland"
        else -> countryName.toString()
    }

    private fun fixCurrencyName(currencyName: Any?): String = when (currencyName) {
        "Bolívar Soberano, new valuation" -> "BolivarSoberano_NewValuation"
        else -> currencyName.toString().replace(" ", "")
    }

}
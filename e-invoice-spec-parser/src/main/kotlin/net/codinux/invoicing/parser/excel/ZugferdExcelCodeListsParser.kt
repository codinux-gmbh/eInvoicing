package net.codinux.invoicing.parser.excel

import net.codinux.invoicing.parser.model.CodeListType
import net.codinux.invoicing.parser.model.Column
import net.codinux.log.logger
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File

class ZugferdExcelCodeListsParser {

    companion object {
        private val TableStartColumns = listOf("Code", "English Name", "Country", "Scheme ID")

        private val CodeListsWithDescription = listOf(CodeListType.UN_5189_AllowanceIdentificationCode, CodeListType.UN_7161_SpecialServiceDescriptionCodes,
            CodeListType.UN_4451_TextSubjectCodeQualifier, CodeListType.UN_7143_ItemTypeIdentificationCode, CodeListType.UN_4461_PaymentCodes, CodeListType.UN_1153_ReferenceCode)
    }


    private val log by logger()


    fun parse(zugferdXslxCodeListsFile: File): List<CodeList> {
        val workbook = XSSFWorkbook(zugferdXslxCodeListsFile)
        val sheets = workbook.sheetIterator().toList()

        val codeListsSheet = sheets.firstOrNull { it.sheetName == "Codelists" }
        if (codeListsSheet == null) {
            log.warn { "Could not find 'Codelists' sheet in ZUGFeRD .xslx file $zugferdXslxCodeListsFile. Available sheets: ${sheets.joinToString { it.sheetName }}" }
            return emptyList()
        }

        val rows = codeListsSheet.rowIterator().toList()
        val links = rows.get(0).toList()
        val secondRow = rows.get(1).toList()
        val codeListNames = rows.get(2).toList()
        val forthRow = rows.get(3).toList()
        val headerNames = rows.get(4).toList()

        val tableStartCells = headerNames.filter { it.stringCellValue in TableStartColumns }
        return tableStartCells.mapNotNull { mapCodeList(it, rows, links, secondRow, codeListNames, forthRow, headerNames) }
    }

    private fun mapCodeList(tableStartCell: Cell, allRows: List<Row>, links: List<Cell>, secondRow: List<Cell>, codeListNames: List<Cell>, forthRow: List<Cell>, headerNames: List<Cell>): CodeList? {
        try {
            val startColumn = tableStartCell.columnIndex

            val url = links.firstOrNull { it.columnIndex == startColumn }?.stringCellValue?.takeUnless { it.isBlank() }
            val name = codeListNames.firstOrNull { it.columnIndex == startColumn }?.stringCellValue?.takeUnless { it.isBlank() }
                ?: secondRow.firstOrNull { it.columnIndex == startColumn }?.stringCellValue?.takeUnless { it.isBlank() } // for EAS and VATEX the name is in second row
                ?: codeListNames.firstOrNull { it.columnIndex == startColumn + 1 }?.stringCellValue?.takeUnless { it.isBlank() } // for the country codes the name is in next column
            val usedInInvoiceFields = codeListNames.firstOrNull { it.columnIndex == startColumn + 1 }?.stringCellValue?.takeUnless { it.isBlank() }
                ?: codeListNames.firstOrNull { it.columnIndex == startColumn + 2 }?.stringCellValue?.takeUnless { it.isBlank() }
            val additionalUsedInInvoiceFields = forthRow.firstOrNull { it.columnIndex == startColumn + 1 }?.stringCellValue?.takeUnless { it.isBlank() }

            if (name == null) { // ISO currency codes table ends with a column called "Code" (columnIndex 30), actually below warning will be logged one time
                log.warn { "Could not find name for Code List table with start column index $startColumn" }
                return null
            }

            val type = getType(name)
            // very clever, the description is given in an extra column, now it's stated in the next row - which is empty otherwise. So we have to do a lot of special handling to the the description
            val isTypeWithDescription = type in CodeListsWithDescription
            // != 30: for currency Code - part of TableStartColumns - is the last column and has columnIndex 30, so don't stop here
            val indexOfNextEmptyCell = (headerNames.firstOrNull { it.columnIndex > startColumn && it.columnIndex != 30 && (it.stringCellValue.let { it.isBlank() || it in TableStartColumns || it == "Source" }) }
                ?: headerNames.firstOrNull { it.columnIndex == 53 })?.columnIndex // for last table we won't find the end otherwise
            val sourceColumn = headerNames.firstOrNull { it.columnIndex == startColumn - 1 && it.stringCellValue == "Source" }
            val columns = IntRange(startColumn, indexOfNextEmptyCell?.let { it - 1 } ?: startColumn).map { index -> headerNames.firstOrNull { it.columnIndex == index } }
                .mapNotNull { mapColumn(it) }.toMutableList().apply {
                    mapColumn(sourceColumn)?.let { add(it) }
                }
            val columnIndices = columns.map { it.index }
            val descriptionColumnIndex = columns.firstOrNull { it.name == "Meaning" }?.index ?: columnIndices.last()

            // if this Code List has a description, ignore every second row, as in the second row is the description
            val rows = allRows.drop(5).filterIndexed { index, _ -> isTypeWithDescription == false || index % 2 == 0 }.map { row ->
                val cells = columnIndices.map { row.getCell(it) }
                // if the cell is filled with color "FF4BACC6" (but not gray) that means this value is frequently used
                val isFrequentlyUsedValue = cells.all { (it?.cellStyle?.fillForegroundColorColor as? XSSFColor)?.argbHex == "FF4BACC6"
                        || it?.columnIndex == sourceColumn?.columnIndex } // only the source column never has a background color

                val values = cells.map { getCellValue(it) } +
                        ( if (isTypeWithDescription) listOf(getCellValue(allRows.get(row.rowNum + 1).getCell(descriptionColumnIndex))) else emptyList())
                net.codinux.invoicing.parser.model.Row(values, isFrequentlyUsedValue)
            }.filterNot { it.values.all { it == null } } // filter out empty rows

            if (isTypeWithDescription) {
                columns.add(Column(indexOfNextEmptyCell!! - 1, "Description", "String", "Description"))
            }

            return CodeList(type, name, url, usedInInvoiceFields, additionalUsedInInvoiceFields, columns, rows)
        } catch (e: Throwable) {
            log.error(e) { "Code not map Code List for start cell index ${tableStartCell.columnIndex}" }
            return null
        }
    }

    private fun getType(name: String): CodeListType = when (name) {
        "ISO 3166" -> CodeListType.IsoCountryCodes
        "ISO 4217" -> CodeListType.IsoCurrencyCodes
        "ISO 6523" -> CodeListType.Iso_6523_IdentificationSchemeIdentifier

        "UNTDID 1001" -> CodeListType.UN_1001_InvoiceType
        "UNTDID 1153" -> CodeListType.UN_1153_ReferenceCode

        "UNTDID 4451" -> CodeListType.UN_4451_TextSubjectCodeQualifier
        "UNTDID 4461" -> CodeListType.UN_4461_PaymentCodes
        "UNTDID 5189" -> CodeListType.UN_5189_AllowanceIdentificationCode
        "UNTDID 7143" -> CodeListType.UN_7143_ItemTypeIdentificationCode
        "UNTDID7161" -> CodeListType.UN_7161_SpecialServiceDescriptionCodes

        "Unit of Measure" -> CodeListType.Units

        "EAS : Electronice Address Scheme" -> CodeListType.EAS
        "CEF VATEX — VAT exemption reason code" -> CodeListType.VATEX

        else -> throw IllegalArgumentException("No known Code List of name '$name' found")
    }

    private fun mapColumn(cell: Cell?): Column? = cell?.let {
        val name = cell.stringCellValue ?: ""
        return Column(cell.columnIndex, name, "String", name)
    }

    private fun getCellValue(cell: Cell?) = when (cell?.cellType) {
        CellType.STRING -> cell.stringCellValue.trim().takeUnless { it.isBlank() }
        CellType.NUMERIC -> cell.numericCellValue.toInt()
        CellType.BLANK -> null
        else -> null
    }

    private fun <T> Iterator<T>.toList(): List<T> =
        this.asSequence().toList()

}
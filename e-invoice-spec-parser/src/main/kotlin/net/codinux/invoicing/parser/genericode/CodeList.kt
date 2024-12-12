package net.codinux.invoicing.parser.genericode

import net.codinux.invoicing.parser.model.CodeListType
import net.codinux.invoicing.parser.model.Column
import net.codinux.invoicing.parser.model.Row

class CodeList(
    val type: CodeListType,
    val name: String,
    val version: String?,
    val canonicalUri: String?,
    val canonicalVersionUri: String?,
    val columns: List<Column>,
    val rows: List<Row>
) {
    override fun toString() = "$name ${columns.joinToString { it.name }}, ${rows.size} rows"
}
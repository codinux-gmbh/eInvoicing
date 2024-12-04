package net.codinux.invoicing.parser.excel

import net.codinux.invoicing.parser.model.CodeListType
import net.codinux.invoicing.parser.model.Column

data class CodeList(
    val type: CodeListType,
    val name: String,
    val url: String?,
    val usedInInvoiceFields: String?,
    val additionalUsedInInvoiceFields: String?,
    val columns: List<Column>,
    val rows: List<List<Any?>>
) {
    override fun toString() = "$name${usedInInvoiceFields?.let { ", $it" } ?: ""}"
}
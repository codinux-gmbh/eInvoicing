package net.codinux.invoicing.parser.model

data class Column(
    val index: Int,
    val id: String,
    val dataType: String,
    val name: String,
) {
    override fun toString() = "$dataType $name ($id)"
}
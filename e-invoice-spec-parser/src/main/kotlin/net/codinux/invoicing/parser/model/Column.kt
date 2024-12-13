package net.codinux.invoicing.parser.model

data class Column(
    val index: Int,
    val id: String,
    val dataType: String,
    val name: String = id,
) {
    override fun toString() = "$dataType $name ($id)"
}
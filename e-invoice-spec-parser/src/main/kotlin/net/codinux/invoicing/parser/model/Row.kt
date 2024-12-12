package net.codinux.invoicing.parser.model

class Row(
    values: List<Any?>,
    val isFrequentlyUsedValue: Boolean = false
) {

    val values: List<Any?> = values.toMutableList()

    fun addValue(value: Any?) {
        (values as? MutableList<Any?>)?.add(value)
    }

    fun addIsFrequentlyUsedValueCell() {
        addValue(isFrequentlyUsedValue)
    }

    fun removeValueAtIndex(index: Int): Any? =
        (values as? MutableList<Any?>)?.removeAt(index)


    override fun toString() = values.joinToString()
}
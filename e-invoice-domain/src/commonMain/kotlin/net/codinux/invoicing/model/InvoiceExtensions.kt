package net.codinux.invoicing.model


inline fun <T> Iterable<T>.sumOf(selector: (T) -> BigDecimal): BigDecimal {
    var sum: BigDecimal = BigDecimal.Zero
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
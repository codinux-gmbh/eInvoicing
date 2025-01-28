package net.codinux.invoicing.model


private val Hundred = BigDecimal(100)

fun BigDecimal.percent(percent: BigDecimal): BigDecimal =
    (this * percent / Hundred).setScale(2)

inline fun <T> Iterable<T>.sumOf(selector: (T) -> BigDecimal): BigDecimal {
    var sum: BigDecimal = BigDecimal.Zero
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

fun Collection<BigDecimal>.sum(): BigDecimal = sumOf { it }
package net.codinux.invoicing.model

actual class BigDecimal actual constructor(private val value: String) {

    actual companion object {
        actual val Zero = BigDecimal("0")
    }


    actual constructor(value: Int) : this(value.toString())


    actual fun toPlainString(): String = value


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false
        return true
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }

    override fun toString() = value

}
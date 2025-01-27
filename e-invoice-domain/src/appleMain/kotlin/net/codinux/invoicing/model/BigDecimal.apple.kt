package net.codinux.invoicing.model

import kotlinx.cinterop.UnsafeNumber
import kotlinx.serialization.Serializable
import net.codinux.invoicing.platform.toNSUInteger
import net.codinux.invoicing.serialization.BigDecimalSerializer
import platform.Foundation.*

@OptIn(UnsafeNumber::class)
@Serializable(with = BigDecimalSerializer::class)
actual class BigDecimal(private val value: NSDecimalNumber) : Comparable<BigDecimal> {

    actual companion object {
        actual val Zero = BigDecimal("0")

        val MinusOne = BigDecimal(NSDecimalNumber(mantissa = 1u, exponent = 0, isNegative = true))
    }

    actual constructor(value: String) : this(NSDecimalNumber(string = value))
    actual constructor(value: Int) : this(NSDecimalNumber(int = value))
    constructor(value: Double) : this(NSDecimalNumber(double = value))


    fun plus(other: BigDecimal): BigDecimal = BigDecimal(value.decimalNumberByAdding (other.value))
    fun minus(other: BigDecimal): BigDecimal = BigDecimal(value.decimalNumberBySubtracting(other.value))
    fun times(other: BigDecimal): BigDecimal = BigDecimal(value.decimalNumberByMultiplyingBy(other.value))
    fun divide(other: BigDecimal): BigDecimal = BigDecimal(value.decimalNumberByDividingBy(other.value))
    fun modulo(other: BigDecimal): BigDecimal {
        val quotient = this.value.decimalNumberByDividingBy(other.value, withBehavior = numberHandler(0, NSRoundingMode.NSRoundDown))
        val subtractAmount = quotient.decimalNumberByMultiplyingBy(other.value)
        return BigDecimal(this.value.decimalNumberBySubtracting(subtractAmount))
    }

    val isNegative: Boolean by lazy { this.compareTo(Zero) < 0 }
    fun negated(): BigDecimal = this.times(MinusOne)
//    fun sqrt(): BigDecimal = BigDecimal(value.raising(toPower = 0.5))
    fun pow(exponent: Int): BigDecimal = BigDecimal(value.decimalNumberByRaisingToPower(exponent.toNSUInteger()))
    fun abs(): BigDecimal = if (value.compare(NSDecimalNumber.zero) == NSOrderedAscending) negated() else this

    fun round(decimalPlaces: Int, roundingMode: NSRoundingMode = NSRoundingMode.NSRoundPlain): BigDecimal {
        val handler = numberHandler(decimalPlaces, roundingMode)
        return BigDecimal(value.decimalNumberByRoundingAccordingToBehavior(handler))
    }

    actual fun setScale(newScale: Int): BigDecimal = round(newScale)

    private fun numberHandler(decimalPlaces: Int, roundingMode: NSRoundingMode = NSRoundingMode.NSRoundPlain) = NSDecimalNumberHandler(
        roundingMode = roundingMode,
        scale = decimalPlaces.toShort(),
        raiseOnExactness = false,
        raiseOnOverflow = false,
        raiseOnUnderflow = false,
        raiseOnDivideByZero = false
    )

    actual override fun compareTo(other: BigDecimal): Int = when (value.compare(other.value)) {
        NSOrderedAscending -> -1
        NSOrderedSame -> 0
        NSOrderedDescending -> 1
        else -> throw IllegalStateException("Unexpected comparison result")
    }

    fun equals(other: BigDecimal): Boolean = value.isEqualToNumber(other.value)

    actual fun toInt(): Int = value.intValue
    actual fun toDouble(): Double = value.doubleValue

    actual fun toPlainString(): String = value.stringValue

    fun toFixed(decimalPlaces: Int): String {
        val format = NSNumberFormatter().apply {
            this.locale = NSLocale(localeIdentifier = "en_US") // otherwise e.g. on German systems ',' would be used as decimal separator
            this.numberStyle = NSNumberFormatterNoStyle
            this.minimumFractionDigits = decimalPlaces.toNSUInteger()
            this.maximumFractionDigits = decimalPlaces.toNSUInteger()
            this.roundingMode = NSRoundingMode.NSRoundPlain.value
        }

        return format.stringFromNumber(this.value)!!
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BigDecimal) return false

        return value.equals(other.value)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = toPlainString()

}
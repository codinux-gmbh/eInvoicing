package net.codinux.invoicing.model

import org.khronos.webgl.Int32Array

val DefaultRoundingMode = 1

// scale = number of digits after the decimal point
// significant digits = the number of meaningful digits in the entire number

@JsModule("big.js")
//@JsNonModule
external class BigJs(value: String) {
    val c: Int32Array // coefficient (i.e. significand), e.g. [1,2,3,4,5,6]
    val e: Int // exponent
    val s: Int // sign

    fun plus(other: BigJs): BigJs
    fun minus(other: BigJs): BigJs
    fun times(other: BigJs): BigJs
    fun div(other: BigJs): BigJs
    fun mod(modulo: Int): BigJs
    fun neg(): BigJs // negated value of this BigJs

    /*
     * Return a new Big whose value is the value of this Big rounded to a maximum of dp decimal places
     * using rounding mode rm, or Big.RM if rm is not specified.
     * If dp is negative, round to an integer which is a multiple of 10**-dp.
     * If dp is not specified, round to 0 decimal places.
     *
     * dp? {number} Integer, -MAX_DP to MAX_DP inclusive.
     * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
     */
    fun round(decimalPlaces: Int): BigJs
    fun round(decimalPlaces: Int, roundingMode: Int): BigJs

    /**
     * Return a new Big whose value is the value of this Big rounded to a maximum precision of sd
     * significant digits using rounding mode rm, or Big.RM if rm is not specified.
     *
     * Significant digits: integer, 1 to MAX_DP inclusive.
     * Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
     */
    fun prec(significantDigits: Int): BigJs
    fun prec(significantDigits: Int, roundingMode: Int): BigJs

    fun sqrt(): BigJs
    fun pow(power: Int): BigJs
    fun abs(): BigJs

    fun cmp(other: BigJs): Int
    fun eq(other: BigJs): Boolean
    fun gt(other: BigJs): Boolean
    fun gte(other: BigJs): Boolean // greater than or equal
    fun lt(other: BigJs): Boolean
    fun lte(other: BigJs): Boolean // lower than or equal

    /*
     * Return a string representing the value of this Big in exponential notation rounded to dp fixed
     * decimal places using rounding mode rm, or Big.RM if rm is not specified.
     *
     * dp? {number} Decimal places: integer, 0 to MAX_DP inclusive.
     * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
     */
    fun toExponential(decimalPlaces: Int): String
    fun toExponential(decimalPlaces: Int, roundingMode: Int): String

    /*
     * Return a string representing the value of this Big in normal notation rounded to dp fixed
     * decimal places using rounding mode rm, or Big.RM if rm is not specified.
     *
     * dp? {number} Decimal places: integer, 0 to MAX_DP inclusive.
     * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
     *
     * (-0).toFixed(0) is '0', but (-0.1).toFixed(0) is '-0'.
     * (-0).toFixed(1) is '0.0', but (-0.01).toFixed(1) is '-0.0'.
     */
    fun toFixed(decimalPlaces: Int): String
    fun toFixed(decimalPlaces: Int, roundingMode: Int): String

    /*
     * Return a string representing the value of this Big rounded to sd significant digits using
     * rounding mode rm, or Big.RM if rm is not specified.
     * Use exponential notation if sd is less than the number of digits necessary to represent
     * the integer part of the value in normal notation.
     *
     * sd {number} Significant digits: integer, 1 to MAX_DP inclusive.
     * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
     */
    fun toPrecision(significantDigits: Int): String
    fun toPrecision(significantDigits: Int, roundingMode: Int): String

    /*
     * Return the value of this Big as a primitve number.
     */
    fun toNumber(): JsNumber
    /*
     * Return a string representing the value of this Big.
     * Return exponential notation if this Big has a positive exponent equal to or greater than
     * Big.PE, or a negative exponent equal to or less than Big.NE.
     * Include the sign for negative zero.
     */
    fun valueOf(): String

    override fun toString(): String
}
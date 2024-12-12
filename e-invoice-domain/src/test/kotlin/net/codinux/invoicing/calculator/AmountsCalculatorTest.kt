package net.codinux.invoicing.calculator

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import assertk.assertions.isEqualTo
import net.codinux.invoicing.model.InvoiceItem
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.test.Test

class AmountsCalculatorTest {

    private val underTest = AmountsCalculator()


    @Test
    fun calculateTotalAmounts() {
        val items = listOf(
            InvoiceItem("", BigDecimal(7), "", BigDecimal(5), BigDecimal(19)),
            InvoiceItem("", BigDecimal(20), "", BigDecimal(5), BigDecimal(7)),
        )

        val result = underTest.calculateTotalAmounts(items)

        val expectedNetAmount = BigDecimal(7 * 5 + 20 * 5).setScale(2)
        val expectedVatAmount = BigDecimal(7 * 5 * 0.19 + 20 * 5 * 0.07).setScale(2, RoundingMode.DOWN)
        val expectedTotalAmount = expectedNetAmount + expectedVatAmount

        assertThat(result.lineTotalAmount).isEqualByComparingTo(expectedNetAmount)
        assertThat(result.taxBasisTotalAmount).isEqualByComparingTo(expectedNetAmount)

        assertThat(result.taxTotalAmount).isEqualTo(expectedVatAmount)

        assertThat(result.grandTotalAmount).isEqualTo(expectedTotalAmount)
        assertThat(result.duePayableAmount).isEqualTo(expectedTotalAmount)
    }

}
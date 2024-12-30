package net.codinux.invoicing.calculator

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.TotalAmounts
import kotlin.test.Test

class WebServiceAmountsCalculatorTest {

    companion object {
        val itemPrices = listOf(
            InvoiceItemPrice(BigDecimal(7), BigDecimal(5), BigDecimal(19)),
            InvoiceItemPrice(BigDecimal(20), BigDecimal(5), BigDecimal(7))
        )


        private val expectedNetAmount = BigDecimal(7 * 5 + 20 * 5).setScale(2)
        private val expectedVatAmount = BigDecimal((7 * 5 * 0.19 + 20 * 5 * 0.07).toString()).setScale(2) // 13,65
        private val expectedTotalAmount = BigDecimal((7 * 5 + 7 * 5 * 0.19 + 20 * 5 + 20 * 5 * 0.07).toString()).setScale(2) // 148,65


        fun assertTotalAmounts(result: TotalAmounts?) {
            assertThat(result).isNotNull()

            assertThat(result!!.lineTotalAmount).isEqualTo(expectedNetAmount)
            assertThat(result.taxBasisTotalAmount).isEqualTo(expectedNetAmount)

            assertThat(result.taxTotalAmount).isEqualTo(expectedVatAmount)

            assertThat(result.grandTotalAmount).isEqualTo(expectedTotalAmount)
            assertThat(result.duePayableAmount).isEqualTo(expectedTotalAmount)
        }
    }


    private val underTest = WebServiceAmountsCalculator()


    @Test
    fun calculateTotalAmounts() = runTest {
        val result = underTest.calculateTotalAmounts(itemPrices)

        assertTotalAmounts(result)
    }

}
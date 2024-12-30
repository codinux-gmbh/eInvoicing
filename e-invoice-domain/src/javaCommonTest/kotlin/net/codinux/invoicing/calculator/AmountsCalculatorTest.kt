package net.codinux.invoicing.calculator

import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.InvoiceItem
import net.codinux.invoicing.model.codes.UnitOfMeasure
import kotlin.test.Test

class AmountsCalculatorTest {

    private val underTest = AmountsCalculator()


    @Test
    fun calculateTotalAmounts() {
        val items = listOf(
            InvoiceItem("", BigDecimal(7), UnitOfMeasure.HUR, BigDecimal(5), BigDecimal(19)),
            InvoiceItem("", BigDecimal(20), UnitOfMeasure.HUR, BigDecimal(5), BigDecimal(7)),
        )

        val result = underTest.calculateTotalAmounts(items)

        WebServiceAmountsCalculatorTest.assertTotalAmounts(result)
    }

    @Test
    fun calculateTotalAmountsFromInvoiceItemPrices() {
        val result = underTest.calculateTotalAmountsJvm(WebServiceAmountsCalculatorTest.itemPrices)

        WebServiceAmountsCalculatorTest.assertTotalAmounts(result)
    }

}
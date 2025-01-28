package net.codinux.invoicing.calculator

import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.UnitOfMeasure

open class AmountsCalculator {

    private val invoiceDetails by lazy { InvoiceDetails("", LocalDate(0, 1, 1)) }

    private val party by lazy { Party("", "", null, null, "") }


    open fun ensureLineMonetarySummationsAreSet(invoice: Invoice) =
        invoice.items.map { ensureLineMonetarySummationIsSet(it) }

    open fun ensureLineMonetarySummationIsSet(item: InvoiceItem): LineMonetarySummation =
        item.monetarySummation ?: run {
            val summation = calculateLineMonetarySummation(item)
            item.monetarySummation = summation
            summation
        }

    open fun calculateLineMonetarySummation(item: InvoiceItem): LineMonetarySummation {
        val grossPrice = item.unitPrice * item.quantity
        // we don't support charges and allowances yet, therefore the gross price always equals the net price
        val netPrice = grossPrice

        val taxAmount = netPrice * item.vatRate / BigDecimal(100)

        return LineMonetarySummation(
            null,
            netPrice,
            taxAmount,
            netPrice + taxAmount
        )
    }


    open fun ensureTotalAmountsIsSet(invoice: Invoice): TotalAmounts {
        ensureLineMonetarySummationsAreSet(invoice)

        return invoice.totals ?: run {
            val totals = calculateTotalAmounts(invoice)
            invoice.totals = totals
            totals
        }
    }

    open fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>): TotalAmounts =
        calculateTotalAmounts(itemPrices.map { InvoiceItem("", it.quantity, UnitOfMeasure.ZZ, it.unitPrice, it.vatRate) })

    open fun calculateTotalAmounts(items: List<InvoiceItem>) =
        calculateTotalAmounts(Invoice(invoiceDetails, party, party, items))

    open fun calculateTotalAmounts(invoice: Invoice): TotalAmounts {
        val lineTotals = invoice.items.map { ensureLineMonetarySummationIsSet(it) }

        val lineTotalAmount = lineTotals.sumOf { it.netPrice }
        val chargeTotalAmount = invoice.amountAdjustments?.charges?.sumOf { it.actualAmount } ?: BigDecimal.Zero
        val allowanceTotalAmount = invoice.amountAdjustments?.allowances?.sumOf { it.actualAmount } ?: BigDecimal.Zero
        val taxBasisTotalAmount = lineTotalAmount + chargeTotalAmount - allowanceTotalAmount
        val taxTotalAmount = lineTotals.sumOf { it.taxAmount }
        val grandTotalAmount = taxBasisTotalAmount + taxTotalAmount
        val totalPrepaidAmount = invoice.amountAdjustments?.prepaidAmounts ?: BigDecimal.Zero

        return TotalAmounts(
            lineTotalAmount = lineTotalAmount,
            chargeTotalAmount = chargeTotalAmount,
            allowanceTotalAmount = allowanceTotalAmount,
            taxBasisTotalAmount = taxBasisTotalAmount,
            taxTotalAmount = taxTotalAmount,
            grandTotalAmount = grandTotalAmount,
            totalPrepaidAmount = totalPrepaidAmount,
            duePayableAmount = grandTotalAmount - totalPrepaidAmount
        )
    }

}
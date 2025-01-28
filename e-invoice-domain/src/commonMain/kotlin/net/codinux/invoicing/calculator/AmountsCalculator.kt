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
        val grossPrice = item.unitPrice
        // we don't support charges and allowances yet, therefore the gross price always equals the net price
        val netPrice = grossPrice

        val taxAmount = netPrice.percent(item.vatRate)

        return LineMonetarySummation(
            null,
            netPrice,
            emptyList(),
            emptyList(),
            netPrice * item.quantity,
            taxAmount
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

        val documentLevelCharges = invoice.amountAdjustments?.charges.orEmpty()
        val documentLevelAllowances = invoice.amountAdjustments?.allowances.orEmpty()

        val lineTotalAmount = lineTotals.sumOf { it.lineTotalAmount }
        val chargeTotalAmount = documentLevelCharges.sumOf { it.actualAmount }
        val allowanceTotalAmount = documentLevelAllowances.sumOf { it.actualAmount }
        val taxBasisTotalAmount = lineTotalAmount + chargeTotalAmount - allowanceTotalAmount
        val taxTotalAmount = lineTotals.sumOf { it.lineTotalTaxAmount } + getTaxAmount(documentLevelCharges) - getTaxAmount(documentLevelAllowances)
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

    protected open fun getTaxAmount(chargeOrAllowances: List<ChargeOrAllowance>): BigDecimal =
        chargeOrAllowances.mapNotNull { charge -> charge.taxRateApplicablePercent?.let { charge.actualAmount.percent(it) } }.sum()

}
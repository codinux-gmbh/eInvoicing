package net.codinux.invoicing.calculator

import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.UnitOfMeasure

open class AmountsCalculator {

    private val invoiceDetails by lazy { InvoiceDetails("", LocalDate(0, 1, 1)) }

    private val party by lazy { Party("", "", null, null, "") }


    open fun ensureLineAmountsAreSet(invoice: Invoice) =
        invoice.items.map { ensureLineAmountsSet(it) }

    open fun ensureLineAmountsSet(item: InvoiceItem): LineAmounts =
        item.amounts ?: calculateLineAmounts(item).also {
            item.amounts = it
        }

    open fun calculateLineAmounts(item: InvoiceItem): LineAmounts {
        val grossPrice = item.unitPrice
        // we don't support charges and allowances yet, therefore the gross price always equals the net price
        val netPrice = grossPrice

        val lineTotalAmount = netPrice * item.quantity
        val taxAmount = lineTotalAmount.percent(item.vatRate)

        return LineAmounts(
            null,
            netPrice,
            emptyList(),
            emptyList(),
            lineTotalAmount,
            taxAmount
        )
    }


    open fun ensureTotalAmountsIsSet(invoice: Invoice): TotalAmounts {
        ensureLineAmountsAreSet(invoice)

        return invoice.totals ?: calculateTotalAmounts(invoice).also {
            invoice.totals = it
        }
    }

    open fun calculateTotalAmounts(itemPrices: Collection<InvoiceItemPrice>): TotalAmounts =
        calculateTotalAmounts(itemPrices.map { InvoiceItem("", it.quantity, UnitOfMeasure.ZZ, it.unitPrice, it.vatRate) })

    open fun calculateTotalAmounts(items: List<InvoiceItem>) =
        calculateTotalAmounts(Invoice(invoiceDetails, party, party, items))

    open fun calculateTotalAmounts(invoice: Invoice): TotalAmounts {
        val lineTotals = invoice.items.map { ensureLineAmountsSet(it) }

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
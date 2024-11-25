package net.codinux.invoicing.calculator

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.model.TotalAmounts
import org.mustangproject.ZUGFeRD.IExportableTransaction
import org.mustangproject.ZUGFeRD.TransactionCalculator
import java.math.BigDecimal

open class AmountsCalculator {

    protected open val mapper by lazy { MustangMapper() } // lazy to avoid circular dependency creation with MustangMapper


    open fun calculateTotalAmounts(invoice: Invoice) =
        calculateTotalAmounts(mapper.mapToTransaction(invoice))

    open fun calculateTotalAmounts(invoice: IExportableTransaction): TotalAmounts {
        val calculator = MustangTransactionCalculator(invoice)

        return TotalAmounts(
            lineTotalAmount = calculator.lineTotalAmount,
            chargeTotalAmount = calculator.chargeTotal,
            allowanceTotalAmount = calculator.allowanceTotal,
            taxBasisTotalAmount = calculator.taxBasisTotalAmount,
            taxTotalAmount = calculator.taxTotalAmount,
            grandTotalAmount = calculator.grandTotal,
            totalPrepaidAmount = calculator.totalPrepaidAmount,
            duePayableAmount = calculator.duePayableAmount
        )
    }


    protected open class MustangTransactionCalculator(invoice: IExportableTransaction): TransactionCalculator(invoice) {
        val lineTotalAmount: BigDecimal = this.total

        val taxBasisTotalAmount: BigDecimal = this.taxBasis
        val taxTotalAmount: BigDecimal by lazy { this.getGrandTotal().subtract(this.taxBasis) }

        val totalPrepaidAmount: BigDecimal = this.totalPrepaid
        val duePayableAmount: BigDecimal by lazy { this.getGrandTotal().subtract(this.totalPrepaid) }
    }

}
package net.codinux.invoicing.calculator

import net.codinux.invoicing.mapper.MustangMapper
import net.codinux.invoicing.model.*
import org.mustangproject.ZUGFeRD.IExportableTransaction
import org.mustangproject.ZUGFeRD.TransactionCalculator
import java.math.BigDecimal

open class AmountsCalculator {

    protected open val mapper by lazy { MustangMapper() } // lazy to avoid circular dependency creation with MustangMapper

    private val invoiceDetails by lazy { InvoiceDetails("", LocalDate.now()) }

    private val party by lazy { Party("", "", null, null, "") }


    open fun calculateTotalAmounts(items: List<InvoiceItem>) =
        calculateTotalAmounts(Invoice(invoiceDetails, party, party, items))

    open fun calculateTotalAmounts(invoice: Invoice) =
        calculateTotalAmounts(mapper.mapToTransaction(invoice))

    open fun calculateTotalAmounts(invoice: IExportableTransaction): TotalAmounts {
        val calculator = MustangTransactionCalculator(invoice)

        return TotalAmounts(
            lineTotalAmount = calculator.lineTotalAmount.toEInvoicingBigDecimal(),
            chargeTotalAmount = calculator.chargeTotal.toEInvoicingBigDecimal(),
            allowanceTotalAmount = calculator.allowanceTotal.toEInvoicingBigDecimal(),
            taxBasisTotalAmount = calculator.taxBasisTotalAmount.toEInvoicingBigDecimal(),
            taxTotalAmount = calculator.taxTotalAmount.toEInvoicingBigDecimal(),
            grandTotalAmount = calculator.grandTotal.toEInvoicingBigDecimal(),
            totalPrepaidAmount = calculator.totalPrepaidAmount.toEInvoicingBigDecimal(),
            duePayableAmount = calculator.duePayableAmount.toEInvoicingBigDecimal()
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
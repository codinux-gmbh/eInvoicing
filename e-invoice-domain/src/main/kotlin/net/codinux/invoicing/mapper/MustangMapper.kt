package net.codinux.invoicing.mapper

import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.AmountAdjustments
import net.codinux.invoicing.model.ChargeOrAllowance
import net.codinux.invoicing.model.InvoiceItem
import net.codinux.invoicing.model.Party
import org.mustangproject.*
import org.mustangproject.ZUGFeRD.IExportableTransaction
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableItem
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

open class MustangMapper(
    protected open val calculator: AmountsCalculator = AmountsCalculator()
) {

    open fun mapToTransaction(invoice: net.codinux.invoicing.model.Invoice): IExportableTransaction = Invoice().apply {
        this.number = invoice.invoiceNumber
        this.issueDate = map(invoice.invoicingDate)
        this.sender = mapParty(invoice.sender)
        this.recipient = mapParty(invoice.recipient)

        this.setZFItems(ArrayList(invoice.items.map { mapLineItem(it) }))

        this.dueDate = map(invoice.dueDate)
        this.paymentTermDescription = invoice.paymentDescription

        this.referenceNumber = invoice.buyerReference

        invoice.amountAdjustments?.let { adjustments ->
            this.totalPrepaidAmount = adjustments.prepaidAmounts
            adjustments.charges.forEach { this.addCharge(mapCharge(it)) }
            adjustments.allowances.forEach { this.addAllowance(mapAllowance(it)) }
        }

        if (invoice.totalAmounts == null) {
            invoice.totalAmounts = calculator.calculateTotalAmounts(this)
        }
    }

    open fun mapParty(party: Party): TradeParty = TradeParty(
        party.name, party.street, party.postalCode, party.city, party.countryIsoCode
    ).apply {
        this.setVATID(party.vatId)
        // TODO: description?

        this.email = party.email
        this.setContact(Contact(party.contactName, party.phone, party.email).apply {
            this.fax = party.fax
        })

        party.bankDetails?.let {
            this.addBankDetails(BankDetails(it.accountNumber, it.bankCode).apply {
                accountName = it.accountHolderName
                // TODO: there's currently no field for financialInstitutionName in Zugferd model even though it exists on CII and UBL
            })
        }
    }

    open fun mapLineItem(item: InvoiceItem): IZUGFeRDExportableItem = Item(
        // description has to be an empty string if not set
        Product(item.name, item.description ?: "", item.unit, item.vatRate), item.unitPrice, item.quantity
    ).apply {

    }

    protected open fun mapCharge(charge: ChargeOrAllowance) = Charge(charge.actualAmount).apply {
        this.percent = charge.calculationPercent

        this.reason = charge.reason
        this.reasonCode = charge.reasonCode

        this.taxPercent = charge.taxRateApplicablePercent
        this.categoryCode = charge.taxCategoryCode
    }

    protected open fun mapAllowance(allowance: ChargeOrAllowance) = Allowance(allowance.actualAmount).apply {
        this.percent = allowance.calculationPercent

        this.reason = allowance.reason
        this.reasonCode = allowance.reasonCode

        this.taxPercent = allowance.taxRateApplicablePercent
        this.categoryCode = allowance.taxCategoryCode
    }


    open fun mapToInvoice(invoice: Invoice) = net.codinux.invoicing.model.Invoice(
        invoiceNumber = invoice.number,
        invoicingDate = map(invoice.issueDate),
        sender = mapParty(invoice.sender),
        recipient = mapParty(invoice.recipient),
        items = invoice.zfItems.map { mapLineItem(it) },

        dueDate = map(invoice.dueDate ?: invoice.paymentTerms?.dueDate),
        paymentDescription = invoice.paymentTermDescription ?: invoice.paymentTerms?.description,

        buyerReference = invoice.referenceNumber,

        amountAdjustments = mapAmountAdjustments(invoice),

        totalAmounts = calculator.calculateTotalAmounts(invoice)
    )

    open fun mapParty(party: TradeParty) = Party(
        party.name, party.street, party.zip, party.location, party.country, party.vatID,
        party.email ?: party.contact?.eMail, party.contact?.phone, party.contact?.fax, party.contact?.name,
        party.bankDetails?.firstOrNull()?.let { net.codinux.invoicing.model.BankDetails(it.iban, it.bic, it.accountName) }
    )

    open fun mapLineItem(item: IZUGFeRDExportableItem) = InvoiceItem(
        item.product.name, item.quantity, item.product.unit, item.price, item.product.vatPercent, item.product.description.takeUnless { it.isBlank() }
    )

    protected open fun mapAmountAdjustments(invoice: Invoice): AmountAdjustments? {
        if ((invoice.totalPrepaidAmount == null || invoice.totalPrepaidAmount == BigDecimal.ZERO)
            && invoice.zfCharges.isNullOrEmpty() && invoice.zfAllowances.isNullOrEmpty()) {
            return null
        }

        return AmountAdjustments(
            invoice.totalPrepaidAmount,
            invoice.zfCharges.mapNotNull { mapChargeOrAllowance(it as? Charge) },
            invoice.zfAllowances.mapNotNull { mapChargeOrAllowance(it as? Allowance ?: it as? Charge) }
        )
    }

    private fun mapChargeOrAllowance(chargeOrAllowance: Charge?) = chargeOrAllowance?.let {
        ChargeOrAllowance(it.totalAmount, null, null, it.percent, it.reason, it.reasonCode, it.taxPercent, it.categoryCode)
    }



    @JvmName("mapNullable")
    protected fun map(date: LocalDate?) =
        date?.let { map(it) }

    protected open fun map(date: LocalDate): Date =
        Date.from(mapToInstant(date))

    protected open fun mapToInstant(date: LocalDate): Instant =
        date.atStartOfDay(ZoneId.systemDefault()).toInstant()

    @JvmName("mapNullable")
    protected fun map(date: Date?) =
        date?.let { map(it) }

    protected open fun map(date: Date): LocalDate =
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

}
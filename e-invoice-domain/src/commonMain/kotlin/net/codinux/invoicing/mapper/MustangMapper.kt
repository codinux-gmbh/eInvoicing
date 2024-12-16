package net.codinux.invoicing.mapper

import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.Country
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure
import org.mustangproject.*
import org.mustangproject.BankDetails
import org.mustangproject.Invoice
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

    companion object {
        val CountriesByIsoCode = Country.entries.associateBy { it.alpha2Code }

        val CurrenciesByIsoCode = Currency.entries.associateBy { it.alpha3Code }

        val UnitsByCode = UnitOfMeasure.entries.associateBy { it.code }
    }


    open fun mapToTransaction(invoice: net.codinux.invoicing.model.Invoice): IExportableTransaction = Invoice().apply {
        this.number = invoice.details.invoiceNumber
        this.issueDate = map(invoice.details.invoiceDate)
        this.currency = invoice.details.currency.alpha3Code

        this.sender = mapParty(invoice.supplier)
        this.recipient = mapParty(invoice.customer)

        this.setZFItems(ArrayList(invoice.items.map { mapLineItem(it) }))

        this.dueDate = map(invoice.details.dueDate)
        this.paymentTermDescription = invoice.details.paymentDescription

        this.referenceNumber = invoice.customerReferenceNumber

        invoice.amountAdjustments?.let { adjustments ->
            this.totalPrepaidAmount = adjustments.prepaidAmounts
            adjustments.charges.forEach { this.addCharge(mapCharge(it)) }
            adjustments.allowances.forEach { this.addAllowance(mapAllowance(it)) }
        }

        if (invoice.totals == null) {
            invoice.totals = calculator.calculateTotalAmounts(this)
        }
    }

    open fun mapParty(party: Party): TradeParty = TradeParty(
        party.name, party.address, party.postalCode, party.city, party.country.alpha2Code
    ).apply {
        this.setAdditionalAddress(party.additionalAddressLine)

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
        Product(item.name, item.description ?: "", item.unit.code, item.vatRate).apply {
            this.sellerAssignedID = item.articleNumber // TODO: what is the articleNumber? sellerAssignedId, globalId, ...?
        },
        item.unitPrice, item.quantity
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
        // TODO: what to do if currency code is unknown? Currently it throws an exception, but i don't like that mapping fails just due to an unknown currency code
        details = InvoiceDetails(invoice.number, map(invoice.issueDate), mapCurrency(invoice.currency), map(invoice.dueDate ?: invoice.paymentTerms?.dueDate), invoice.paymentTermDescription ?: invoice.paymentTerms?.description),

        supplier = mapParty(invoice.sender),
        customer = mapParty(invoice.recipient),
        items = invoice.zfItems.map { mapLineItem(it) },

        customerReferenceNumber = invoice.referenceNumber,

        amountAdjustments = mapAmountAdjustments(invoice),

        totals = calculator.calculateTotalAmounts(invoice)
    )

    open fun mapParty(party: TradeParty) = Party(
        // TODO: what to do if country code is unknown? Currently it throws an exception, but i don't like that mapping fails just due to an unknown country code
        party.name, party.street, party.additionalAddress, party.zip, party.location, mapCountry(party.country), party.vatID,
        party.email ?: party.contact?.eMail, party.contact?.phone, party.contact?.fax, party.contact?.name,
        party.bankDetails?.firstOrNull()?.let { net.codinux.invoicing.model.BankDetails(it.iban, it.bic, it.accountName) }
    )

    open fun mapLineItem(item: IZUGFeRDExportableItem) = InvoiceItem(
        // TODO: what to use as fallback if unit cannot be determined?
        item.product.name, item.quantity, item.product.unit?.let { UnitsByCode[it] } ?: UnitOfMeasure.ASM, item.price, item.product.vatPercent, item.product.sellerAssignedID, item.product.description.takeUnless { it.isBlank() }
    )

    protected open fun mapAmountAdjustments(invoice: Invoice): AmountAdjustments? {
        if ((invoice.totalPrepaidAmount == null || invoice.totalPrepaidAmount == BigDecimal.ZERO)
            && invoice.zfCharges.isNullOrEmpty() && invoice.zfAllowances.isNullOrEmpty()) {
            return null
        }

        return AmountAdjustments(
            invoice.totalPrepaidAmount,
            invoice.zfCharges.orEmpty().mapNotNull { mapChargeOrAllowance(it as? Charge) },
            invoice.zfAllowances.orEmpty().mapNotNull { mapChargeOrAllowance(it as? Allowance ?: it as? Charge) }
        )
    }

    private fun mapChargeOrAllowance(chargeOrAllowance: Charge?) = chargeOrAllowance?.let {
        ChargeOrAllowance(it.totalAmount, null, null, it.percent, it.reason, it.reasonCode, it.taxPercent, it.categoryCode)
    }


    private fun mapCountry(isoAlpha2CountryCode: String?): Country =
        CountriesByIsoCode[isoAlpha2CountryCode]
            ?: throw IllegalArgumentException("Unknown ISO Alpha-2 country code \"$isoAlpha2CountryCode\", therefore cannot map ISO code to Country")

    private fun mapCurrency(isoCurrencyCode: String?): Currency =
        CurrenciesByIsoCode[isoCurrencyCode]
            ?: throw IllegalArgumentException("Unknown ISO currency code \"$isoCurrencyCode\", therefore cannot map ISO code to Currency")

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
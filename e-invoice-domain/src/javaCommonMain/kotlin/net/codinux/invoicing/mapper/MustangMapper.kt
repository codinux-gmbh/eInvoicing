package net.codinux.invoicing.mapper

import net.codinux.invoicing.calculator.AmountsCalculator
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.Country
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure
import net.codinux.log.logger
import org.mustangproject.*
import org.mustangproject.BankDetails
import org.mustangproject.Invoice
import org.mustangproject.ZUGFeRD.IExportableTransaction
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableItem
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.util.*

open class MustangMapper(
    protected open val calculator: AmountsCalculator = AmountsCalculator()
) {

    companion object {
        val CountriesByIsoCode = Country.entries.associateBy { it.alpha2Code }

        val CurrenciesByIsoCode = Currency.entries.associateBy { it.alpha3Code }

        val UnitsByCode = UnitOfMeasure.entries.associateBy { it.code }

        val CountryFallbackValue = Country.UnknownCountry

        val CurrencyFallbackValue = Currency.TheCodesAssignedForTransactionsWhereNoCurrencyIsInvolved

        val UnitFallbackValue = UnitOfMeasure.ZZ
    }


    private val log by logger()


    open fun mapToTransaction(invoice: net.codinux.invoicing.model.Invoice): IExportableTransaction = Invoice().apply {
        this.number = invoice.details.invoiceNumber
        this.issueDate = map(invoice.details.invoiceDate)
        this.currency = invoice.details.currency.alpha3Code

        this.sender = mapParty(invoice.supplier)
        this.recipient = mapParty(invoice.customer)

        this.setZFItems(ArrayList(invoice.items.map { mapInvoiceItem(it) }))

        this.dueDate = map(invoice.details.dueDate)
        this.paymentTermDescription = invoice.details.paymentDescription

        this.referenceNumber = invoice.customerReferenceNumber

        invoice.amountAdjustments?.let { adjustments ->
            this.totalPrepaidAmount = adjustments.prepaidAmounts.toJvmBigDecimal()
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

    open fun mapInvoiceItem(item: InvoiceItem): IZUGFeRDExportableItem = Item(
        // description has to be an empty string if not set
        Product(item.name, item.description ?: "", item.unit.code, item.vatRate.toJvmBigDecimal()).apply {
            this.sellerAssignedID = item.articleNumber // TODO: what is the articleNumber? sellerAssignedId, globalId, ...?
        },
        item.unitPrice.toJvmBigDecimal(), item.quantity.toJvmBigDecimal()
    ).apply {

    }

    protected open fun mapCharge(charge: ChargeOrAllowance) = Charge(charge.actualAmount.toJvmBigDecimal()).apply {
        this.percent = charge.calculationPercent?.toJvmBigDecimal()

        this.reason = charge.reason
        this.reasonCode = charge.reasonCode

        this.taxPercent = charge.taxRateApplicablePercent?.toJvmBigDecimal()
        this.categoryCode = charge.taxCategoryCode
    }

    protected open fun mapAllowance(allowance: ChargeOrAllowance) = Allowance(allowance.actualAmount.toJvmBigDecimal()).apply {
        this.percent = allowance.calculationPercent?.toJvmBigDecimal()

        this.reason = allowance.reason
        this.reasonCode = allowance.reasonCode

        this.taxPercent = allowance.taxRateApplicablePercent?.toJvmBigDecimal()
        this.categoryCode = allowance.taxCategoryCode
    }


    open fun mapToInvoice(invoice: Invoice): MapInvoiceResult {
        val dataErrors = mutableListOf<InvoiceDataError>()

        return MapInvoiceResult(
            net.codinux.invoicing.model.Invoice(
                details = InvoiceDetails(invoice.number, map(invoice.issueDate), mapCurrency(invoice.currency, dataErrors), map(invoice.dueDate ?: invoice.paymentTerms?.dueDate), invoice.paymentTermDescription ?: invoice.paymentTerms?.description),

                supplier = mapParty(invoice.sender, true, dataErrors),
                customer = mapParty(invoice.recipient, false, dataErrors),
                items = invoice.zfItems.map { mapInvoiceItem(it, dataErrors) },

                customerReferenceNumber = invoice.referenceNumber,

                amountAdjustments = mapAmountAdjustments(invoice),

                totals = calculator.calculateTotalAmounts(invoice)
            ),
            dataErrors
        )
    }

    open fun mapParty(party: TradeParty, isSupplier: Boolean, dataErrors: MutableList<InvoiceDataError>) = Party(
        party.name, party.street, party.additionalAddress, party.zip, party.location,
        mapCountry(party.country, if (isSupplier) InvoiceField.SupplierCountry else InvoiceField.CustomerCountry, dataErrors),
        party.vatID, party.email ?: party.contact?.eMail, party.contact?.phone, party.contact?.fax, party.contact?.name,
        party.bankDetails?.firstOrNull()?.let { net.codinux.invoicing.model.BankDetails(it.iban, it.bic, it.accountName) }
    )

    open fun mapInvoiceItem(item: IZUGFeRDExportableItem, dataErrors: MutableList<InvoiceDataError>) = InvoiceItem(
        item.product.name, item.quantity.toEInvoicingBigDecimal(), mapUnit(item.product.unit, dataErrors),
        item.price.toEInvoicingBigDecimal(), item.product.vatPercent.toEInvoicingBigDecimal(), item.product.sellerAssignedID, item.product.description.takeUnless { it.isBlank() }
    )

    protected open fun mapAmountAdjustments(invoice: Invoice): AmountAdjustments? {
        if ((invoice.totalPrepaidAmount == null || invoice.totalPrepaidAmount == BigDecimal.ZERO)
            && invoice.zfCharges.isNullOrEmpty() && invoice.zfAllowances.isNullOrEmpty()) {
            return null
        }

        return AmountAdjustments(
            invoice.totalPrepaidAmount.toEInvoicingBigDecimal(),
            invoice.zfCharges.orEmpty().mapNotNull { mapChargeOrAllowance(it as? Charge) },
            invoice.zfAllowances.orEmpty().mapNotNull { mapChargeOrAllowance(it as? Allowance ?: it as? Charge) }
        )
    }

    private fun mapChargeOrAllowance(chargeOrAllowance: Charge?) = chargeOrAllowance?.let {
        ChargeOrAllowance(it.totalAmount.toEInvoicingBigDecimal(), null, null, it.percent.toEInvoicingBigDecimal(),
            it.reason, it.reasonCode, it.taxPercent.toEInvoicingBigDecimal(), it.categoryCode)
    }


    private fun mapCountry(isoAlpha2CountryCode: String?, invoiceField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): Country {
        if (isoAlpha2CountryCode.isNullOrBlank()) {
            dataErrors.add(InvoiceDataError(invoiceField, InvoiceDataErrorType.ValueNotSet, isoAlpha2CountryCode))
            return CountryFallbackValue
        }

        val country = CountriesByIsoCode[isoAlpha2CountryCode.uppercase()]
        if (country != null && isoAlpha2CountryCode.any { it.isUpperCase() == false }) {
            dataErrors.add(InvoiceDataError(invoiceField, InvoiceDataErrorType.ValueNotUpperCase, isoAlpha2CountryCode))
        } else if (country == null) {
            log.warn { "Unknown ISO Alpha-2 country code \"$isoAlpha2CountryCode\", therefore cannot map ISO code to Country" }
            dataErrors.add(InvoiceDataError(invoiceField, InvoiceDataErrorType.ValueIsInvalid, isoAlpha2CountryCode))
        }

        return country ?: CountryFallbackValue
    }

    private fun mapCurrency(isoCurrencyCode: String?, dataErrors: MutableList<InvoiceDataError>): Currency {
        if (isoCurrencyCode.isNullOrBlank()) {
            dataErrors.add(InvoiceDataError(InvoiceField.Currency, InvoiceDataErrorType.ValueNotSet, isoCurrencyCode))
            return CurrencyFallbackValue
        }

        val currency = CurrenciesByIsoCode[isoCurrencyCode.uppercase()]
        if (currency != null && isoCurrencyCode.any { it.isUpperCase() == false }) {
            dataErrors.add(InvoiceDataError(InvoiceField.Currency, InvoiceDataErrorType.ValueNotUpperCase, isoCurrencyCode))
        } else if (currency == null) {
            log.warn { "Unknown ISO currency code \"$isoCurrencyCode\", therefore cannot map ISO code to Currency" }
            dataErrors.add(InvoiceDataError(InvoiceField.Currency, InvoiceDataErrorType.ValueIsInvalid, isoCurrencyCode))
        }

        return currency ?: CurrencyFallbackValue
    }

    private fun mapUnit(unitUnCefactCode: String?, dataErrors: MutableList<InvoiceDataError>): UnitOfMeasure {
        if (unitUnCefactCode.isNullOrBlank()) {
            dataErrors.add(InvoiceDataError(InvoiceField.ItemUnit, InvoiceDataErrorType.ValueNotSet, unitUnCefactCode))
            return UnitFallbackValue
        }

        val unit = UnitsByCode[unitUnCefactCode.uppercase()]
        if (unit != null && unitUnCefactCode != unitUnCefactCode.uppercase()) {
            dataErrors.add(InvoiceDataError(InvoiceField.ItemUnit, InvoiceDataErrorType.ValueNotUpperCase, unitUnCefactCode))
        } else if (unit == null) {
            log.warn { "Unknown UN/CEFACT unit of measurement code \"$unitUnCefactCode\", therefore cannot map code to UnitOfMeasurement" }
            dataErrors.add(InvoiceDataError(InvoiceField.ItemUnit, InvoiceDataErrorType.ValueIsInvalid, unitUnCefactCode))
        }

        return unit ?: UnitFallbackValue
    }

    @JvmName("mapNullable")
    protected fun map(date: LocalDate?) =
        date?.let { map(it) }

    protected open fun map(date: LocalDate): Date =
        Date.from(mapToInstant(date))

    protected open fun mapToInstant(date: LocalDate): Instant =
        date.toJvmInstantAtSystemDefaultZone()

    @JvmName("mapNullable")
    protected fun map(date: Date?) =
        date?.let { map(it) }

    protected open fun map(date: Date): LocalDate =
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toEInvoicingDate()

}
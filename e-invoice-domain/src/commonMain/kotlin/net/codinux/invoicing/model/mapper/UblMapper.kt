package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult.Companion.isNotMinimumOrBasicWLProfile
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.Party
import net.codinux.invoicing.model.codes.ElectronicAddressSchemeIdentifier
import net.codinux.invoicing.model.codes.UnitOfMeasure
import net.codinux.invoicing.model.codes.VatCategoryCode
import net.codinux.invoicing.model.mapper.MapperConstants.BigDecimalFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.LocalDateFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.TextFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.UnitFallbackValue
import net.codinux.invoicing.model.ubl.*
import net.codinux.invoicing.reader.ReadEInvoiceXmlResult
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType
import net.codinux.log.logger

open class UblMapper {

    companion object {
        private const val NewLine = "\r\n"
    }

    private val log by logger()


    open fun map(invoice: UblInvoice, format: EInvoiceFormatDetectionResult?): ReadEInvoiceXmlResult {
        try {
            val dataErrors = checkCommonDataErrors(invoice, format)

            return ReadEInvoiceXmlResult(
                if (dataErrors.isEmpty()) ReadEInvoiceXmlResultType.Success else ReadEInvoiceXmlResultType.InvalidInvoiceData,
                MapInvoiceResult(mapInvoice(invoice, dataErrors), dataErrors), format
            )
        } catch (e: Throwable) {
            log.error(e) { "Could not read UBL invoice: $invoice" }
            return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, e)
        }
    }

    protected open fun mapInvoice(invoice: UblInvoice, dataErrors: MutableList<InvoiceDataError>) = Invoice(
        details = mapInvoiceDetails(invoice, dataErrors),

        supplier = mapParty(invoice.accountingSupplierParty?.party, invoice.paymentMeans.firstNotNullOfOrNull { it.payeeFinancialAccount }),
        customer = mapParty(invoice.accountingCustomerParty?.party, invoice.paymentMeans.firstNotNullOfOrNull { it.payerFinancialAccount }),
        items = invoice.invoiceLine.map { mapLineItem(it, dataErrors) },

        customerReferenceNumber = mapNullableText(invoice.buyerReference),

        amountAdjustments = mapAmountAdjustments(invoice),
        totals = mapTotalAmounts(invoice, dataErrors)
    )

    protected open fun mapInvoiceDetails(invoice: UblInvoice, dataErrors: MutableList<InvoiceDataError>) = InvoiceDetails(
        invoiceNumber = invoice.id?.value ?: TextFallbackValue,
        invoiceDate = mapNullableDate(invoice.issueDate),
        // TODO: map InvoiceTypeCode
        currency = mapCurrency(invoice.documentCurrencyCode),
        serviceDate = mapServiceDate(invoice.invoicePeriod.firstOrNull()),
        dueDate = mapNullableDate(invoice.dueDate),
        paymentDescription = invoice.paymentTerms.flatMap { it.note.mapNotNull { mapNullableText(it) } }.joinToString(NewLine)
    )

    protected open fun mapServiceDate(period: Period?): ServiceDate? = period?.startDate?.let { startDate ->
        if (period.endDate != null) {
            ServiceDate.ServicePeriod(mapDate(startDate), mapDate(period.endDate))
        } else {
            ServiceDate.DeliveryDate(mapDate(startDate))
        }
    }

    protected open fun mapParty(party: net.codinux.invoicing.model.ubl.Party?, financialAccount: FinancialAccount? = null) =
        if (party == null) {
            Party(TextFallbackValue, TextFallbackValue, null, null, TextFallbackValue)
        } else {
            Party(
                name = if (party.partyName.isNotEmpty()) party.partyName.joinToString(NewLine) { mapText(it.name) } else party.partyLegalEntity.joinToString(NewLine) { mapText(it.registrationName) },
                address = party.postalAddress?.streetName?.value?.let { "$it${party.postalAddress.buildingNumber?.value?.let { " $it" } ?: ""}" }
                    ?: mapText(party.postalAddress?.addressLine?.firstOrNull()?.line),
                additionalAddressLine = mapNullableText(party.postalAddress?.additionalStreetName)
                    ?: party.postalAddress?.addressLine.orEmpty().drop(1).mapNotNull { mapNullableText(it.line) }.joinToString(NewLine),
                postalCode = mapNullableText(party.postalAddress?.postalZone),
                city = mapText(party.postalAddress?.cityName),
                country = mapCountry(party.postalAddress?.country),

                vatId = party.partyTaxScheme.firstOrNull { it.taxScheme?.id?.value == "VAT" }?.companyID?.value,

                email = party.endpointID?.takeIf { it.schemeID == ElectronicAddressSchemeIdentifier.EM.code }?.value
                    ?: mapNullableText(party.contact?.electronicMail),
                phone = mapNullableText(party.contact?.telephone),
                fax = mapNullableText(party.contact?.telefax),

                contactName = mapNullableText(party.contact?.name) ?: party.person.firstNotNullOfOrNull { mapNullableText(it.familyName) },

                bankDetails = mapBankDetails(party.financialAccount ?: financialAccount)
            )
        }

    protected open fun mapBankDetails(account: FinancialAccount?): BankDetails? =
        account?.let {
            account.id?.value?.let { accountId ->
                BankDetails(accountId, account.financialInstitutionBranch?.id?.value, mapNullableText(account.name), mapNullableText(account.financialInstitutionBranch?.name))
            }
        }

    protected open fun mapLineItem(line: InvoiceLine, dataErrors: MutableList<InvoiceDataError>) = InvoiceItem(
        // TODO: map ID (a mandatory UBL field)
        name = mapText(line.item?.name),
        // TODO: what's the difference between line.cbcInvoicedQuantity and line.price.baseQuantity
        quantity = mapQuantity(line.invoicedQuantity, InvoiceField.ItemQuantity, dataErrors),
        unit = mapUnit(line.invoicedQuantity, InvoiceField.ItemUnit, dataErrors),
        unitPrice = mapAmount(line.price?.priceAmount, InvoiceField.ItemUnitPrice, dataErrors),
        vatRate = mapVatRateOrDefault(line.item?.classifiedTaxCategory),

        description = line.item?.description?.joinToString(NewLine),
    )

    protected open fun mapAmountAdjustments(invoice: UblInvoice): AmountAdjustments? =
        if (invoice.allowanceCharge.isEmpty()) null
        else AmountAdjustments(
            prepaidAmounts = BigDecimal.Zero, // TODO: check if we keep this property here are use only that one on TotalAmounts
            allowances = mapChargeOrAllowances(invoice.allowanceCharge.filter { it.chargeIndicator == false })
        )

    protected open fun mapChargeOrAllowances(chargeOrAllowance: List<AllowanceCharge>) =
        chargeOrAllowance.map { mapChargeOrAllowance(it) }

    protected open fun mapChargeOrAllowance(chargeOrAllowance: AllowanceCharge) = ChargeOrAllowance(
        actualAmount = mapAmountOrZero(chargeOrAllowance.amount),
        basisAmount = mapNullableAmount(chargeOrAllowance.baseAmount),
        basisQuantity = mapNullableAmount(chargeOrAllowance.perUnitAmount),
        calculationPercent = chargeOrAllowance.multiplierFactorNumeric?.value, // TODO: is this correct?
        reason = chargeOrAllowance.allowanceChargeReason.joinToString(NewLine) { mapText(it) },
        reasonCode = chargeOrAllowance.allowanceChargeReasonCode?.value,
        taxRateApplicablePercent = chargeOrAllowance.taxCategory.firstOrNull { it.taxScheme?.id?.value == "VAT" }?.percent?.value
            ?: chargeOrAllowance.taxCategory.firstOrNull()?.percent?.value,
        taxCategoryCode = chargeOrAllowance.taxCategory.firstOrNull { it.taxScheme?.id?.value == "VAT" }?.id?.value
            ?: chargeOrAllowance.taxCategory.firstOrNull()?.id?.value
        // TODO: there's also taxableAmount and taxAmount
    )

    protected open fun mapTotalAmounts(invoice: UblInvoice, dataErrors: MutableList<InvoiceDataError>): TotalAmounts? = invoice.legalMonetaryTotal?.let { total ->
        val taxBasisTotalAmount = mapAmount(total.taxExclusiveAmount, InvoiceField.TaxBasisTotalAmount, dataErrors)
        val grandTotalAmount = mapAmount(total.taxInclusiveAmount, InvoiceField.GrandTotalAmount, dataErrors)

        TotalAmounts(
            lineTotalAmount = mapAmount(total.lineExtensionAmount, InvoiceField.LineTotalAmount, dataErrors),
            chargeTotalAmount = mapAmountOrZero(total.chargeTotalAmount),
            allowanceTotalAmount = mapAmountOrZero(total.allowanceTotalAmount),
            taxBasisTotalAmount = taxBasisTotalAmount,
            taxTotalAmount = mapNullableAmount(invoice.taxTotal.firstNotNullOfOrNull { it.taxAmount }) // TODO: is this correct
                ?: (grandTotalAmount - taxBasisTotalAmount), // TODO: should we do this?
            grandTotalAmount = grandTotalAmount,
            totalPrepaidAmount = mapAmountOrZero(total.prepaidAmount),
            duePayableAmount = mapAmount(total.payableAmount, InvoiceField.DuePayableAmount, dataErrors),
            // TODO: there's also payableRoundingAmount and payableAlternativeAmount
        )
    }


    protected open fun checkCommonDataErrors(invoice: UblInvoice, format: EInvoiceFormatDetectionResult?) = mutableListOf<InvoiceDataError>().apply {
        if (invoice.id?.value.isNullOrBlank()) {
            add(InvoiceDataError.missing(InvoiceField.InvoiceNumber))
        }
        if (invoice.issueDate?.isoDateString.isNullOrBlank()) {
            add(InvoiceDataError.missing(InvoiceField.InvoiceDate))
        }
        if (invoice.documentCurrencyCode?.value.isNullOrBlank()) {
            add(InvoiceDataError.missing(InvoiceField.Currency))
        }

        val supplier = invoice.accountingSupplierParty?.party
        if (supplier == null) {
            add(InvoiceDataError.missing(InvoiceField.Supplier))
        } else {
            if (nameNotSet(supplier)) {
                add(InvoiceDataError.missing(InvoiceField.SupplierName))
            }
            if (supplier.postalAddress?.country?.identificationCode?.value.isNullOrBlank()) {
                add(InvoiceDataError.missing(InvoiceField.SupplierCountry))
            }
        }

        val customer = invoice.accountingCustomerParty?.party
        if (customer == null) {
            add(InvoiceDataError.missing(InvoiceField.Customer))
        } else {
            if (nameNotSet(customer)) {
                add(InvoiceDataError.missing(InvoiceField.CustomerName))
            }
            if (customer.postalAddress?.country?.identificationCode?.value.isNullOrBlank()) {
                add(InvoiceDataError.missing(InvoiceField.CustomerCountry))
            }
        }

        if (invoice.invoiceLine.isEmpty() && format.isNotMinimumOrBasicWLProfile) {
            add(InvoiceDataError.missing(InvoiceField.Items))
        }
    }

    protected open fun nameNotSet(party: net.codinux.invoicing.model.ubl.Party): Boolean =
        (party.partyName.isEmpty() || party.partyName.joinToString("") { mapText(it.name) }.isBlank())
                && (party.partyLegalEntity.isEmpty() || party.partyLegalEntity.joinToString("") { mapText(it.registrationName) }.isBlank())


    protected open fun mapVatRateOrDefault(tradeTax: List<TaxCategory>?): BigDecimal =
        tradeTax?.let { mapVatRate(it) } ?: BigDecimal.Zero

    protected open fun mapVatRate(tradeTax: List<TaxCategory>): BigDecimal =
        tradeTax.firstOrNull { it.taxScheme?.id?.value == "VAT" && it.id?.value == VatCategoryCode.S.code && it.percent != null }
            ?.percent?.value ?: BigDecimalFallbackValue

    protected open fun mapNullableAmount(amount: Amount?): BigDecimal? =
        amount?.value

    protected open fun mapAmountOrZero(amount: Amount?): BigDecimal =
        mapNullableAmount(amount) ?: BigDecimalFallbackValue

    protected open fun mapAmount(amount: Amount?, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        if (amount?.value != null) {
            amount.value
        } else {
            dataErrors.add(InvoiceDataError.missing(amountField))
            BigDecimalFallbackValue
        }

    protected open fun mapNullableQuantity(quantity: Quantity?) = quantity?.value

    protected open fun mapQuantity(quantity: Quantity?, quantityField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        mapNullableQuantity(quantity) ?: run {
            dataErrors.add(InvoiceDataError.missing(quantityField))
            BigDecimalFallbackValue
        }

    protected open fun mapUnit(quantity: Quantity?, unitField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): UnitOfMeasure =
        quantity?.unitCode?.let { unitCode -> UnitOfMeasure.entries.firstOrNull { it.code == unitCode } } ?: run {
            dataErrors.add(InvoiceDataError.missing(unitField))
            UnitFallbackValue
        }

    protected open fun mapCurrency(currency: Code?) = MapperConstants.mapCurrency(currency?.value)

    protected open fun mapCountry(country: Country?) = MapperConstants.mapCountry(country?.identificationCode?.value)


    protected open fun mapNullableDate(date: Date?) =
        date?.let { mapDate(it) } ?: LocalDateFallbackValue

    protected open fun mapDate(date: Date): LocalDate =
        if (date.isoDateString == null) LocalDateFallbackValue
        else {
            val parts = date.isoDateString.split('-')
            if (parts.size == 3) LocalDate(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
            else LocalDateFallbackValue
        }

    protected open fun mapNullableText(text: Text?): String? =
        text?.value

    protected open fun mapText(text: Text?): String =
        mapNullableText(text) ?: TextFallbackValue

}
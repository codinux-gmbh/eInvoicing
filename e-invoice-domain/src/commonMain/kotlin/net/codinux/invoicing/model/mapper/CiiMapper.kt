package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult.Companion.isMinimumOrBasicWLProfile
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult.Companion.isNotMinimumOrBasicWLProfile
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.cii.lenient.*
import net.codinux.invoicing.model.codes.*
import net.codinux.invoicing.model.mapper.MapperConstants.BigDecimalFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.CodeFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.CountryFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.CurrencyFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.IdFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.LocalDateFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.TextFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.UnitFallbackValue
import net.codinux.invoicing.reader.ReadEInvoiceXmlResult
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType
import net.codinux.log.logger

open class CiiMapper {

    companion object {
        const val Iso8601DateFormatCode = "102" // YYYYMMDD
        const val Iso8601TimeFormatCode = "303" // HHMM
        const val Iso8601DateTimeFormatCode = "203" // YYYYMMDDHHMM
    }


    private val log by logger()

    open fun map(ciiInvoice: CrossIndustryInvoice, profile: EInvoiceFormatDetectionResult?): ReadEInvoiceXmlResult {
        try {
            val dataErrors = mutableListOf<InvoiceDataError>()
            val exchangedDocument = ciiInvoice.exchangedDocument
            val tradeTransaction = ciiInvoice.supplyChainTradeTransaction
            if (exchangedDocument == null) { // TODO: try to map tradeTransaction
                return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, null as? Throwable)
            }
            if (tradeTransaction == null) { // TODO: try to map exchangedDocument
                return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, null as? Throwable)
            }

            val tradeAgreement = tradeTransaction.applicableHeaderTradeAgreement
            if (tradeAgreement == null) { // TODO: try to map tradeTransaction and exchangedDocument
                return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, null as? Throwable)
            }
            val tradeSettlement = tradeTransaction.applicableHeaderTradeSettlement
            if (tradeSettlement == null) { // TODO: try to map tradeTransaction and exchangedDocument
                return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, null as? Throwable)
            }

            val invoice = map(ciiInvoice, profile, dataErrors, exchangedDocument, tradeTransaction, tradeAgreement, tradeSettlement)

            return ReadEInvoiceXmlResult(
                if (dataErrors.isEmpty()) ReadEInvoiceXmlResultType.Success else ReadEInvoiceXmlResultType.InvalidInvoiceData,
                MapInvoiceResult(invoice, dataErrors), profile
            )
        } catch (e: Throwable) {
            log.error(e) { "Could not read CII invoice" }
            return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, e)
        }
    }

    protected open fun map(ciiInvoice: CrossIndustryInvoice, profile: EInvoiceFormatDetectionResult?, dataErrors: MutableList<InvoiceDataError>,
                           exchangedDocument: ExchangedDocument, tradeTransaction: SupplyChainTradeTransaction,
                           tradeAgreement: HeaderTradeAgreement, tradeSettlement: HeaderTradeSettlement): Invoice {

        val delivery = tradeTransaction.applicableHeaderTradeDelivery

        return Invoice(
            details = mapInvoiceDetails(exchangedDocument, tradeSettlement, delivery, dataErrors),

            supplier = mapParty(tradeAgreement.sellerTradeParty, true, profile, dataErrors, mapBankDetails(tradeSettlement)),
            customer = mapParty(tradeAgreement.buyerTradeParty, false, profile, dataErrors),
            items = (tradeTransaction.includedSupplyChainTradeLineItem.map { mapInvoiceItem(it, dataErrors) }).also {
                if (it.isEmpty() && profile.isNotMinimumOrBasicWLProfile) {
                    dataErrors.add(InvoiceDataError.missing(InvoiceField.Items))
                }
            },

            customerReferenceNumber = tradeAgreement.buyerReference?.value,

            // ApplicableTradeTax: Eine Gruppe von betriebswirtschaftlichen Begriffen, die Informationen über die Umsatzsteueraufschlüsselung in
            // verschiedene Kategorien, Sätze und Befreiungsgründe enthält
            amountAdjustments = mapAmountAdjustments(tradeSettlement),

            totals = mapTotalAmounts(tradeSettlement.specifiedTradeSettlementHeaderMonetarySummation, profile, dataErrors)
        )
    }

    protected open fun mapInvoiceDetails(
        exchangedDocument: ExchangedDocument,
        tradeSettlement: HeaderTradeSettlement,
        delivery: HeaderTradeDelivery?,
        dataErrors: MutableList<InvoiceDataError>
    ): InvoiceDetails {
        val paymentTerms = tradeSettlement.specifiedTradePaymentTerms.firstOrNull()

        // deliveryDate oder billingSpecifiedPeriod sind in Deutschland auf Dokumentenebene verpflichtend anzugeben
        val deliveryDate = delivery?.actualDeliverySupplyChainEvent?.occurrenceDateTime // im Extended Profile kann es optional noch auf LineItem Ebene angegeben werden
        val (startDate, endDate) = tradeSettlement.billingSpecifiedPeriod?.let { it.startDateTime to it.endDateTime } ?: (null to null) // kann optional noch auf LineItem Ebene angegeben werden
        val serviceDate = if (deliveryDate != null) ServiceDate.DeliveryDate(mapDate(deliveryDate))
                        else if (startDate != null && endDate != null) ServiceDate.ServicePeriod(mapDate(startDate), mapDate(endDate))
                        else null

        return InvoiceDetails(
            mapId(exchangedDocument.id, InvoiceField.InvoiceNumber, dataErrors),
            mapDate(exchangedDocument.issueDateTime, InvoiceField.InvoiceDate, dataErrors),
            mapCurrency(tradeSettlement.invoiceCurrencyCode, dataErrors),
            serviceDate,
            mapDateOrNull(/*invoice.dueDate ?:*/ paymentTerms?.dueDateDateTime), mapNullableText(/*invoice.paymentTermDescription ?:*/ paymentTerms?.description)
        )
    }

    protected open fun mapParty(party: TradeParty?, isSeller: Boolean, profile: EInvoiceFormatDetectionResult?, dataErrors: MutableList<InvoiceDataError>, bankDetails: BankDetails? = null): Party =
        if (party == null) {
            dataErrors.add(InvoiceDataError.missing(if (isSeller) InvoiceField.Supplier else InvoiceField.Customer))
            Party(TextFallbackValue, TextFallbackValue, null, null, TextFallbackValue)
        } else {
            // according to XSD the TradeParty object has no mandatory field - even in Extended profile. Only the Technical Appendix says that name is required
            if (party.name?.value == null) {
                dataErrors.add(InvoiceDataError.missing(if (isSeller) InvoiceField.SupplierName else InvoiceField.CustomerName))
            }
            val address = party.postalTradeAddress
            // countryId is the only mandatory field of address. And only in Factur-X, neither in CII nor in XRechnung
            if (profile?.format == EInvoiceFormat.FacturX && address != null && address.countryID?.value == null) {
                dataErrors.add(InvoiceDataError.missing(if (isSeller) InvoiceField.SupplierCountry else InvoiceField.CustomerCountry))
            }

            Party(
                map(party.name),
                // streetName is only in CII and XRechnung, not in Factur-X, that uses lineOne
                map(address?.streetName ?: address?.lineOne), // lineOne = Usually the street name and number or post office box.
                // also additionalStreetName is only in CII and XRechnung, not in Factur-X, that uses lineTwo and lineThree
                map(address?.additionalStreetName ?: address?.lineTwo), // lineTwo & lineThree = An additional address line in an address that can be used to give further details supplementing the main line.
                mapNullable(address?.postcodeCode), map(address?.cityName),
                address?.countryID?.value?.let { code -> Country.entries.firstOrNull { it.alpha2Code == code || it.alpha3Code == code } } ?: CountryFallbackValue,

                party.specifiedTaxRegistration.mapNotNull { it.id }.firstOrNull { it.schemeID == ReferenceType.VA.code }?.value,

                party.uRIUniversalCommunication.firstOrNull { it.channelCode?.value == ElectronicAddressSchemeIdentifier.EM.code }?.completeNumber?.value
                    ?: party.definedTradeContact.firstNotNullOfOrNull { it.emailURIUniversalCommunication?.uriid?.value },
                party.definedTradeContact.firstNotNullOfOrNull { it.mobileTelephoneUniversalCommunication?.completeNumber?.value }
                    ?: party.definedTradeContact.firstNotNullOfOrNull { it.telephoneUniversalCommunication?.completeNumber?.value },
                party.definedTradeContact.firstNotNullOfOrNull { it.faxUniversalCommunication?.completeNumber?.value },
                null, // TODO: map contact name

                bankDetails
            )
        }

    protected open fun mapBankDetails(settlement: HeaderTradeSettlement): BankDetails? =
        settlement.specifiedTradeSettlementPaymentMeans.let { paymentMeans ->
            // TODO: der paymentMeans.PaymentMeansCodeType gibt an, um welche Art von Zahlungsmethode es sich handelt wie Bank card, Direct debit, SEPA direct debit, ...
            // even in Extended profile there's only at max one payeePartyCreditorFinancialAccount and payerPartyDebtorFinancialAccount
            // (but there can be of course multiple specifiedTradeSettlementPaymentMeans)
            // payerSpecifiedDebtorFinancialInstitution does not exist in Factur-X
            paymentMeans.firstNotNullOfOrNull { it.payeePartyCreditorFinancialAccount.firstOrNull { it.ibanid?.value != null } }?.let { account ->
                val financialInstitution = paymentMeans.firstNotNullOfOrNull { it.payeeSpecifiedCreditorFinancialInstitution }
                // Nutzen Sie die IBANID bei SEPA-Zahlungen, sonst die ProprietaryID - but in Factur-X there is no ProprietaryID
                val bic = financialInstitution?.bicid?.value
                val bankName = financialInstitution?.name?.value
                BankDetails(account.ibanid!!.value!!, bic, mapNullable(account.accountName), bankName)
            }
        }

    protected open fun mapInvoiceItem(item: SupplyChainTradeLineItem, dataErrors: MutableList<InvoiceDataError>): InvoiceItem {
        val document = item.associatedDocumentLineDocument
        val product = item.specifiedTradeProduct
        val agreement = item.specifiedLineTradeAgreement
        val delivery = item.specifiedLineTradeDelivery
        val itemSettlement = item.specifiedLineTradeSettlement

        // gross: Der Einheitspreis ohne Umsatzsteuer vor Abzug des Nachlass auf den Artikelpreis
        // net: Der Preis eines Artikels ohne Umsatzsteuer nach Abzug des Nachlass auf den Artikelpreis
        val price = agreement?.grossPriceProductTradePrice ?: agreement?.netPriceProductTradePrice  // netPrice is mandatory, grossPrice optional
        // billedQuantity: Die Menge der in der betreffenden Zeile in Rechnung gestellten Einzelartikel (Waren oder Dienstleistungen) (both, specifiedLineTradeDelivery and billedQuantity, are mandatory)
        val quantity = delivery?.billedQuantity

        return InvoiceItem(
            mapText(product?.name, InvoiceField.ItemName, dataErrors),
            mapQuantity(quantity, InvoiceField.ItemQuantity, dataErrors),
            mapUnit(quantity, InvoiceField.ItemUnit, dataErrors),
            mapAmount(price?.chargeAmount, InvoiceField.ItemUnitPrice, dataErrors),
            mapVatRateOrDefault(itemSettlement?.applicableTradeTax),
            null,
            mapNullableText(product?.description),
            mapLineAmounts(itemSettlement, agreement)
        )
    }

    protected open fun mapLineAmounts(settlement: LineTradeSettlement?, agreement: LineTradeAgreement?): LineAmounts? =
        settlement?.let {
            val summation = settlement.specifiedTradeSettlementLineMonetarySummation
            val chargesAndAllowances = settlement.specifiedTradeAllowanceCharge

            val lineTotalAmount = mapAmountOrDefault(summation?.lineTotalAmount)

            if (summation == null && chargesAndAllowances.isEmpty()) {
                null
            } else {
                LineAmounts(
                    mapAmountOrDefault(agreement?.grossPriceProductTradePrice?.chargeAmount),
                    mapAmountOrDefault(agreement?.netPriceProductTradePrice?.chargeAmount),
                    mapItemChargeOrAllowances(chargesAndAllowances.filter { isCharge(it) }),
                    mapItemChargeOrAllowances(chargesAndAllowances.filter { isAllowance(it) }),
                    lineTotalAmount,
                    calculateLineTotalTaxAmount(settlement, lineTotalAmount)
                )
            }
        }

    protected open fun mapItemChargeOrAllowances(chargeOrAllowances: List<TradeAllowanceCharge>) =
        chargeOrAllowances.map { mapItemChargeOrAllowance(it) }

    protected open fun mapItemChargeOrAllowance(chargeOrAllowance: TradeAllowanceCharge) = ItemChargeOrAllowance(
        mapAmountOrDefault(chargeOrAllowance.actualAmount),
        mapNullableAmount(chargeOrAllowance.basisAmount),
        mapNullablePercent(chargeOrAllowance.calculationPercent),
        mapNullable(chargeOrAllowance.reason),
        chargeOrAllowance.reasonCode?.value
    )

    protected open fun calculateLineTotalTaxAmount(settlement: LineTradeSettlement, lineTotalAmount: BigDecimal): BigDecimal {
        val taxAmounts = settlement.applicableTradeTax.mapNotNull { tax ->
            mapNullableAmount(tax.calculatedAmount)
                ?: tax.rateApplicablePercent?.value?.let { lineTotalAmount.percent(it) }
        }

        return taxAmounts.sumOf { it }
    }


    protected open fun mapVatRateOrDefault(tradeTax: List<TradeTax>?): BigDecimal =
        tradeTax?.let { mapVatRate(it) } ?: BigDecimal.Zero

    protected open fun mapVatRate(tradeTax: List<TradeTax>): BigDecimal =
        tradeTax.firstOrNull { it.rateApplicablePercent?.value != null && (it.calculatedAmount.isEmpty() || it.calculatedAmount.any { it.value?.toPlainString()?.startsWith("-") == false }) }
            ?.rateApplicablePercent?.value ?: BigDecimalFallbackValue

    protected open fun mapText(texts: List<Text>?, invoiceField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): String =
        mapNullableText(texts) ?: run {
            dataErrors.add(InvoiceDataError.missing(invoiceField))
            TextFallbackValue
        }

    protected open fun mapText(texts: List<Text>?): String =
        mapNullableText(texts) ?: TextFallbackValue

    protected open fun mapNullableText(texts: List<Text>?): String? =
        texts?.firstOrNull()?.value

    protected open fun mapAmountAdjustments(settlement: HeaderTradeSettlement) = AmountAdjustments(
        mapAmountOrDefault(settlement.specifiedTradeSettlementHeaderMonetarySummation?.totalPrepaidAmount),
        mapChargeOrAllowances(settlement.specifiedTradeAllowanceCharge.filter { isCharge(it) }),
        mapChargeOrAllowances(settlement.specifiedTradeAllowanceCharge.filter { isAllowance(it) }),
    )

    protected open fun isCharge(chargeOrAllowance: TradeAllowanceCharge): Boolean =
        chargeOrAllowance.chargeIndicator?.indicator == true

    protected open fun isAllowance(chargeOrAllowance: TradeAllowanceCharge): Boolean =
        chargeOrAllowance.chargeIndicator?.indicator == false

    protected open fun mapChargeOrAllowances(chargeOrAllowances: List<TradeAllowanceCharge>) =
        chargeOrAllowances.map { mapChargeOrAllowance(it) }

    protected open fun mapChargeOrAllowance(chargeOrAllowance: TradeAllowanceCharge) = ChargeOrAllowance(
        mapAmountOrDefault(chargeOrAllowance.actualAmount),
        mapNullableAmount(chargeOrAllowance.basisAmount),
        mapNullableQuantity(chargeOrAllowance.basisQuantity),
        mapNullablePercent(chargeOrAllowance.calculationPercent),
        mapNullable(chargeOrAllowance.reason),
        chargeOrAllowance.reasonCode?.value,
        mapNullablePercent(chargeOrAllowance.categoryTradeTax.firstNotNullOfOrNull { it.rateApplicablePercent }),
        chargeOrAllowance.categoryTradeTax.firstNotNullOfOrNull { it.categoryCode?.value }
    )

    protected open fun mapTotalAmounts(summation: TradeSettlementHeaderMonetarySummation?, profile: EInvoiceFormatDetectionResult?, dataErrors: MutableList<InvoiceDataError>): TotalAmounts =
        if (summation == null) {
            dataErrors.add(InvoiceDataError.missing(InvoiceField.TotalAmount))
            TotalAmounts.Zero
        } else {

            // TaxBasisTotalAmount, GrandTotalAmount and DuePayableAmount have to be set. TaxTotalAmount may be not set
            TotalAmounts(
                if (profile.isMinimumOrBasicWLProfile) mapAmountOrDefault(summation.lineTotalAmount) // there are no line items in Minimum and BasicWL profile
                else mapAmount(summation.lineTotalAmount, InvoiceField.LineTotalAmount, dataErrors),
                mapAmountOrDefault(summation.chargeTotalAmount),
                mapAmountOrDefault(summation.allowanceTotalAmount),
                mapAmount(summation.taxBasisTotalAmount, InvoiceField.TaxBasisTotalAmount, dataErrors),
                mapAmountOrDefault(summation.taxTotalAmount), // TODO: there may be more than one taxTotalAmount
                mapAmount(summation.grandTotalAmount, InvoiceField.GrandTotalAmount, dataErrors),
                mapAmountOrDefault(summation.totalPrepaidAmount),
                mapAmount(summation.duePayableAmount, InvoiceField.TaxBasisTotalAmount, dataErrors),
            )
        }


    protected open fun mapNullableAmount(amounts: List<Amount>?): BigDecimal? =
        amounts?.firstOrNull()?.value

    protected open fun mapAmountOrDefault(amounts: List<Amount>?): BigDecimal =
        mapNullableAmount(amounts) ?: BigDecimalFallbackValue

    protected open fun mapAmount(amounts: List<Amount>?, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        if (amounts.isNullOrEmpty() || amounts.firstOrNull()?.value == null) {
            dataErrors.add(InvoiceDataError.missing(amountField))
            BigDecimalFallbackValue
        } else {
            amounts.first().value!!
        }

    protected open fun mapNullableAmount(amount: Amount?): BigDecimal? =
        amount?.value

    protected open fun mapNullablePercent(percent: Percent?): BigDecimal? =
        percent?.value

    protected open fun mapNullableQuantity(quantity: Quantity?): BigDecimal? = quantity?.value

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

    protected open fun mapCurrency(currencyCode: CurrencyCode?, dataErrors: MutableList<InvoiceDataError>): Currency =
        if (currencyCode == null || currencyCode.value == null) {
            dataErrors.add(InvoiceDataError.missing(InvoiceField.Currency))
            CurrencyFallbackValue
        } else {
            val code = currencyCode.value
            Currency.entries.firstOrNull { it.alpha3Code == code } ?: run {
                dataErrors.add(InvoiceDataError.invalid(InvoiceField.Currency, code))
                CurrencyFallbackValue
            }
        }


    protected open fun mapDate(dateTime: DateTime?, field: InvoiceField, dataErrors: MutableList<InvoiceDataError>): LocalDate =
        mapDateOrNull(dateTime) ?: run {
            dataErrors.add(InvoiceDataError.missing(field))
            LocalDateFallbackValue
        }

    protected open fun mapDate(dateTime: DateTime?): LocalDate =
        mapDateOrNull(dateTime) ?: LocalDateFallbackValue

    protected open fun mapDateOrNull(dateTime: DateTime?): LocalDate? =
        dateTime?.dateTime?.let { LocalDate(it.year, it.month, it.dayOfMonth) }
            ?: dateTime?.dateTimeString?.let { mapDateOrNull(it.format, it.value) }

    protected open fun mapDateOrNull(format: String?, dateTimeString: String?): LocalDate? =
        dateTimeString?.let {
            when (format) {
                Iso8601DateFormatCode-> { // YYYYMMDD
                    LocalDate(dateTimeString.substring(0, 4).toInt(), dateTimeString.substring(4, 6).toInt(), dateTimeString.substring(6).toInt())
                }
                // "203" == YYYYMMDDHHMM
                // "303" == HHMM
                // assume an ISO 8601 date = yyyy-MM-dd
                // else -> LocalDate.parse(dateTimeString)
                else -> dateTimeString.split('-').let { parts ->
                    if (parts.size == 3) LocalDate(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
                    else null
                }
            }
        }

    protected open fun map(text: Text?): String =
        mapNullable(text) ?: TextFallbackValue

    protected open fun mapNullable(text: Text?): String? =
        text?.value

    protected open fun map(code: Code?): String =
        mapNullable(code) ?: CodeFallbackValue

    protected open fun mapNullable(code: Code?): String? =
        code?.value

    protected open fun mapIds(ids: List<ID>): List<String> =
        ids.map { mapId(it) }

    protected open fun mapIdOrNull(ids: List<ID>?): String? =
        ids?.firstNotNullOfOrNull { mapIdOrNull(it) }

    protected open fun mapId(id: ID?, field: InvoiceField, dataErrors: MutableList<InvoiceDataError>): String =
        mapIdOrNull(id) ?: run {
            dataErrors.add(InvoiceDataError.missing(field))
            IdFallbackValue
        }

    protected open fun mapId(id: ID?): String =
        mapIdOrNull(id) ?: IdFallbackValue

    protected open fun mapIdOrNull(id: ID?): String? =
        id?.value
}
package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.cii.lenient.*
import net.codinux.invoicing.model.codes.*
import net.codinux.invoicing.reader.ReadEInvoiceXmlResult
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType
import net.codinux.log.logger

open class CiiMapper {

    companion object {
        val IdFallbackValue = ""

        val CodeFallbackValue = ""

        val TextFallbackValue = ""

        val BigDecimalFallbackValue = BigDecimal.Zero

        val LocalDateFallbackValue = LocalDate(0, 1, 1)

        val CurrencyFallbackValue = Currency.TheCodesAssignedForTransactionsWhereNoCurrencyIsInvolved

        val CountryFallbackValue = Country.UnknownCountry

        val UnitFallbackValue = UnitOfMeasure.ZZ
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
                MapInvoiceResult(invoice, dataErrors)
            )
        } catch (e: Throwable) {
            log.error(e) { "Could not read CII invoice" }
            return ReadEInvoiceXmlResult(ReadEInvoiceXmlResultType.InvalidXml, e)
        }
    }

    protected open fun map(ciiInvoice: CrossIndustryInvoice, profile: EInvoiceFormatDetectionResult?, dataErrors: MutableList<InvoiceDataError>,
                           exchangedDocument: ExchangedDocument, tradeTransaction: SupplyChainTradeTransaction,
                           tradeAgreement: HeaderTradeAgreement, tradeSettlement: HeaderTradeSettlement): Invoice {

        return Invoice(
            details = mapInvoiceDetails(exchangedDocument, tradeSettlement, dataErrors),

            supplier = mapParty(tradeAgreement.sellerTradeParty, true, profile, dataErrors, mapBankDetails(tradeSettlement)),
            customer = mapParty(tradeAgreement.buyerTradeParty, false, profile, dataErrors),
            items = (tradeTransaction.includedSupplyChainTradeLineItem.mapNotNull { mapInvoiceItem(it, tradeSettlement, dataErrors) }).also {
                if (it.isEmpty() && profile?.profile != FacturXProfile.Minimum && profile?.profile != FacturXProfile.BasicWL) {
                    dataErrors.add(InvoiceDataError.missing(InvoiceField.Items))
                }
            },

            customerReferenceNumber = tradeAgreement.buyerReference?.value,

//                amountAdjustments = mapAmountAdjustments(invoice),

                totals = mapTotalAmounts(tradeSettlement.specifiedTradeSettlementHeaderMonetarySummation, dataErrors)
        )
    }

    protected open fun mapInvoiceDetails(
        exchangedDocument: ExchangedDocument,
        tradeSettlement: HeaderTradeSettlement,
        dataErrors: MutableList<InvoiceDataError>
    ): InvoiceDetails {
        val paymentTerms = tradeSettlement.specifiedTradePaymentTerms.firstOrNull()

        return InvoiceDetails(
            mapId(exchangedDocument.id, InvoiceField.InvoiceNumber, dataErrors),
            mapDate(exchangedDocument.issueDateTime, InvoiceField.InvoiceDate, dataErrors),
            mapCurrency(tradeSettlement.invoiceCurrencyCode, dataErrors),
            mapDateOrNull(/*invoice.dueDate ?:*/ paymentTerms?.dueDateDateTime), mapNullableText(/*invoice.paymentTermDescription ?:*/ paymentTerms?.description)
        )
    }

    protected open fun mapParty(party: TradeParty?, isSeller: Boolean, profile: EInvoiceFormatDetectionResult?, dataErrors: MutableList<InvoiceDataError>, bankDetails: BankDetails? = null): Party =
        if (party == null) {
            dataErrors.add(InvoiceDataError.missing(if (isSeller) InvoiceField.Supplier else InvoiceField.Customer))
            Party(TextFallbackValue, TextFallbackValue, null, null, TextFallbackValue)
        } else {
            // according to XSD the TradeParty object has no mandatory field - even in Extended profile. Only the Technical Appendix says that name is required
            val address = party.postalTradeAddress
            // countryId is the only mandatory field of address. And only in Factur-X, neither in CII nor in XRechnung
            if (profile?.format == EInvoiceFormat.FacturX && address != null && address.countryID?.value == null) {
                dataErrors.add(InvoiceDataError.missing(if (isSeller) InvoiceField.SupplierCountry else InvoiceField.CustomerCountry))
            }

            Party(
                map(party.name), // that's curious, the XSD says name is optional, the Technical Appendix says it's mandatory
                // streetName is only in CII and XRechnung, not in Factur-X, that uses lineOne
                map(address?.streetName ?: address?.lineOne), // lineOne = Usually the street name and number or post office box.
                // also additionalStreetName is only in CII and XRechnung, not in Factur-X, that uses lineTwo and lineThree
                map(address?.additionalStreetName ?: address?.lineTwo), // lineTwo & lineThree = An additional address line in an address that can be used to give further details supplementing the main line.
                mapNullable(address?.postcodeCode), map(address?.cityName),
                address?.countryID?.value?.let { code -> Country.entries.firstOrNull { it.alpha2Code == code || it.alpha3Code == code } } ?: CountryFallbackValue,

                party.specifiedTaxRegistration.mapNotNull { it.id }.firstOrNull { it.schemeID == "VA" }?.value,

                party.uRIUniversalCommunication.firstOrNull { it.channelCode?.value == "EM" }?.completeNumber?.value
                    ?: party.definedTradeContact.firstNotNullOfOrNull { it.emailURIUniversalCommunication?.uriid?.value },
                party.definedTradeContact.firstNotNullOfOrNull { it.mobileTelephoneUniversalCommunication?.completeNumber?.value }
                    ?: party.definedTradeContact.firstNotNullOfOrNull { it.telephoneUniversalCommunication?.completeNumber?.value },
                party.definedTradeContact.firstNotNullOfOrNull { it.faxUniversalCommunication?.completeNumber?.value },
                null, // TODO: map contact name

                bankDetails
            )
        }

    protected open fun mapBankDetails(settlement: HeaderTradeSettlement): BankDetails? =
        // TODO: der paymentMeans.PaymentMeansCodeType gibt an, um welche Art von Zahlungsmethode es sich handelt wie Bank card, Direct debit, SEPA direct debit, ...
        // even in Extended profile there's only at max one payeePartyCreditorFinancialAccount and payerPartyDebtorFinancialAccount
        // (but there can be of course multiple specifiedTradeSettlementPaymentMeans)
        // payerSpecifiedDebtorFinancialInstitution does not exist in Factur-X
        settlement.specifiedTradeSettlementPaymentMeans.firstNotNullOfOrNull { it.payeePartyCreditorFinancialAccount.firstOrNull { it.ibanid?.value != null } }?.let { account ->
            // Nutzen Sie die IBANID bei SEPA-Zahlungen, sonst die ProprietaryID - but in Factur-X there is no ProprietaryID
            val bic = settlement.specifiedTradeSettlementPaymentMeans.firstNotNullOfOrNull { it.payeeSpecifiedCreditorFinancialInstitution }?.let { it.bicid }?.value
            BankDetails(account.ibanid!!.value!!, bic, mapNullable(account.accountName)) // TODO: where's the bank name?
        }

    protected open fun mapInvoiceItem(item: SupplyChainTradeLineItem, tradeSettlement: HeaderTradeSettlement, dataErrors: MutableList<InvoiceDataError>): InvoiceItem? =
        item.specifiedLineTradeAgreement?.let { agreement ->
            // gross: Der Einheitspreis ohne Umsatzsteuer vor Abzug des Nachlass auf den Artikelpreis
            // net: Der Preis eines Artikels ohne Umsatzsteuer nach Abzug des Nachlass auf den Artikelpreis
            (agreement.grossPriceProductTradePrice ?: agreement.netPriceProductTradePrice)?.let { price -> // netPrice is mandatory, grossPrice optional
                // billedQuantity: Die Menge der in der betreffenden Zeile in Rechnung gestellten Einzelartikel (Waren oder Dienstleistungen) (both, specifiedLineTradeDelivery and billedQuantity, are mandatory)
                item.specifiedLineTradeDelivery?.billedQuantity?.let { quantity ->
                    InvoiceItem(
                        mapText(item.specifiedTradeProduct?.name, InvoiceField.ItemName, dataErrors),
                        mapQuantity(quantity, InvoiceField.ItemQuantity, dataErrors),
                        mapUnit(quantity, InvoiceField.ItemUnit, dataErrors),
                        mapAmount(price.chargeAmount, InvoiceField.ItemUnit, dataErrors),
                        mapVatRate(tradeSettlement.applicableTradeTax)
                    )
                }
            }
        }

    protected open fun mapVatRate(tradeTax: List<TradeTax>): BigDecimal =
        tradeTax.firstOrNull { it.rateApplicablePercent?.value != null && it.calculatedAmount.any { it.value?.toPlainString()?.startsWith("-") == false } }
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

    protected open fun mapTotalAmounts(summation: TradeSettlementHeaderMonetarySummation?, dataErrors: MutableList<InvoiceDataError>): TotalAmounts =
        if (summation == null) {
            dataErrors.add(InvoiceDataError.missing(InvoiceField.TotalAmount))
            TotalAmounts.Zero
        } else {

            // TaxBasisTotalAmount, GrandTotalAmount and DuePayableAmount have to be set. TaxTotalAmount may be not set
            TotalAmounts(
                BigDecimal.Zero, // TODO: sum line items
                BigDecimal.Zero, BigDecimal.Zero, // TODO: map allowances and charges
                mapAmount(summation.taxBasisTotalAmount, InvoiceField.TaxBasisTotalAmount, dataErrors),
                mapNullableAmount(summation.taxTotalAmount), // TODO: there may be more than one taxTotalAmount
                mapAmount(summation.grandTotalAmount, InvoiceField.GrandTotalAmount, dataErrors),
                BigDecimal.Zero, // TODO: get totalPrepaidAmount
                mapAmount(summation.duePayableAmount, InvoiceField.TaxBasisTotalAmount, dataErrors),
            )
        }


    protected open fun mapNullableAmount(amounts: List<Amount>): BigDecimal =
        amounts.firstOrNull()?.value ?: BigDecimalFallbackValue

    protected open fun mapAmount(amounts: List<Amount>, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        if (amounts.isEmpty() || amounts.firstOrNull()?.value == null) {
            dataErrors.add(InvoiceDataError.missing(amountField))
            BigDecimalFallbackValue
        } else {
            amounts.first().value!!
        }

    protected open fun mapQuantity(quantity: Quantity?, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        quantity?.value ?: run {
            dataErrors.add(InvoiceDataError.missing(amountField))
            BigDecimalFallbackValue
        }

    protected open fun mapUnit(quantity: Quantity?, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): UnitOfMeasure =
        quantity?.unitCode?.let { unitCode -> UnitOfMeasure.entries.firstOrNull { it.code == unitCode } } ?: run {
            dataErrors.add(InvoiceDataError.missing(amountField))
            UnitFallbackValue
        }

    protected open fun mapCurrency(currencyCode: CurrencyCode?, dataErrors: MutableList<InvoiceDataError>): Currency =
        if (currencyCode == null || currencyCode.value == null) {
            dataErrors.add(InvoiceDataError.missing(InvoiceField.Currency))
            CurrencyFallbackValue
        } else {
            val code = currencyCode.value
            Currency.entries.first { it.alpha3Code == code }
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
                "102" -> { // YYYYMMDD
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
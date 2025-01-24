package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.cii.lenient.*
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure
import net.codinux.invoicing.reader.ReadEInvoiceXmlResult
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType
import net.codinux.log.logger

open class CiiMapper {

    private val log by logger()

    open fun map(ciiInvoice: CrossIndustryInvoice, profile: FacturXProfile?): ReadEInvoiceXmlResult {
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

    protected open fun map(ciiInvoice: CrossIndustryInvoice, profile: FacturXProfile?, dataErrors: MutableList<InvoiceDataError>,
                           exchangedDocument: ExchangedDocument, tradeTransaction: SupplyChainTradeTransaction,
                           tradeAgreement: HeaderTradeAgreement, tradeSettlement: HeaderTradeSettlement): Invoice {

        return Invoice(
            details = mapInvoiceDetails(exchangedDocument, tradeSettlement, dataErrors),

            supplier = mapParty(tradeAgreement.sellerTradeParty, true, profile, dataErrors),
            customer = mapParty(tradeAgreement.buyerTradeParty, false, profile, dataErrors),
            items = (tradeTransaction.includedSupplyChainTradeLineItem.mapNotNull { mapInvoiceItem(it, tradeSettlement, dataErrors) }).also {
                if (it.isEmpty() && profile != FacturXProfile.Minimum && profile != FacturXProfile.BasicWL) {
                    dataErrors.add(InvoiceDataError(InvoiceField.Items, InvoiceDataErrorType.ValueNotSet))
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
            map(exchangedDocument.id), map(tradeSettlement.invoiceDateTime), mapCurrency(tradeSettlement.invoiceCurrencyCode, dataErrors),
            map(/*invoice.dueDate ?:*/ paymentTerms?.dueDateDateTime), map(/*invoice.paymentTermDescription ?:*/ paymentTerms?.description?.firstOrNull())
        )
    }

    protected open fun mapParty(party: TradeParty?, isSeller: Boolean, profile: FacturXProfile?, dataErrors: MutableList<InvoiceDataError>): Party =
        if (party == null) {
            dataErrors.add(InvoiceDataError(if (isSeller) InvoiceField.Supplier else InvoiceField.Customer, InvoiceDataErrorType.ValueNotSet))
            Party("", "", null, null, "")
        } else {
            val address = party.postalTradeAddress
            val isAddressMandatory = isSeller || profile != FacturXProfile.Minimum // in the Minimum profile the buyer address may be null
            if (address == null && isAddressMandatory) {
                dataErrors.add(InvoiceDataError(if (isSeller) InvoiceField.SupplierAddress else InvoiceField.CustomerAddress, InvoiceDataErrorType.ValueNotSet))
            }
            if (address?.countryID?.value == null && isAddressMandatory) {
                dataErrors.add(InvoiceDataError(if (isSeller) InvoiceField.SupplierCountry else InvoiceField.CustomerCountry, InvoiceDataErrorType.ValueNotSet))
            }

            Party(map(party.name), map(address?.streetName), address?.additionalStreetName?.value, address?.postcodeCode?.value, map(address?.cityName))
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
            ?.rateApplicablePercent?.value ?: BigDecimal.Zero

    protected open fun mapText(texts: List<Text>?, invoiceField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): String =
        mapNullableText(texts) ?: run {
            dataErrors.add(InvoiceDataError(invoiceField, InvoiceDataErrorType.ValueNotSet))
            ""
        }

    protected open fun mapNullableText(texts: List<Text>?): String? =
        texts?.firstOrNull()?.value

    protected open fun mapTotalAmounts(summation: TradeSettlementHeaderMonetarySummation?, dataErrors: MutableList<InvoiceDataError>): TotalAmounts =
        if (summation == null) {
            dataErrors.add(InvoiceDataError(InvoiceField.TotalAmount, InvoiceDataErrorType.ValueNotSet))
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
        amounts.firstOrNull()?.value ?: BigDecimal.Zero

    protected open fun mapAmount(amounts: List<Amount>, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        if (amounts.isEmpty() || amounts.firstOrNull()?.value == null) {
            dataErrors.add(InvoiceDataError(amountField, InvoiceDataErrorType.ValueNotSet))
            BigDecimal.Zero
        } else {
            amounts.first().value!!
        }

    protected open fun mapQuantity(quantity: Quantity?, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): BigDecimal =
        quantity?.value ?: run {
            dataErrors.add(InvoiceDataError(amountField, InvoiceDataErrorType.ValueNotSet))
            BigDecimal.Zero
        }

    protected open fun mapUnit(quantity: Quantity?, amountField: InvoiceField, dataErrors: MutableList<InvoiceDataError>): UnitOfMeasure =
        quantity?.unitCode?.let { unitCode -> UnitOfMeasure.entries.firstOrNull { it.code == unitCode } } ?: run {
            dataErrors.add(InvoiceDataError(amountField, InvoiceDataErrorType.ValueNotSet))
            UnitOfMeasure.ZZ
        }

    protected open fun mapCurrency(currencyCode: CurrencyCode?, dataErrors: MutableList<InvoiceDataError>): Currency =
        if (currencyCode == null || currencyCode.value == null) {
            dataErrors.add(InvoiceDataError(InvoiceField.Currency, InvoiceDataErrorType.ValueNotSet))
            Currency.TheCodesAssignedForTransactionsWhereNoCurrencyIsInvolved
        } else {
            val code = currencyCode.value
            Currency.entries.first { it.alpha3Code == code }
        }


    protected open fun map(dateTime: DateTime?): LocalDate =
        mapNullable(dateTime) ?: LocalDate(0, 1, 1)

    protected open fun mapNullable(dateTime: DateTime?): LocalDate? =
        dateTime?.dateTime?.let { LocalDate(it.year, it.month, it.dayOfMonth) }

    protected open fun map(text: Text?): String =
        text?.value ?: ""

    protected open fun map(code: Code?): String =
        code?.value ?: ""

    protected open fun map(id: ID?): String =
        id?.value ?: ""
}
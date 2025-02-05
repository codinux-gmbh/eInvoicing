package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.codes.*
import net.codinux.invoicing.model.facturx.en16931.*
import net.codinux.invoicing.model.facturx.en16931.PaymentMeansCode

open class DomainToCiiMapper {

    open fun mapInvoice(invoice: Invoice): CrossIndustryInvoice = CrossIndustryInvoice(
        mapExchangedDocumentContext(invoice),
        mapExchangedDocument(invoice),
        mapSupplyChainTradeTransaction(invoice)
    )


    protected open fun mapExchangedDocumentContext(invoice: Invoice) = ExchangedDocumentContext(
        null,
        DocumentContextParameter(ID(FacturXProfile.EN16931.profileId))
    )

    protected open fun mapExchangedDocument(invoice: Invoice) = ExchangedDocument(
        ID(invoice.details.invoiceNumber),
        DocumentCode(InvoiceType._380.code),
        mapDate(invoice.details.invoiceDate),
    )


    protected open fun mapSupplyChainTradeTransaction(invoice: Invoice) = SupplyChainTradeTransaction(
        mapItems(invoice),
        mapHeaderTradeAgreement(invoice),
        mapHeaderTradeDelivery(invoice),
        mapHeaderTradeSettlement(invoice)
    )

    protected open fun mapItems(invoice: Invoice) = invoice.items.mapIndexed { index, item -> mapItem(item, index + 1) }

    protected open fun mapItem(item: InvoiceItem, lineId: Int) = SupplyChainTradeLineItem(
        associatedDocumentLineDocument = DocumentLineDocument(ID(lineId.toString())), // TODO: add lineId
        specifiedTradeProduct = TradeProduct(name = mapText(item.name), description = mapNullableText(item.description)),
        specifiedLineTradeDelivery = LineTradeDelivery(mapQuantity(item)),
        specifiedLineTradeAgreement = LineTradeAgreement(
            netPriceProductTradePrice = TradePrice(mapAmount(item.unitPrice), basisQuantity = mapQuantity(item)), // TODO: is this correct using both times the same quantity data?
        ),
        specifiedLineTradeSettlement = LineTradeSettlement(
            // on line level exist on TradeTax object: typeCode, categoryCode, rateApplicablePercent
            // in Extended profile also: calculatedAmount, ExemptionReason and ExemptionReasonCode
            applicableTradeTax = TradeTax(
                rateApplicablePercent = mapPercent(item.vatRate),
                // Angabe nur bei Steuern, die nicht USt (VAT) sind.
                // calculatedAmount = mapAmount(item.), // TODO: calculate calculatedAmount (= Steuerbetrag); basisAmount does not exist on line leve
                typeCode = TaxTypeCode("VAT"), // in Factur-X Basic - Extended profile always "VAT"
                categoryCode = TaxCategoryCode(VatCategoryCode.S.code)
            ),
            specifiedTradeSettlementLineMonetarySummation = TradeSettlementLineMonetarySummation(mapAmount(item.unitPrice)), // TODO: calculate lineTotalAmount
        )
        // TODO: map articleNumber
    )

    protected open fun mapHeaderTradeAgreement(invoice: Invoice) = HeaderTradeAgreement(
        invoice.customerReferenceNumber?.let { Text(it) },
        mapParty(invoice.supplier),
        mapParty(invoice.customer)
    )

    protected open fun mapParty(party: Party) = TradeParty(
        name = mapText(party.name),
        postalTradeAddress = mapAddress(party),
        // TODO: also Leitweg-ID (0204) or EAN Location Code (0060) could be used here
        uRIUniversalCommunication = mapEmailCommunication(party.email),
        definedTradeContact = mapContact(party),
        specifiedTaxRegistration = mapTaxRegistration(party),
    )

    protected open fun mapContact(party: Party): TradeContact? =
        if (party.email != null || party.phone != null || party.fax != null) {
            TradeContact(
                personName = mapText(party.name), // TODO: really add twice?
                departmentName = null, // TODO
                emailURIUniversalCommunication = mapEmailCommunication(party.email), // TODO: really add twice, here and on TradeParty directly?
                telephoneUniversalCommunication = mapNumberCommunication(party.phone),
                // TODO: faxUniversalCommunication is only in Extended profile and CII, not in EN16931 profile
                //   the same with mobileTelephoneUniversalCommunication
            )
        } else {
            null
        }

    protected open fun mapAddress(party: Party) = TradeAddress(
        lineOne = mapText(party.address),
        lineTwo = mapNullableText(party.additionalAddressLine),
        postcodeCode = mapNullableCode(party.postalCode),
        cityName = mapText(party.city),
        countryID = CountryID(party.country.alpha2Code),
        countrySubDivisionName = null // TODO: add to data model
    )

    protected open fun mapTaxRegistration(party: Party): List<TaxRegistration> = buildList {
        party.vatId?.let {
            add(TaxRegistration(ID(it, ReferenceType.VA.code)))
        }

        // TODO: add taxId to data model
    }


    protected open fun mapHeaderTradeDelivery(invoice: Invoice) = HeaderTradeDelivery(
        actualDeliverySupplyChainEvent = invoice.details.serviceDate?.asDeliveryDate()?.let {
            SupplyChainEvent(mapDate(it.deliveryDate))
        }
    )

    protected open fun mapHeaderTradeSettlement(invoice: Invoice) = HeaderTradeSettlement(
        invoiceCurrencyCode = CurrencyCode(invoice.details.currency.alpha3Code),
        billingSpecifiedPeriod = invoice.details.serviceDate?.asServicePeriod()?.let {
            SpecifiedPeriod(mapDate(it.startDate), mapDate(it.endDate))
        },
        paymentReference = mapText(invoice.details.invoiceNumber), // TODO: make paymentReference configurable
        applicableTradeTax = mapTradeTax(invoice),
        specifiedTradeSettlementHeaderMonetarySummation = mapMonetarySummation(invoice),
        specifiedTradePaymentTerms = TradePaymentTerms(mapNullableText(invoice.details.paymentDescription), mapNullableDate(invoice.details.dueDate)),
        specifiedTradeSettlementPaymentMeans = mapBankDetails(invoice)?.let { listOf(it) } ?: emptyList(),
    )

    protected open fun mapBankDetails(invoice: Invoice): TradeSettlementPaymentMeans? =
        invoice.supplier.bankDetails?.let { details ->
            TradeSettlementPaymentMeans(
                // TODO: there are also other means of payment then credit transfer: direct debit and financial card
                typeCode = PaymentMeansCode(net.codinux.invoicing.model.codes.PaymentMeansCode._58.code),
                information = mapText(net.codinux.invoicing.model.codes.PaymentMeansCode._58.meaning),
                payeePartyCreditorFinancialAccount = CreditorFinancialAccount(ID(details.accountNumber), mapNullableText(details.accountHolderName)),
                payeeSpecifiedCreditorFinancialInstitution = details.bankCode?.let { CreditorFinancialInstitution(ID(it), mapNullableText(details.financialInstitutionName)) },
                payerPartyDebtorFinancialAccount = null, // in case of direct debit
                applicableTradeSettlementFinancialCard = null, // in case of card payment
            )
        }

    protected open fun mapTradeTax(invoice: Invoice): List<TradeTax> {
        val byTaxRateAndCategory = invoice.items.groupBy { it.vatRate } // TODO: also group by CategoryCode and ExamptReason(Code)

        return byTaxRateAndCategory.entries.map { (percent, items) ->
            // Die Summe des Gesamtnettobetrags der Positionen zuzüglich der Zuschläge abzüglich der Abschläge der
            // Dokumentenebene, für die ein bestimmter Code der Umsatzsteuerkategorie und ein bestimmter
            // Umsatzsteuersatz gelten (falls ein kategoriespezifischer Umsatzsteuersatz gilt).
            val basisAmount = items.sumOf { it.amounts!!.netPrice }
            val calculatedAmount = basisAmount * percent

            TradeTax(mapAmount(calculatedAmount), TaxTypeCode("VAT"), null, mapAmount(basisAmount),
                TaxCategoryCode(VatCategoryCode.S.code), rateApplicablePercent = mapPercent(percent))
        }
    }

    protected open fun mapMonetarySummation(invoice: Invoice): TradeSettlementHeaderMonetarySummation =
        invoice.totals?.let { mapMonetarySummation(it) }
            ?: throw IllegalStateException("In order to create a Factur-X invoice total amounts need to be set.")

    protected open fun mapMonetarySummation(totals: TotalAmounts) = TradeSettlementHeaderMonetarySummation(
        lineTotalAmount = mapAmount(totals.lineTotalAmount),
        chargeTotalAmount = mapAmount(totals.chargeTotalAmount),
        allowanceTotalAmount = mapAmount(totals.allowanceTotalAmount),
        taxBasisTotalAmount = mapAmount(totals.taxBasisTotalAmount),
        // TODO: there can be two taxTotalAmount:
        //   - Der Gesamtbetrag der Rechnungsumsatzsteuer ist die Summe aller Beträge für die einzelnen Umsatzsteuerkategorien in der Rechnungswährung.
        //   - Der Steuergesamtbetrag in Buchungswährung, die im Land des Verkäufers gültig ist oder verlangt wird.
        //     Zu verwenden, wenn der Code für die Währung der Umsatzsteuerbuchung (BT-6) nach Artikel 230 der Richtlinie
        //2006/112/EG über Umsatzsteuer vom Code für die Rechnungswährung (BT-5) abweicht.
        //Der Steuergesamtbetrag in Buchungswährung wird bei der Berechnung der Rechnungssummen nicht
        //berücksichtigt.
        taxTotalAmount = listOf(mapAmount(totals.taxTotalAmount)), // TODO: add to data modell that there can be multiple tax amounts for multiple tax rates
        roundingAmount = null, // TODO: add to data modell
        grandTotalAmount = mapAmount(totals.grandTotalAmount),
        totalPrepaidAmount = mapAmount(totals.totalPrepaidAmount),
        duePayableAmount = mapAmount(totals.duePayableAmount),
    )


    protected open fun mapEmailCommunication(email: String?) =
        mapUriCommunication(email, ElectronicAddressSchemeIdentifier.EM)

    protected open fun mapUriCommunication(uri: String?, schemeIdentifier: ElectronicAddressSchemeIdentifier): UniversalCommunication? =
        uri?.let { UniversalCommunication(ID(it, schemeIdentifier.code)) }

    protected open fun mapNumberCommunication(number: String?): UniversalCommunication? =
        number?.let { UniversalCommunication(completeNumber = mapText(number)) }

    protected open fun mapAmount(amount: BigDecimal) = Amount(amount)

    protected open fun mapQuantity(item: InvoiceItem) = mapQuantity(item.quantity, item.unit)

    protected open fun mapQuantity(quantity: BigDecimal, unitCode: UnitOfMeasure) = Quantity(quantity, unitCode.code)

    protected open fun mapPercent(percent: BigDecimal) = Percent(percent)


    protected open fun mapNullableDate(date: LocalDate?): DateTime? =
        date?.let { mapDate(it) }

    protected open fun mapDate(date: LocalDate) = DateTime(
        DateTimeString("${date.year.toString().padStart(4, '0')}${date.month.toString().padStart(2, '0')}${date.dayOfMonth.toString().padStart(2, '0')}", CiiMapper.Iso8601DateFormatCode)
    )

    protected open fun mapNullableCode(code: String?): Code? =
        code?.let { mapCode(it) }

    protected open fun mapCode(code: String) = Code(code)

    protected open fun mapNullableText(text: String?): Text? =
        text?.let { mapText(it) }

    protected open fun mapText(text: String) = Text(text)

}
package net.codinux.invoicing.model.mapper

import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult.Companion.isNotMinimumOrBasicWLProfile
import net.codinux.invoicing.model.*
import net.codinux.invoicing.model.Party
import net.codinux.invoicing.model.codes.Currency
import net.codinux.invoicing.model.codes.UnitOfMeasure
import net.codinux.invoicing.model.codes.VatCategoryCode
import net.codinux.invoicing.model.mapper.MapperConstants.BigDecimalFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.CurrencyFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.LocalDateFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.TextFallbackValue
import net.codinux.invoicing.model.mapper.MapperConstants.UnitFallbackValue
import net.codinux.invoicing.model.ubl.*
import net.codinux.invoicing.reader.ReadEInvoiceXmlResult
import net.codinux.invoicing.reader.ReadEInvoiceXmlResultType
import net.codinux.log.logger

open class UblMapper {

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

        supplier = mapParty(invoice.accountingSupplierParty.party),
        customer = mapParty(invoice.accountingCustomerParty.party),
        items = invoice.invoiceLine.map { mapLineItem(it, dataErrors) },

        customerReferenceNumber = mapNullableText(invoice.buyerReference)
    )

    protected open fun mapInvoiceDetails(invoice: UblInvoice, dataErrors: MutableList<InvoiceDataError>) = InvoiceDetails(
        invoiceNumber = invoice.id.value ?: TextFallbackValue,
        invoiceDate = mapDate(invoice.issueDate),
        // TODO: map InvoiceTypeCode
        currency = mapCurrency(invoice.documentCurrencyCode)
        // TODO: map serviceDate, dueDate and paymentDescription
    )

    protected open fun mapParty(party: net.codinux.invoicing.model.ubl.Party?) =
        if (party == null) {
            Party(TextFallbackValue, TextFallbackValue, null, null, TextFallbackValue)
        } else {
            Party(
                name = party.partyName.joinToString(" ") { mapText(it.name) },
                address = party.postalAddress?.streetName?.value?.let { "$it${party.postalAddress.buildingNumber?.value?.let { " $it" } ?: ""}" }
                    ?: mapText(party.postalAddress?.addressLine?.firstOrNull()?.line),
                additionalAddressLine = null, // TODO
                postalCode = mapNullableText(party.postalAddress?.postalZone),
                city = mapText(party.postalAddress?.cityName),
            )
        }

    protected open fun mapLineItem(line: InvoiceLine, dataErrors: MutableList<InvoiceDataError>) = InvoiceItem(
        // TODO: map ID (a required field)
        name = mapText(line.item.name),
        // TODO: what's the difference between line.cbcInvoicedQuantity and line.price.baseQuantity
        quantity = mapQuantity(line.invoicedQuantity, InvoiceField.ItemQuantity, dataErrors),
        unit = mapUnit(line.invoicedQuantity, InvoiceField.ItemUnit, dataErrors),
        unitPrice = mapAmount(line.price?.priceAmount, InvoiceField.ItemUnitPrice, dataErrors),
        vatRate = mapVatRateOrDefault(line.item.classifiedTaxCategory),

        description = line.item.description.joinToString(" "), // TODO: or use new line?
    )


    protected open fun checkCommonDataErrors(invoice: UblInvoice, format: EInvoiceFormatDetectionResult?) = mutableListOf<InvoiceDataError>().apply {
        // TODO: check also for empty values
        if (invoice.id.value == null) {
            add(InvoiceDataError.missing(InvoiceField.InvoiceNumber))
        }
        if (invoice.issueDate == null) {
            add(InvoiceDataError.missing(InvoiceField.InvoiceDate))
        }
        if (invoice.documentCurrencyCode?.value == null) {
            add(InvoiceDataError.missing(InvoiceField.Currency))
        }

        val supplier = invoice.accountingSupplierParty.party
        if (supplier == null) {
            add(InvoiceDataError.missing(InvoiceField.Supplier))
        } else {
            if (supplier.partyName.isEmpty()) {
                add(InvoiceDataError.missing(InvoiceField.SupplierName))
            }
            if (supplier.postalAddress?.country?.identificationCode == null) {
                add(InvoiceDataError.missing(InvoiceField.SupplierCountry))
            }
        }

        val customer = invoice.accountingCustomerParty.party
        if (customer == null) {
            add(InvoiceDataError.missing(InvoiceField.Customer))
        } else {
            if (customer.partyName.isEmpty()) {
                add(InvoiceDataError.missing(InvoiceField.CustomerName))
            }
            if (customer.postalAddress?.country?.identificationCode == null) {
                add(InvoiceDataError.missing(InvoiceField.CustomerCountry))
            }
        }

        if (invoice.invoiceLine.isEmpty() && format.isNotMinimumOrBasicWLProfile) {
            add(InvoiceDataError.missing(InvoiceField.Items))
        }
    }


    protected open fun mapVatRateOrDefault(tradeTax: List<TaxCategory>?): BigDecimal =
        tradeTax?.let { mapVatRate(it) } ?: BigDecimal.Zero

    protected open fun mapVatRate(tradeTax: List<TaxCategory>): BigDecimal =
        tradeTax.firstOrNull { it.taxScheme.id?.value == "VAT" && it.id?.value == VatCategoryCode.S.code && it.percent != null }
            ?.percent?.value ?: BigDecimalFallbackValue

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

    protected open fun mapCurrency(currency: Code?): Currency =
        currency?.value?.let { code ->
            Currency.entries.firstOrNull { it.alpha3Code == code }
        } ?: CurrencyFallbackValue


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
package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeTax(
  @XmlSerialName(
    value = "CalculatedAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val calculatedAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: TaxTypeCode? = null,
  @XmlSerialName(
    value = "ExemptionReason",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val exemptionReason: Text? = null,
  @XmlSerialName(
    value = "CalculatedRate",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val calculatedRate: Rate? = null,
  @XmlSerialName(
    value = "CalculationSequenceNumeric",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val calculationSequenceNumeric: Numeric? = null,
  @XmlSerialName(
    value = "BasisQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val basisQuantity: Quantity? = null,
  @XmlSerialName(
    value = "BasisAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val basisAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "UnitBasisAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val unitBasisAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "LineTotalBasisAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val lineTotalBasisAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "AllowanceChargeBasisAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val allowanceChargeBasisAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "CategoryCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val categoryCode: TaxCategoryCode? = null,
  @XmlSerialName(
    value = "CurrencyCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val currencyCode: CurrencyCode? = null,
  @XmlSerialName(
    value = "Jurisdiction",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val jurisdiction: List<Text> = emptyList(),
  @XmlSerialName(
    value = "CustomsDutyIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val customsDutyIndicator: Indicator? = null,
  @XmlSerialName(
    value = "ExemptionReasonCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val exemptionReasonCode: Code? = null,
  @XmlSerialName(
    value = "TaxBasisAllowanceRate",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxBasisAllowanceRate: Rate? = null,
  @XmlSerialName(
    value = "TaxPointDate",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxPointDate: Date? = null,
  @XmlSerialName(
    value = "Type",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val type: Text? = null,
  @XmlSerialName(
    value = "InformationAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val informationAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "CategoryName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val categoryName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "DueDateTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val dueDateTypeCode: TimeReferenceCode? = null,
  @XmlSerialName(
    value = "RateApplicablePercent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val rateApplicablePercent: Percent? = null,
  @XmlSerialName(
    value = "GrandTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grandTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "CalculationMethodCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val calculationMethodCode: Code? = null,
  @XmlSerialName(
    value = "SpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeAccountingAccount: List<TradeAccountingAccount> = emptyList(),
  @XmlSerialName(
    value = "ServiceSupplyTradeCountry",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val serviceSupplyTradeCountry: TradeCountry? = null,
  @XmlSerialName(
    value = "BuyerRepayableTaxSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerRepayableTaxSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
  @XmlSerialName(
    value = "SellerPayableTaxSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerPayableTaxSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
  @XmlSerialName(
    value = "SellerRefundableTaxSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerRefundableTaxSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
  @XmlSerialName(
    value = "BuyerDeductibleTaxSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerDeductibleTaxSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
  @XmlSerialName(
    value = "BuyerNonDeductibleTaxSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerNonDeductibleTaxSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
  @XmlSerialName(
    value = "PlaceApplicableTradeLocation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val placeApplicableTradeLocation: List<TradeLocation> = emptyList(),
)

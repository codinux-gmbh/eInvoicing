package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradePrice(
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: PriceTypeCode? = null,
  @XmlSerialName(
    value = "ChargeAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val chargeAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "BasisQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val basisQuantity: Quantity? = null,
  @XmlSerialName(
    value = "MinimumQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val minimumQuantity: Quantity? = null,
  @XmlSerialName(
    value = "MaximumQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val maximumQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ChangeReason",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val changeReason: List<Text> = emptyList(),
  @XmlSerialName(
    value = "OrderUnitConversionFactorNumeric",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val orderUnitConversionFactorNumeric: Numeric? = null,
  @XmlSerialName(
    value = "Type",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val type: List<Text> = emptyList(),
  @XmlSerialName(
    value = "BasisDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val basisDateTime: DateTime? = null,
  @XmlSerialName(
    value = "AppliedTradeAllowanceCharge",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val appliedTradeAllowanceCharge: List<TradeAllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "ValiditySpecifiedPeriod",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val validitySpecifiedPeriod: SpecifiedPeriod? = null,
  @XmlSerialName(
    value = "IncludedTradeTax",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedTradeTax: List<TradeTax> = emptyList(),
  @XmlSerialName(
    value = "DeliveryTradeLocation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val deliveryTradeLocation: List<TradeLocation> = emptyList(),
  @XmlSerialName(
    value = "TradeComparisonReferencePrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val tradeComparisonReferencePrice: List<ReferencePrice> = emptyList(),
  @XmlSerialName(
    value = "AssociatedReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val associatedReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTradeLocation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeLocation: TradeLocation? = null,
)

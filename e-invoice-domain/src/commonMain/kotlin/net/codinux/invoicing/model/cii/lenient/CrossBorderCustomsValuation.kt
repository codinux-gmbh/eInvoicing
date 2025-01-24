package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class CrossBorderCustomsValuation(
  @XmlSerialName(
    value = "AddedAdjustmentAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val addedAdjustmentAmount: Amount? = null,
  @XmlSerialName(
    value = "DeductedAdjustmentAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val deductedAdjustmentAmount: Amount? = null,
  @XmlSerialName(
    value = "AddedAdjustmentPercent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val addedAdjustmentPercent: Percent? = null,
  @XmlSerialName(
    value = "DeductedAdjustmentPercent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val deductedAdjustmentPercent: Percent? = null,
  @XmlSerialName(
    value = "MethodCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val methodCode: Code? = null,
  @XmlSerialName(
    value = "WTOAdditionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val wTOAdditionCode: Code? = null,
  @XmlSerialName(
    value = "ChargeApportionMethodCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val chargeApportionMethodCode: Code? = null,
  @XmlSerialName(
    value = "OtherChargeAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val otherChargeAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "BuyerSellerRelationshipIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerSellerRelationshipIndicator: Indicator? = null,
  @XmlSerialName(
    value = "BuyerSellerRelationshipPriceInfluenceIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerSellerRelationshipPriceInfluenceIndicator: Indicator? = null,
  @XmlSerialName(
    value = "SaleRestrictionIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val saleRestrictionIndicator: Indicator? = null,
  @XmlSerialName(
    value = "SalePriceConditionIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val salePriceConditionIndicator: Indicator? = null,
  @XmlSerialName(
    value = "RoyaltyLicenseFeeIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val royaltyLicenseFeeIndicator: Indicator? = null,
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: Code? = null,
  @XmlSerialName(
    value = "SaleRestriction",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val saleRestriction: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTradeCurrencyExchange",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeCurrencyExchange: TradeCurrencyExchange? = null,
)

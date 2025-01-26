package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SubordinateTradeLineItem(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: List<ID> = emptyList(),
  @XmlSerialName(
    value = "ResponseReasonCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val responseReasonCode: Code? = null,
  @XmlSerialName(
    value = "CategoryCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val categoryCode: Code? = null,
  @XmlSerialName(
    value = "SpecifiedReferencedProduct",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedReferencedProduct: ReferencedProduct? = null,
  @XmlSerialName(
    value = "ApplicableTradeProduct",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeProduct: List<TradeProduct> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedSubordinateLineTradeAgreement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedSubordinateLineTradeAgreement: SubordinateLineTradeAgreement? = null,
  @XmlSerialName(
    value = "SpecifiedSubordinateLineTradeDelivery",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedSubordinateLineTradeDelivery: SubordinateLineTradeDelivery? = null,
  @XmlSerialName(
    value = "SpecifiedSubordinateLineTradeSettlement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedSubordinateLineTradeSettlement: SubordinateLineTradeSettlement? = null,
)

package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SupplyChainTradeTransaction(
  @XmlSerialName(
    value = "IncludedSupplyChainTradeLineItem",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedSupplyChainTradeLineItem: List<SupplyChainTradeLineItem> = emptyList(),
  @XmlSerialName(
    value = "ApplicableHeaderTradeAgreement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableHeaderTradeAgreement: HeaderTradeAgreement? = null,
  @XmlSerialName(
    value = "ApplicableHeaderTradeDelivery",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableHeaderTradeDelivery: HeaderTradeDelivery? = null,
  @XmlSerialName(
    value = "ApplicableHeaderTradeSettlement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableHeaderTradeSettlement: HeaderTradeSettlement? = null,
)

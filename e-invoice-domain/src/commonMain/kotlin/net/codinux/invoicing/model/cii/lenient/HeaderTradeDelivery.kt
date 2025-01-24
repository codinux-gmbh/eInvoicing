package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HeaderTradeDelivery(
  @XmlSerialName(
    value = "RelatedSupplyChainConsignment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val relatedSupplyChainConsignment: SupplyChainConsignment? = null,
  @XmlSerialName(
    value = "ShipToTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val shipToTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "UltimateShipToTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val ultimateShipToTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "ShipFromTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val shipFromTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "ActualDespatchSupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualDespatchSupplyChainEvent: SupplyChainEvent? = null,
  @XmlSerialName(
    value = "ActualPickUpSupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualPickUpSupplyChainEvent: SupplyChainEvent? = null,
  @XmlSerialName(
    value = "ActualDeliverySupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualDeliverySupplyChainEvent: SupplyChainEvent? = null,
  @XmlSerialName(
    value = "ActualReceiptSupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualReceiptSupplyChainEvent: SupplyChainEvent? = null,
  @XmlSerialName(
    value = "AdditionalReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "DespatchAdviceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val despatchAdviceReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "ReceivingAdviceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val receivingAdviceReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "DeliveryNoteReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val deliveryNoteReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "ConsumptionReportReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val consumptionReportReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "PreviousDeliverySupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val previousDeliverySupplyChainEvent: List<SupplyChainEvent> = emptyList(),
  @XmlSerialName(
    value = "PackingListReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val packingListReferencedDocument: ReferencedDocument? = null,
)

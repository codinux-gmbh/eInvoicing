package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class LineTradeDelivery(
  @XmlSerialName(
    value = "RequestedQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val requestedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ReceivedQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val receivedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "BilledQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val billedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ChargeFreeQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val chargeFreeQuantity: Quantity? = null,
  @XmlSerialName(
    value = "PackageQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val packageQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ProductUnitQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val productUnitQuantity: Quantity? = null,
  @XmlSerialName(
    value = "PerPackageUnitQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val perPackageUnitQuantity: Quantity? = null,
  @XmlSerialName(
    value = "NetWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netWeightMeasure: WeightUnitMeasure? = null,
  @XmlSerialName(
    value = "GrossWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossWeightMeasure: WeightUnitMeasure? = null,
  @XmlSerialName(
    value = "TheoreticalWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val theoreticalWeightMeasure: WeightUnitMeasure? = null,
  @XmlSerialName(
    value = "DespatchedQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val despatchedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "SpecifiedDeliveryAdjustment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedDeliveryAdjustment: List<DeliveryAdjustment> = emptyList(),
  @XmlSerialName(
    value = "IncludedSupplyChainPackaging",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedSupplyChainPackaging: List<SupplyChainPackaging> = emptyList(),
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
    value = "RequestedDeliverySupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val requestedDeliverySupplyChainEvent: SupplyChainEvent? = null,
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
    value = "PackingListReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val packingListReferencedDocument: ReferencedDocument? = null,
)

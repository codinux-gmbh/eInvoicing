package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SubordinateLineTradeDelivery(
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
    value = "BilledQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val billedQuantity: List<Quantity> = emptyList(),
  @XmlSerialName(
    value = "IncludedSupplyChainPackaging",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedSupplyChainPackaging: List<SupplyChainPackaging> = emptyList(),
  @XmlSerialName(
    value = "ActualDeliverySupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualDeliverySupplyChainEvent: List<SupplyChainEvent> = emptyList(),
)

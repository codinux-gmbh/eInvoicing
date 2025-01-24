package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeProductInstance(
  @XmlSerialName(
    value = "GlobalSerialID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val globalSerialID: ID? = null,
  @XmlSerialName(
    value = "BatchID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val batchID: ID? = null,
  @XmlSerialName(
    value = "KanbanID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val kanbanID: ID? = null,
  @XmlSerialName(
    value = "SupplierAssignedSerialID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val supplierAssignedSerialID: ID? = null,
  @XmlSerialName(
    value = "BestBeforeDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val bestBeforeDateTime: DateTime? = null,
  @XmlSerialName(
    value = "ExpiryDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val expiryDateTime: DateTime? = null,
  @XmlSerialName(
    value = "SellByDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellByDateTime: DateTime? = null,
  @XmlSerialName(
    value = "SerialID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val serialID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "RegistrationID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val registrationID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "ProductionSupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val productionSupplyChainEvent: SupplyChainEvent? = null,
  @XmlSerialName(
    value = "PackagingSupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val packagingSupplyChainEvent: SupplyChainEvent? = null,
  @XmlSerialName(
    value = "ApplicableMaterialGoodsCharacteristic",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableMaterialGoodsCharacteristic: List<MaterialGoodsCharacteristic> = emptyList(),
  @XmlSerialName(
    value = "ApplicableProductCharacteristic",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableProductCharacteristic: List<ProductCharacteristic> = emptyList(),
)

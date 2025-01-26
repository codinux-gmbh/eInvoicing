package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SupplyChainConsignmentItem(
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: GoodsTypeCode? = null,
  @XmlSerialName(
    value = "TypeExtensionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeExtensionCode: GoodsTypeExtensionCode? = null,
  @XmlSerialName(
    value = "DeclaredValueForCustomsAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val declaredValueForCustomsAmount: Amount? = null,
  @XmlSerialName(
    value = "DeclaredValueForStatisticsAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val declaredValueForStatisticsAmount: Amount? = null,
  @XmlSerialName(
    value = "InvoiceAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "GrossWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossWeightMeasure: WeightUnitMeasure? = null,
  @XmlSerialName(
    value = "NetWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netWeightMeasure: WeightUnitMeasure? = null,
  @XmlSerialName(
    value = "TariffQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val tariffQuantity: Quantity? = null,
  @XmlSerialName(
    value = "GlobalID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val globalID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "NatureIdentificationTransportCargo",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val natureIdentificationTransportCargo: TransportCargo? = null,
  @XmlSerialName(
    value = "ApplicableTransportDangerousGoods",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTransportDangerousGoods: List<TransportDangerousGoods> = emptyList(),
  @XmlSerialName(
    value = "PreviousAdministrativeReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val previousAdministrativeReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "ApplicableNote",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableNote: List<Note> = emptyList(),
)

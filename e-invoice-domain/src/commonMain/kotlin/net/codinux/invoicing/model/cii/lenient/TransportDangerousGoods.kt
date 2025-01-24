package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TransportDangerousGoods(
  @XmlSerialName(
    value = "UNDGIdentificationCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val uNDGIdentificationCode: Code? = null,
  @XmlSerialName(
    value = "RegulationCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val regulationCode: DangerousGoodsRegulationCode? = null,
  @XmlSerialName(
    value = "RegulationName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val regulationName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TechnicalName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val technicalName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "EMSID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val emsid: ID? = null,
  @XmlSerialName(
    value = "PackagingDangerLevelCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val packagingDangerLevelCode: DangerousGoodsPackagingLevelCode? = null,
  @XmlSerialName(
    value = "HazardClassificationID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val hazardClassificationID: ID? = null,
  @XmlSerialName(
    value = "AdditionalHazardClassificationID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalHazardClassificationID: ID? = null,
  @XmlSerialName(
    value = "ProperShippingName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val properShippingName: Text? = null,
)

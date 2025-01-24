package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SupplyChainTradeLineItem(
  @XmlSerialName(
    value = "DescriptionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val descriptionCode: Code? = null,
  @XmlSerialName(
    value = "AssociatedDocumentLineDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val associatedDocumentLineDocument: DocumentLineDocument? = null,
  @XmlSerialName(
    value = "SpecifiedTradeProduct",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeProduct: TradeProduct? = null,
  @XmlSerialName(
    value = "AdditionalInformationNote",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalInformationNote: List<Note> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedLineTradeAgreement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLineTradeAgreement: LineTradeAgreement? = null,
  @XmlSerialName(
    value = "SpecifiedLineTradeDelivery",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLineTradeDelivery: LineTradeDelivery? = null,
  @XmlSerialName(
    value = "SpecifiedLineTradeSettlement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLineTradeSettlement: LineTradeSettlement? = null,
  @XmlSerialName(
    value = "IncludedSubordinateTradeLineItem",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedSubordinateTradeLineItem: List<SubordinateTradeLineItem> = emptyList(),
)

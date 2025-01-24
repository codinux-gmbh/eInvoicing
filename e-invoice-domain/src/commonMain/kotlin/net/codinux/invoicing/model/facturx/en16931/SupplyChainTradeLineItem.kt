package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SupplyChainTradeLineItem(
  @XmlSerialName(
    value = "AssociatedDocumentLineDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val associatedDocumentLineDocument: DocumentLineDocument,
  @XmlSerialName(
    value = "SpecifiedTradeProduct",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeProduct: TradeProduct,
  @XmlSerialName(
    value = "SpecifiedLineTradeAgreement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLineTradeAgreement: LineTradeAgreement,
  @XmlSerialName(
    value = "SpecifiedLineTradeDelivery",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLineTradeDelivery: LineTradeDelivery,
  @XmlSerialName(
    value = "SpecifiedLineTradeSettlement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLineTradeSettlement: LineTradeSettlement,
)

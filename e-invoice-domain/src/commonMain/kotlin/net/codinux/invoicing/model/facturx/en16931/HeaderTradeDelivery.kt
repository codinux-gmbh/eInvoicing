package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HeaderTradeDelivery(
  @XmlSerialName(
    value = "ShipToTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val shipToTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "ActualDeliverySupplyChainEvent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualDeliverySupplyChainEvent: SupplyChainEvent? = null,
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
)

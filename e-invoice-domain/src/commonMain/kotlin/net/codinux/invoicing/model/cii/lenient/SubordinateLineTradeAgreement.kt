package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SubordinateLineTradeAgreement(
  @XmlSerialName(
    value = "SellerOrderReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerOrderReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "BuyerOrderReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerOrderReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "AdditionalReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "GrossPriceProductTradePrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossPriceProductTradePrice: List<TradePrice> = emptyList(),
  @XmlSerialName(
    value = "NetPriceProductTradePrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netPriceProductTradePrice: List<TradePrice> = emptyList(),
)

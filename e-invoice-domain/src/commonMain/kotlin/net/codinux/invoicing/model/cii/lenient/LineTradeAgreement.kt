package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class LineTradeAgreement(
  @XmlSerialName(
    value = "BuyerReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerReference: Text? = null,
  @XmlSerialName(
    value = "BuyerRequisitionerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerRequisitionerTradeParty: List<TradeParty> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTradeDeliveryTerms",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeDeliveryTerms: TradeDeliveryTerms? = null,
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
    value = "QuotationReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val quotationReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "ContractReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contractReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "DemandForecastReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val demandForecastReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "PromotionalDealReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val promotionalDealReferencedDocument: ReferencedDocument? = null,
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
  val grossPriceProductTradePrice: TradePrice? = null,
  @XmlSerialName(
    value = "NetPriceProductTradePrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netPriceProductTradePrice: TradePrice? = null,
  @XmlSerialName(
    value = "RequisitionerReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val requisitionerReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "ItemSellerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val itemSellerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "ItemBuyerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val itemBuyerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "IncludedSpecifiedMarketplace",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedSpecifiedMarketplace: SpecifiedMarketplace? = null,
  @XmlSerialName(
    value = "UltimateCustomerOrderReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val ultimateCustomerOrderReferencedDocument: List<ReferencedDocument> = emptyList(),
)

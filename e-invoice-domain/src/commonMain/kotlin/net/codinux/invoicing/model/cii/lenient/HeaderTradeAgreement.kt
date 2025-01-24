package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HeaderTradeAgreement(
  @XmlSerialName(
    value = "Reference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val reference: List<Text> = emptyList(),
  @XmlSerialName(
    value = "BuyerReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerReference: Text? = null,
  @XmlSerialName(
    value = "SellerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "BuyerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "SalesAgentTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val salesAgentTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "BuyerRequisitionerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerRequisitionerTradeParty: List<TradeParty> = emptyList(),
  @XmlSerialName(
    value = "BuyerAssignedAccountantTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerAssignedAccountantTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "SellerAssignedAccountantTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerAssignedAccountantTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "BuyerTaxRepresentativeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerTaxRepresentativeTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "SellerTaxRepresentativeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerTaxRepresentativeTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "ProductEndUserTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val productEndUserTradeParty: TradeParty? = null,
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
    value = "OrderResponseReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val orderResponseReferencedDocument: ReferencedDocument? = null,
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
    value = "SupplyInstructionReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val supplyInstructionReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "PromotionalDealReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val promotionalDealReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "PriceListReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val priceListReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "AdditionalReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "RequisitionerReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val requisitionerReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "BuyerAgentTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerAgentTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "PurchaseConditionsReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val purchaseConditionsReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedProcuringProject",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedProcuringProject: ProcuringProject? = null,
  @XmlSerialName(
    value = "UltimateCustomerOrderReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val ultimateCustomerOrderReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "PricingBaseApplicableLogisticsLocation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val pricingBaseApplicableLogisticsLocation: LogisticsLocation? = null,
)

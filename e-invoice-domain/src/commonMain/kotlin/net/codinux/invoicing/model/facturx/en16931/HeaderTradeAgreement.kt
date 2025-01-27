package net.codinux.invoicing.model.facturx.en16931

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

/**
 * ### English
 * A group of business terms to specify a direct debit.
 * This group may be used to give prior notice in the invoice that payment will be made through a SEPA or other
 * direct debit initiated by the Seller, in accordance with the rules of the SEPA or other direct debit scheme.
 *
 * ### German
 * Eine Gruppe von betriebswirtschaftlichen Begriffen, die Informationen über eine Lastschrift enthält<br/>
 * Diese Gruppe darf genutzt werden, um in der Rechnung vorweg anzugeben, dass die Zahlung über SEPA oder ein
 * anderes vom Verkäufer eingeleitetes Lastschrif
 */
@Serializable
data class HeaderTradeAgreement(
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
  val sellerTradeParty: TradeParty,
  @XmlSerialName(
    value = "BuyerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerTradeParty: TradeParty,
  @XmlSerialName(
    value = "SellerTaxRepresentativeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerTaxRepresentativeTradeParty: TradeParty? = null,
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
    value = "ContractReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contractReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "AdditionalReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedProcuringProject",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedProcuringProject: ProcuringProject? = null,
)

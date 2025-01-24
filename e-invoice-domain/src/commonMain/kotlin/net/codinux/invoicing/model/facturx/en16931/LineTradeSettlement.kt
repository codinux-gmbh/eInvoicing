package net.codinux.invoicing.model.facturx.en16931

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class LineTradeSettlement(
  @XmlSerialName(
    value = "ApplicableTradeTax",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeTax: TradeTax,
  @XmlSerialName(
    value = "BillingSpecifiedPeriod",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val billingSpecifiedPeriod: SpecifiedPeriod? = null,
  @XmlSerialName(
    value = "SpecifiedTradeAllowanceCharge",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeAllowanceCharge: List<TradeAllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTradeSettlementLineMonetarySummation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeSettlementLineMonetarySummation: TradeSettlementLineMonetarySummation,
  @XmlSerialName(
    value = "AdditionalReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "ReceivableSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val receivableSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
)

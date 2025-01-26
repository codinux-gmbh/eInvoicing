package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SubordinateLineTradeSettlement(
  @XmlSerialName(
    value = "AmountDirectionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val amountDirectionCode: Code? = null,
  @XmlSerialName(
    value = "ApplicableTradeTax",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeTax: TradeTax? = null,
  @XmlSerialName(
    value = "InvoiceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedFinancialAdjustment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedFinancialAdjustment: List<FinancialAdjustment> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTradeAllowanceCharge",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeAllowanceCharge: List<TradeAllowanceCharge> = emptyList(),
)

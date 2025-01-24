package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class LineTradeSettlement(
  @XmlSerialName(
    value = "PaymentReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentReference: List<Text> = emptyList(),
  @XmlSerialName(
    value = "InvoiceIssuerReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceIssuerReference: Text? = null,
  @XmlSerialName(
    value = "TotalAdjustmentAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalAdjustmentAmount: Amount? = null,
  @XmlSerialName(
    value = "DiscountIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val discountIndicator: Indicator? = null,
  @XmlSerialName(
    value = "InvoiceDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceDateTime: DateTime? = null,
  @XmlSerialName(
    value = "ApplicableTradeTax",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeTax: List<TradeTax> = emptyList(),
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
    value = "SubtotalCalculatedTradeTax",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subtotalCalculatedTradeTax: List<TradeTax> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedLogisticsServiceCharge",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLogisticsServiceCharge: List<LogisticsServiceCharge> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTradePaymentTerms",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradePaymentTerms: List<TradePaymentTerms> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTradeSettlementLineMonetarySummation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeSettlementLineMonetarySummation:
      TradeSettlementLineMonetarySummation? = null,
  @XmlSerialName(
    value = "SpecifiedFinancialAdjustment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedFinancialAdjustment: List<FinancialAdjustment> = emptyList(),
  @XmlSerialName(
    value = "InvoiceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "AdditionalReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "PayableSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payableSpecifiedTradeAccountingAccount: List<TradeAccountingAccount> = emptyList(),
  @XmlSerialName(
    value = "ReceivableSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val receivableSpecifiedTradeAccountingAccount: List<TradeAccountingAccount> = emptyList(),
  @XmlSerialName(
    value = "PurchaseSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val purchaseSpecifiedTradeAccountingAccount: List<TradeAccountingAccount> = emptyList(),
  @XmlSerialName(
    value = "SalesSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val salesSpecifiedTradeAccountingAccount: List<TradeAccountingAccount> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTradeSettlementFinancialCard",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeSettlementFinancialCard: TradeSettlementFinancialCard? = null,
  @XmlSerialName(
    value = "SpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeAccountingAccount: List<TradeAccountingAccount> = emptyList(),
)

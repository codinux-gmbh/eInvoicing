package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HeaderTradeSettlement(
  @XmlSerialName(
    value = "DuePayableAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val duePayableAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "CreditorReferenceTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditorReferenceTypeCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "CreditorReferenceType",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditorReferenceType: List<Text> = emptyList(),
  @XmlSerialName(
    value = "CreditorReferenceIssuerID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditorReferenceIssuerID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "CreditorReferenceID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditorReferenceID: ID? = null,
  @XmlSerialName(
    value = "PaymentReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentReference: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TaxCurrencyCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxCurrencyCode: CurrencyCode? = null,
  @XmlSerialName(
    value = "InvoiceCurrencyCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceCurrencyCode: CurrencyCode? = null,
  @XmlSerialName(
    value = "PaymentCurrencyCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentCurrencyCode: CurrencyCode? = null,
  @XmlSerialName(
    value = "InvoiceIssuerReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceIssuerReference: Text? = null,
  @XmlSerialName(
    value = "InvoiceDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceDateTime: DateTime? = null,
  @XmlSerialName(
    value = "NextInvoiceDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val nextInvoiceDateTime: List<DateTime> = emptyList(),
  @XmlSerialName(
    value = "CreditReasonCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditReasonCode: Code? = null,
  @XmlSerialName(
    value = "CreditReason",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditReason: List<Text> = emptyList(),
  @XmlSerialName(
    value = "InvoicerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoicerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "InvoiceeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceeTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "PayeeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payeeTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "PayerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "TaxApplicableTradeCurrencyExchange",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxApplicableTradeCurrencyExchange: TradeCurrencyExchange? = null,
  @XmlSerialName(
    value = "InvoiceApplicableTradeCurrencyExchange",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceApplicableTradeCurrencyExchange: TradeCurrencyExchange? = null,
  @XmlSerialName(
    value = "PaymentApplicableTradeCurrencyExchange",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentApplicableTradeCurrencyExchange: TradeCurrencyExchange? = null,
  @XmlSerialName(
    value = "SpecifiedTradeSettlementPaymentMeans",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeSettlementPaymentMeans: List<TradeSettlementPaymentMeans> = emptyList(),
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
    value = "SpecifiedTradeSettlementHeaderMonetarySummation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeSettlementHeaderMonetarySummation:
      TradeSettlementHeaderMonetarySummation? = null,
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
    value = "ProFormaInvoiceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val proFormaInvoiceReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "LetterOfCreditReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val letterOfCreditReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "FactoringAgreementReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val factoringAgreementReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "FactoringListReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val factoringListReferencedDocument: List<ReferencedDocument> = emptyList(),
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
  val specifiedTradeSettlementFinancialCard:
      List<TradeSettlementFinancialCard> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedAdvancePayment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedAdvancePayment: List<AdvancePayment> = emptyList(),
  @XmlSerialName(
    value = "UltimatePayeeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val ultimatePayeeTradeParty: TradeParty? = null,
)

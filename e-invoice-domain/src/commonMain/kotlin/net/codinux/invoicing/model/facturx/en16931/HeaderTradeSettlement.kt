package net.codinux.invoicing.model.facturx.en16931

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HeaderTradeSettlement(
  @XmlSerialName(
    value = "CreditorReferenceID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val creditorReferenceID: ID? = null,

  /**
   * ### English
   * A textual value used to establish a link between the payment and the Invoice, issued by the Seller.
   *
   * Used for creditor's critical reconciliation information. This information element helps the Seller to assign an
   * incoming payment to the relevant payment process. When specifying the textual value, which is commonly the
   * invoice number of the invoice being paid, but may be another seller reference, the buyer should indicate this
   * reference in his payment order when executing the payment. In a payment transaction this reference is
   * transferred back to the Seller as Remittance Information.
   * In order to allow for automatic processing of cross-border SEPA payments, only Latin characters should be used
   * in this field, with a maximum of 140 characters. Referencesection 1.4 of the SEPA credit transfer and SEPA direct
   * debit scheme implementation guides [13] and [14] for details of the allowed characters. Other rules may apply
   * for SEPA payments within national borders.
   * If remittance information is structured according to the ISO 11649:2009 standard [16] for Structured RF Creditor
   * Reference, it shall be mapped to the Structured Remittance Information Creditor Reference field in SEPA
   * payments messages.
   * If remittance information is structured according to the EACT standard for automated reconciliation [17], it shall
   * be mapped to the Unstructured Remittance Information field in SEPA payments messages.”
   * If remittance information is to be mapped to the End To End Identification field or to the Structured Remittance
   * Information Creditor Reference field in SEPA payments messages, then in addition to the Latin character set
   * restriction, the content shall not start or end with a '/' and the content shall not contain '//'s. See reference [15].
   *
   * ### German
   * Ein Textwert, der zur Verknüpfung der Zahlung mit der vom Verkäufer ausgestellten Rechnung verwendet wird
   *
   * Zahlungsauftrag oder bei Durchführung der Zahlung angeben. Bei einem Zahlungsvorgang wird diese Referenz
   * dem Verkäufer als Überweisungsinformation zurückübermittelt.
   * Um eine automatische Verarbeitung von grenzüberschreitenden SEPA-Zahlungen zu ermöglichen, sollten in
   * diesem Feld ausschließlich lateinische Schriftzeichen und maximal 140 Zeichen verwendet werden. Siehe 1.4 der
   * SEPA Credit Transfer und der SEPA Direct Debit Scheme Implementation Guides [13] und [14] für weitere
   * Angaben zu den zulässigen Schriftzeichen. Gegebenenfalls gelten für SEPA-Zahlungen innerhalb von
   * Landesgrenzen andere Regeln.
   * Ist die Überweisungsinformation nach ISO 11649:2009 [16] über die strukturierte Referenz des
   * Zahlungsempfängers strukturiert, so muss diese in SEPA-Zahlungsnachrichten dem Feld „Structured Remittance
   * Information Creditor Reference“ zugeordnet werden.
   * Ist die Überweisungsinformation nach EACT-Norm für automatische Kontenabstimmung [17] strukturiert, so
   * muss diese in SEPA-Zahlungsnachrichten dem Feld „Unstructured Remittance Information“ zugeordnet werden.
   * Ist die Überweisungsinformation in SEPA-Zahlungsnachrichten dem Feld „End To End Identification“ oder dem
   * Feld „Structured Remittance Information Creditor Reference“ zuzuordnen, darf der Inhalt, abgesehen von der
   * Einschränkung auf lateinische Schriftzeichen, nicht mit einem ‚/‘ beginnen oder enden und keine ‚//‘ beinhalten.
   * Siehe Literaturhinweis [15].
   */
  @XmlSerialName(
    value = "PaymentReference",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentReference: Text? = null,
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
  val invoiceCurrencyCode: CurrencyCode,
  @XmlSerialName(
    value = "PayeeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payeeTradeParty: TradeParty? = null,
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
    value = "SpecifiedTradePaymentTerms",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradePaymentTerms: TradePaymentTerms? = null,
  @XmlSerialName(
    value = "SpecifiedTradeSettlementHeaderMonetarySummation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTradeSettlementHeaderMonetarySummation:
      TradeSettlementHeaderMonetarySummation,
  @XmlSerialName(
    value = "InvoiceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val invoiceReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "ReceivableSpecifiedTradeAccountingAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val receivableSpecifiedTradeAccountingAccount: TradeAccountingAccount? = null,
)

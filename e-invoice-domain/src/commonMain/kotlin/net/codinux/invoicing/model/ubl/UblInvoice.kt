package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class UblInvoice(
  @XmlSerialName(
    value = "UBLExtensions",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val uBLExtensions: UBLExtensions? = null,
  @XmlSerialName(
    value = "UBLVersionID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val uBLVersionID: Identifier? = null,
  @XmlSerialName(
    value = "CustomizationID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val customizationID: Identifier? = null,
  @XmlSerialName(
    value = "ProfileID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val profileID: Identifier? = null,
  @XmlSerialName(
    value = "ProfileExecutionID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val profileExecutionID: Identifier? = null,
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "CopyIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val copyIndicator: Boolean? = null,
  @XmlSerialName(
    value = "UUID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val uuid: Identifier? = null,
  @XmlSerialName(
    value = "IssueDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val issueDate: Date? = null,
  @XmlSerialName(
    value = "IssueTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val issueTime: Time? = null,
  @XmlSerialName(
    value = "DueDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val dueDate: Date? = null,
  @XmlSerialName(
    value = "InvoiceTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val invoiceTypeCode: Code? = null,
  @XmlSerialName(
    value = "Note",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val note: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TaxPointDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxPointDate: Date? = null,
  @XmlSerialName(
    value = "DocumentCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val documentCurrencyCode: Code? = null,
  @XmlSerialName(
    value = "TaxCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxCurrencyCode: Code? = null,
  @XmlSerialName(
    value = "PricingCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val pricingCurrencyCode: Code? = null,
  @XmlSerialName(
    value = "PaymentCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val paymentCurrencyCode: Code? = null,
  @XmlSerialName(
    value = "PaymentAlternativeCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val paymentAlternativeCurrencyCode: Code? = null,
  @XmlSerialName(
    value = "AccountingCostCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val accountingCostCode: Code? = null,
  @XmlSerialName(
    value = "AccountingCost",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val accountingCost: Text? = null,
  @XmlSerialName(
    value = "LineCountNumeric",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val lineCountNumeric: Numeric? = null,
  @XmlSerialName(
    value = "BuyerReference",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val buyerReference: Text? = null,
  @XmlSerialName(
    value = "InvoicePeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val invoicePeriod: List<Period> = emptyList(),
  @XmlSerialName(
    value = "OrderReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val orderReference: OrderReference? = null,
  @XmlSerialName(
    value = "BillingReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val billingReference: List<BillingReference> = emptyList(),
  @XmlSerialName(
    value = "DespatchDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val despatchDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "ReceiptDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val receiptDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "StatementDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val statementDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "OriginatorDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originatorDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "ContractDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val contractDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "AdditionalDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val additionalDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "ProjectReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val projectReference: List<ProjectReference> = emptyList(),
  @XmlSerialName(
    value = "Signature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val signature: List<Signature> = emptyList(),
  @XmlSerialName(
    value = "AccountingSupplierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val accountingSupplierParty: SupplierParty? = null,
  @XmlSerialName(
    value = "AccountingCustomerParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val accountingCustomerParty: CustomerParty? = null,
  @XmlSerialName(
    value = "PayeeParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val payeeParty: Party? = null,
  @XmlSerialName(
    value = "BuyerCustomerParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val buyerCustomerParty: CustomerParty? = null,
  @XmlSerialName(
    value = "SellerSupplierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val sellerSupplierParty: SupplierParty? = null,
  @XmlSerialName(
    value = "TaxRepresentativeParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val taxRepresentativeParty: Party? = null,
  @XmlSerialName(
    value = "Delivery",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val delivery: List<Delivery> = emptyList(),
  @XmlSerialName(
    value = "DeliveryTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryTerms: DeliveryTerms? = null,
  @XmlSerialName(
    value = "PaymentMeans",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val paymentMeans: List<PaymentMeans> = emptyList(),
  @XmlSerialName(
    value = "PaymentTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val paymentTerms: List<PaymentTerms> = emptyList(),
  @XmlSerialName(
    value = "PrepaidPayment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val prepaidPayment: List<Payment> = emptyList(),
  @XmlSerialName(
    value = "AllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val allowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "TaxExchangeRate",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val taxExchangeRate: ExchangeRate? = null,
  @XmlSerialName(
    value = "PricingExchangeRate",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val pricingExchangeRate: ExchangeRate? = null,
  @XmlSerialName(
    value = "PaymentExchangeRate",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val paymentExchangeRate: ExchangeRate? = null,
  @XmlSerialName(
    value = "PaymentAlternativeExchangeRate",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val paymentAlternativeExchangeRate: ExchangeRate? = null,
  @XmlSerialName(
    value = "TaxTotal",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val taxTotal: List<TaxTotal> = emptyList(),
  @XmlSerialName(
    value = "WithholdingTaxTotal",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val withholdingTaxTotal: List<TaxTotal> = emptyList(),
  @XmlSerialName(
    value = "LegalMonetaryTotal",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val legalMonetaryTotal: MonetaryTotal? = null,
  @XmlSerialName(
    value = "InvoiceLine",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val invoiceLine: List<InvoiceLine> = emptyList(),
)

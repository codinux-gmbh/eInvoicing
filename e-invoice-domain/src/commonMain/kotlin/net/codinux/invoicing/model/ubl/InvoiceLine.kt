package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class InvoiceLine(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier,
  @XmlSerialName(
    value = "UUID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val uuid: Identifier? = null,
  @XmlSerialName(
    value = "Note",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val note: List<Text> = emptyList(),
  @XmlSerialName(
    value = "InvoicedQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val invoicedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "LineExtensionAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val lineExtensionAmount: Amount,
  @XmlSerialName(
    value = "TaxPointDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxPointDate: Date? = null,
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
    value = "PaymentPurposeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val paymentPurposeCode: Code? = null,
  @XmlSerialName(
    value = "FreeOfChargeIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val freeOfChargeIndicator: Boolean? = null,
  @XmlSerialName(
    value = "InvoicePeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val invoicePeriod: List<Period> = emptyList(),
  @XmlSerialName(
    value = "OrderLineReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val orderLineReference: List<OrderLineReference> = emptyList(),
  @XmlSerialName(
    value = "DespatchLineReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val despatchLineReference: List<LineReference> = emptyList(),
  @XmlSerialName(
    value = "ReceiptLineReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val receiptLineReference: List<LineReference> = emptyList(),
  @XmlSerialName(
    value = "BillingReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val billingReference: List<BillingReference> = emptyList(),
  @XmlSerialName(
    value = "DocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val documentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "PricingReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val pricingReference: PricingReference? = null,
  @XmlSerialName(
    value = "OriginatorParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originatorParty: Party? = null,
  @XmlSerialName(
    value = "Delivery",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val delivery: List<Delivery> = emptyList(),
  @XmlSerialName(
    value = "PaymentTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val paymentTerms: List<PaymentTerms> = emptyList(),
  @XmlSerialName(
    value = "AllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val allowanceCharge: List<AllowanceCharge> = emptyList(),
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
    value = "Item",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val item: Item,
  @XmlSerialName(
    value = "Price",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val price: Price? = null,
  @XmlSerialName(
    value = "DeliveryTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryTerms: DeliveryTerms? = null,
  @XmlSerialName(
    value = "SubInvoiceLine",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val subInvoiceLine: List<InvoiceLine> = emptyList(),
  @XmlSerialName(
    value = "ItemPriceExtension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val itemPriceExtension: PriceExtension? = null,
)

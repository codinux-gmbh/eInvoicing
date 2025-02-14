package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class BillingReference(
  @XmlSerialName(
    value = "InvoiceDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val invoiceDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "SelfBilledInvoiceDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val selfBilledInvoiceDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "CreditNoteDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val creditNoteDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "SelfBilledCreditNoteDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val selfBilledCreditNoteDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "DebitNoteDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val debitNoteDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "ReminderDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val reminderDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "AdditionalDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val additionalDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "BillingReferenceLine",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val billingReferenceLine: List<BillingReferenceLine> = emptyList(),
)

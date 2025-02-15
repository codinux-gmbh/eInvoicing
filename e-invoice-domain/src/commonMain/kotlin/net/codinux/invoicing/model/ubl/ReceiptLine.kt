package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ReceiptLine(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
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
    value = "ReceivedQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val receivedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ShortQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val shortQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ShortageActionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val shortageActionCode: Code? = null,
  @XmlSerialName(
    value = "RejectedQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val rejectedQuantity: Quantity? = null,
  @XmlSerialName(
    value = "RejectReasonCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val rejectReasonCode: Code? = null,
  @XmlSerialName(
    value = "RejectReason",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val rejectReason: List<Text> = emptyList(),
  @XmlSerialName(
    value = "RejectActionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val rejectActionCode: Code? = null,
  @XmlSerialName(
    value = "QuantityDiscrepancyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val quantityDiscrepancyCode: Code? = null,
  @XmlSerialName(
    value = "OversupplyQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val oversupplyQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ReceivedDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val receivedDate: Date? = null,
  @XmlSerialName(
    value = "TimingComplaintCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val timingComplaintCode: Code? = null,
  @XmlSerialName(
    value = "TimingComplaint",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val timingComplaint: Text? = null,
  @XmlSerialName(
    value = "OrderLineReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val orderLineReference: OrderLineReference? = null,
  @XmlSerialName(
    value = "DespatchLineReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val despatchLineReference: List<LineReference> = emptyList(),
  @XmlSerialName(
    value = "DocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val documentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "Item",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val item: List<Item> = emptyList(),
  @XmlSerialName(
    value = "Shipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipment: List<Shipment> = emptyList(),
)

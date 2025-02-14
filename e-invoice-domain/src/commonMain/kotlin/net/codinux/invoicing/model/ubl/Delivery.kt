package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Delivery(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "Quantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val quantity: Quantity? = null,
  @XmlSerialName(
    value = "MinimumQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val minimumQuantity: Quantity? = null,
  @XmlSerialName(
    value = "MaximumQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val maximumQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ActualDeliveryDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val actualDeliveryDate: Date? = null,
  @XmlSerialName(
    value = "ActualDeliveryTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val actualDeliveryTime: Time? = null,
  @XmlSerialName(
    value = "LatestDeliveryDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val latestDeliveryDate: Date? = null,
  @XmlSerialName(
    value = "LatestDeliveryTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val latestDeliveryTime: Time? = null,
  @XmlSerialName(
    value = "ReleaseID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val releaseID: Identifier? = null,
  @XmlSerialName(
    value = "TrackingID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val trackingID: Identifier? = null,
  @XmlSerialName(
    value = "DeliveryAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryAddress: Address? = null,
  @XmlSerialName(
    value = "DeliveryLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryLocation: Location? = null,
  @XmlSerialName(
    value = "AlternativeDeliveryLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val alternativeDeliveryLocation: Location? = null,
  @XmlSerialName(
    value = "RequestedDeliveryPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val requestedDeliveryPeriod: Period? = null,
  @XmlSerialName(
    value = "PromisedDeliveryPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val promisedDeliveryPeriod: Period? = null,
  @XmlSerialName(
    value = "EstimatedDeliveryPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val estimatedDeliveryPeriod: Period? = null,
  @XmlSerialName(
    value = "CarrierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val carrierParty: Party? = null,
  @XmlSerialName(
    value = "DeliveryParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryParty: Party? = null,
  @XmlSerialName(
    value = "NotifyParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val notifyParty: List<Party> = emptyList(),
  @XmlSerialName(
    value = "Despatch",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val despatch: Despatch? = null,
  @XmlSerialName(
    value = "DeliveryTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryTerms: List<DeliveryTerms> = emptyList(),
  @XmlSerialName(
    value = "MinimumDeliveryUnit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val minimumDeliveryUnit: DeliveryUnit? = null,
  @XmlSerialName(
    value = "MaximumDeliveryUnit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val maximumDeliveryUnit: DeliveryUnit? = null,
  @XmlSerialName(
    value = "Shipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipment: Shipment? = null,
)

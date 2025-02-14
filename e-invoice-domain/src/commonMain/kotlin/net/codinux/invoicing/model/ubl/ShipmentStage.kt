package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ShipmentStage(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "TransportModeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportModeCode: Code? = null,
  @XmlSerialName(
    value = "TransportMeansTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportMeansTypeCode: Code? = null,
  @XmlSerialName(
    value = "TransitDirectionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transitDirectionCode: Code? = null,
  @XmlSerialName(
    value = "PreCarriageIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val preCarriageIndicator: Boolean? = null,
  @XmlSerialName(
    value = "OnCarriageIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val onCarriageIndicator: Boolean? = null,
  @XmlSerialName(
    value = "EstimatedDeliveryDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val estimatedDeliveryDate: Date? = null,
  @XmlSerialName(
    value = "EstimatedDeliveryTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val estimatedDeliveryTime: Time? = null,
  @XmlSerialName(
    value = "RequiredDeliveryDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val requiredDeliveryDate: Date? = null,
  @XmlSerialName(
    value = "RequiredDeliveryTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val requiredDeliveryTime: Time? = null,
  @XmlSerialName(
    value = "LoadingSequenceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val loadingSequenceID: Identifier? = null,
  @XmlSerialName(
    value = "SuccessiveSequenceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val successiveSequenceID: Identifier? = null,
  @XmlSerialName(
    value = "Instructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val instructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "DemurrageInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val demurrageInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "CrewQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val crewQuantity: Quantity? = null,
  @XmlSerialName(
    value = "PassengerQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val passengerQuantity: Quantity? = null,
  @XmlSerialName(
    value = "TransitPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transitPeriod: Period? = null,
  @XmlSerialName(
    value = "CarrierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val carrierParty: List<Party> = emptyList(),
  @XmlSerialName(
    value = "TransportMeans",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportMeans: TransportMeans? = null,
  @XmlSerialName(
    value = "LoadingPortLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val loadingPortLocation: Location? = null,
  @XmlSerialName(
    value = "UnloadingPortLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val unloadingPortLocation: Location? = null,
  @XmlSerialName(
    value = "TransshipPortLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transshipPortLocation: Location? = null,
  @XmlSerialName(
    value = "LoadingTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val loadingTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "ExaminationTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val examinationTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "AvailabilityTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val availabilityTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "ExportationTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val exportationTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "DischargeTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val dischargeTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "WarehousingTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val warehousingTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "TakeoverTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val takeoverTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "OptionalTakeoverTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val optionalTakeoverTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "DropoffTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val dropoffTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "ActualPickupTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val actualPickupTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "DeliveryTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "ReceiptTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val receiptTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "StorageTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val storageTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "AcceptanceTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val acceptanceTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "TerminalOperatorParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val terminalOperatorParty: Party? = null,
  @XmlSerialName(
    value = "CustomsAgentParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val customsAgentParty: Party? = null,
  @XmlSerialName(
    value = "EstimatedTransitPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val estimatedTransitPeriod: Period? = null,
  @XmlSerialName(
    value = "FreightAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightAllowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "FreightChargeLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightChargeLocation: Location? = null,
  @XmlSerialName(
    value = "DetentionTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val detentionTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "RequestedDepartureTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val requestedDepartureTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "RequestedArrivalTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val requestedArrivalTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "RequestedWaypointTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val requestedWaypointTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "PlannedDepartureTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val plannedDepartureTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "PlannedArrivalTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val plannedArrivalTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "PlannedWaypointTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val plannedWaypointTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "ActualDepartureTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val actualDepartureTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "ActualWaypointTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val actualWaypointTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "ActualArrivalTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val actualArrivalTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "TransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "EstimatedDepartureTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val estimatedDepartureTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "EstimatedArrivalTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val estimatedArrivalTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "PassengerPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val passengerPerson: List<Person> = emptyList(),
  @XmlSerialName(
    value = "DriverPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val driverPerson: List<Person> = emptyList(),
  @XmlSerialName(
    value = "ReportingPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val reportingPerson: Person? = null,
  @XmlSerialName(
    value = "CrewMemberPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val crewMemberPerson: List<Person> = emptyList(),
  @XmlSerialName(
    value = "SecurityOfficerPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val securityOfficerPerson: Person? = null,
  @XmlSerialName(
    value = "MasterPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val masterPerson: Person? = null,
  @XmlSerialName(
    value = "ShipsSurgeonPerson",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipsSurgeonPerson: Person? = null,
)

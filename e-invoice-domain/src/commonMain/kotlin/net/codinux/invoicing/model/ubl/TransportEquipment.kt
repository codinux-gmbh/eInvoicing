package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TransportEquipment(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "ReferencedConsignmentID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val referencedConsignmentID: List<Identifier> = emptyList(),
  @XmlSerialName(
    value = "TransportEquipmentTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportEquipmentTypeCode: Code? = null,
  @XmlSerialName(
    value = "ProviderTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val providerTypeCode: Code? = null,
  @XmlSerialName(
    value = "OwnerTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val ownerTypeCode: Code? = null,
  @XmlSerialName(
    value = "SizeTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val sizeTypeCode: Code? = null,
  @XmlSerialName(
    value = "DispositionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val dispositionCode: Code? = null,
  @XmlSerialName(
    value = "FullnessIndicationCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val fullnessIndicationCode: Code? = null,
  @XmlSerialName(
    value = "RefrigerationOnIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val refrigerationOnIndicator: Boolean? = null,
  @XmlSerialName(
    value = "Information",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val information: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ReturnabilityIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val returnabilityIndicator: Boolean? = null,
  @XmlSerialName(
    value = "LegalStatusIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val legalStatusIndicator: Boolean? = null,
  @XmlSerialName(
    value = "AirFlowPercent",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val airFlowPercent: Numeric? = null,
  @XmlSerialName(
    value = "HumidityPercent",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val humidityPercent: Numeric? = null,
  @XmlSerialName(
    value = "AnimalFoodApprovedIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val animalFoodApprovedIndicator: Boolean? = null,
  @XmlSerialName(
    value = "HumanFoodApprovedIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val humanFoodApprovedIndicator: Boolean? = null,
  @XmlSerialName(
    value = "DangerousGoodsApprovedIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val dangerousGoodsApprovedIndicator: Boolean? = null,
  @XmlSerialName(
    value = "RefrigeratedIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val refrigeratedIndicator: Boolean? = null,
  @XmlSerialName(
    value = "Characteristics",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val characteristics: Text? = null,
  @XmlSerialName(
    value = "DamageRemarks",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val damageRemarks: List<Text> = emptyList(),
  @XmlSerialName(
    value = "Description",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "SpecialTransportRequirements",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val specialTransportRequirements: List<Text> = emptyList(),
  @XmlSerialName(
    value = "GrossWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val grossWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "GrossVolumeMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val grossVolumeMeasure: Measure? = null,
  @XmlSerialName(
    value = "TareWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tareWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "TrackingDeviceCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val trackingDeviceCode: Code? = null,
  @XmlSerialName(
    value = "PowerIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val powerIndicator: Boolean? = null,
  @XmlSerialName(
    value = "TraceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val traceID: Identifier? = null,
  @XmlSerialName(
    value = "MeasurementDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val measurementDimension: List<Dimension> = emptyList(),
  @XmlSerialName(
    value = "TransportEquipmentSeal",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEquipmentSeal: List<TransportEquipmentSeal> = emptyList(),
  @XmlSerialName(
    value = "MinimumTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val minimumTemperature: Temperature? = null,
  @XmlSerialName(
    value = "MaximumTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val maximumTemperature: Temperature? = null,
  @XmlSerialName(
    value = "ProviderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val providerParty: Party? = null,
  @XmlSerialName(
    value = "LoadingProofParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val loadingProofParty: Party? = null,
  @XmlSerialName(
    value = "SupplierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val supplierParty: SupplierParty? = null,
  @XmlSerialName(
    value = "OwnerParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val ownerParty: Party? = null,
  @XmlSerialName(
    value = "OperatingParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val operatingParty: Party? = null,
  @XmlSerialName(
    value = "LoadingLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val loadingLocation: Location? = null,
  @XmlSerialName(
    value = "UnloadingLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val unloadingLocation: Location? = null,
  @XmlSerialName(
    value = "StorageLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val storageLocation: Location? = null,
  @XmlSerialName(
    value = "PositioningTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val positioningTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "QuarantineTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val quarantineTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "DeliveryTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "PickupTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val pickupTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "HandlingTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val handlingTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "LoadingTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val loadingTransportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "TransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTransportMeans",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val applicableTransportMeans: TransportMeans? = null,
  @XmlSerialName(
    value = "HaulageTradingTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val haulageTradingTerms: List<TradingTerms> = emptyList(),
  @XmlSerialName(
    value = "HazardousGoodsTransit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val hazardousGoodsTransit: List<HazardousGoodsTransit> = emptyList(),
  @XmlSerialName(
    value = "PackagedTransportHandlingUnit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val packagedTransportHandlingUnit: List<TransportHandlingUnit> = emptyList(),
  @XmlSerialName(
    value = "ServiceAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val serviceAllowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "FreightAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightAllowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "AttachedTransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val attachedTransportEquipment: List<TransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "Delivery",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val delivery: Delivery? = null,
  @XmlSerialName(
    value = "Pickup",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val pickup: Pickup? = null,
  @XmlSerialName(
    value = "Despatch",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val despatch: Despatch? = null,
  @XmlSerialName(
    value = "ShipmentDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipmentDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "ContainedInTransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val containedInTransportEquipment: List<TransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "Package",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val `package`: List<Package> = emptyList(),
  @XmlSerialName(
    value = "GoodsItem",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val goodsItem: List<GoodsItem> = emptyList(),
)

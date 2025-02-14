package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TransportationService(
  @XmlSerialName(
    value = "TransportServiceCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportServiceCode: Code,
  @XmlSerialName(
    value = "TariffClassCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tariffClassCode: Code? = null,
  @XmlSerialName(
    value = "Priority",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val priority: Text? = null,
  @XmlSerialName(
    value = "FreightRateClassCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val freightRateClassCode: Code? = null,
  @XmlSerialName(
    value = "TransportationServiceDescription",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportationServiceDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TransportationServiceDetailsURI",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportationServiceDetailsURI: Identifier? = null,
  @XmlSerialName(
    value = "NominationDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val nominationDate: Date? = null,
  @XmlSerialName(
    value = "NominationTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val nominationTime: Time? = null,
  @XmlSerialName(
    value = "Name",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val name: Text? = null,
  @XmlSerialName(
    value = "SequenceNumeric",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val sequenceNumeric: Numeric? = null,
  @XmlSerialName(
    value = "TransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEquipment: List<TransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "SupportedTransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val supportedTransportEquipment: List<TransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "UnsupportedTransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val unsupportedTransportEquipment: List<TransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "CommodityClassification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val commodityClassification: List<CommodityClassification> = emptyList(),
  @XmlSerialName(
    value = "SupportedCommodityClassification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val supportedCommodityClassification: List<CommodityClassification> = emptyList(),
  @XmlSerialName(
    value = "UnsupportedCommodityClassification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val unsupportedCommodityClassification: List<CommodityClassification> = emptyList(),
  @XmlSerialName(
    value = "TotalCapacityDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val totalCapacityDimension: Dimension? = null,
  @XmlSerialName(
    value = "ShipmentStage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipmentStage: List<ShipmentStage> = emptyList(),
  @XmlSerialName(
    value = "TransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "ResponsibleTransportServiceProviderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val responsibleTransportServiceProviderParty: Party? = null,
  @XmlSerialName(
    value = "EnvironmentalEmission",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val environmentalEmission: List<EnvironmentalEmission> = emptyList(),
  @XmlSerialName(
    value = "EstimatedDurationPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val estimatedDurationPeriod: Period? = null,
  @XmlSerialName(
    value = "ScheduledServiceFrequency",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val scheduledServiceFrequency: List<ServiceFrequency> = emptyList(),
)

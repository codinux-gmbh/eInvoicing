package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TransportMeans(
  @XmlSerialName(
    value = "JourneyID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val journeyID: Identifier? = null,
  @XmlSerialName(
    value = "RegistrationNationalityID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val registrationNationalityID: Identifier? = null,
  @XmlSerialName(
    value = "RegistrationNationality",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val registrationNationality: List<Text> = emptyList(),
  @XmlSerialName(
    value = "DirectionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val directionCode: Code? = null,
  @XmlSerialName(
    value = "TransportMeansTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportMeansTypeCode: Code? = null,
  @XmlSerialName(
    value = "TradeServiceCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tradeServiceCode: Code? = null,
  @XmlSerialName(
    value = "Stowage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val stowage: Stowage? = null,
  @XmlSerialName(
    value = "AirTransport",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val airTransport: AirTransport? = null,
  @XmlSerialName(
    value = "RoadTransport",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val roadTransport: RoadTransport? = null,
  @XmlSerialName(
    value = "RailTransport",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val railTransport: RailTransport? = null,
  @XmlSerialName(
    value = "MaritimeTransport",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val maritimeTransport: MaritimeTransport? = null,
  @XmlSerialName(
    value = "OwnerParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val ownerParty: Party? = null,
  @XmlSerialName(
    value = "MeasurementDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val measurementDimension: List<Dimension> = emptyList(),
)

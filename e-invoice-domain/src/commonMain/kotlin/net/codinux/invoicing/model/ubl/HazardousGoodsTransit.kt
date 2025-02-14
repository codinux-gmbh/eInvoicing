package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HazardousGoodsTransit(
  @XmlSerialName(
    value = "TransportEmergencyCardCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportEmergencyCardCode: Code? = null,
  @XmlSerialName(
    value = "PackingCriteriaCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val packingCriteriaCode: Code? = null,
  @XmlSerialName(
    value = "HazardousRegulationCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousRegulationCode: Code? = null,
  @XmlSerialName(
    value = "InhalationToxicityZoneCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val inhalationToxicityZoneCode: Code? = null,
  @XmlSerialName(
    value = "TransportAuthorizationCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportAuthorizationCode: Code? = null,
  @XmlSerialName(
    value = "MaximumTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val maximumTemperature: Temperature? = null,
  @XmlSerialName(
    value = "MinimumTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val minimumTemperature: Temperature? = null,
)

package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class MaritimeTransport(
  @XmlSerialName(
    value = "VesselID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val vesselID: Identifier? = null,
  @XmlSerialName(
    value = "VesselName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val vesselName: Text? = null,
  @XmlSerialName(
    value = "RadioCallSignID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val radioCallSignID: Identifier? = null,
  @XmlSerialName(
    value = "ShipsRequirements",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val shipsRequirements: List<Text> = emptyList(),
  @XmlSerialName(
    value = "GrossTonnageMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val grossTonnageMeasure: Measure? = null,
  @XmlSerialName(
    value = "NetTonnageMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val netTonnageMeasure: Measure? = null,
  @XmlSerialName(
    value = "RegistryCertificateDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val registryCertificateDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "RegistryPortLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val registryPortLocation: Location? = null,
)

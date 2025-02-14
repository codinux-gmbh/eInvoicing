package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class LocationCoordinate(
  @XmlSerialName(
    value = "CoordinateSystemCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val coordinateSystemCode: Code? = null,
  @XmlSerialName(
    value = "LatitudeDegreesMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val latitudeDegreesMeasure: Measure? = null,
  @XmlSerialName(
    value = "LatitudeMinutesMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val latitudeMinutesMeasure: Measure? = null,
  @XmlSerialName(
    value = "LatitudeDirectionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val latitudeDirectionCode: Code? = null,
  @XmlSerialName(
    value = "LongitudeDegreesMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val longitudeDegreesMeasure: Measure? = null,
  @XmlSerialName(
    value = "LongitudeMinutesMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val longitudeMinutesMeasure: Measure? = null,
  @XmlSerialName(
    value = "LongitudeDirectionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val longitudeDirectionCode: Code? = null,
  @XmlSerialName(
    value = "AltitudeMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val altitudeMeasure: Measure? = null,
)

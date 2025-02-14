package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class HazardousItem(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "PlacardNotation",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val placardNotation: Text? = null,
  @XmlSerialName(
    value = "PlacardEndorsement",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val placardEndorsement: Text? = null,
  @XmlSerialName(
    value = "AdditionalInformation",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val additionalInformation: List<Text> = emptyList(),
  @XmlSerialName(
    value = "UNDGCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val uNDGCode: Code? = null,
  @XmlSerialName(
    value = "EmergencyProceduresCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val emergencyProceduresCode: Code? = null,
  @XmlSerialName(
    value = "MedicalFirstAidGuideCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val medicalFirstAidGuideCode: Code? = null,
  @XmlSerialName(
    value = "TechnicalName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val technicalName: Text? = null,
  @XmlSerialName(
    value = "CategoryName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val categoryName: Text? = null,
  @XmlSerialName(
    value = "HazardousCategoryCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousCategoryCode: Code? = null,
  @XmlSerialName(
    value = "UpperOrangeHazardPlacardID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val upperOrangeHazardPlacardID: Identifier? = null,
  @XmlSerialName(
    value = "LowerOrangeHazardPlacardID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val lowerOrangeHazardPlacardID: Identifier? = null,
  @XmlSerialName(
    value = "MarkingID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val markingID: Identifier? = null,
  @XmlSerialName(
    value = "HazardClassID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardClassID: Identifier? = null,
  @XmlSerialName(
    value = "NetWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val netWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "NetVolumeMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val netVolumeMeasure: Measure? = null,
  @XmlSerialName(
    value = "Quantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val quantity: Quantity? = null,
  @XmlSerialName(
    value = "ContactParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val contactParty: Party? = null,
  @XmlSerialName(
    value = "SecondaryHazard",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val secondaryHazard: List<SecondaryHazard> = emptyList(),
  @XmlSerialName(
    value = "HazardousGoodsTransit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val hazardousGoodsTransit: List<HazardousGoodsTransit> = emptyList(),
  @XmlSerialName(
    value = "EmergencyTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val emergencyTemperature: Temperature? = null,
  @XmlSerialName(
    value = "FlashpointTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val flashpointTemperature: Temperature? = null,
  @XmlSerialName(
    value = "AdditionalTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val additionalTemperature: List<Temperature> = emptyList(),
)

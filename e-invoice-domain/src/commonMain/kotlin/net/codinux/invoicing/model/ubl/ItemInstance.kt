package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ItemInstance(
  @XmlSerialName(
    value = "ProductTraceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val productTraceID: Identifier? = null,
  @XmlSerialName(
    value = "ManufactureDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val manufactureDate: Date? = null,
  @XmlSerialName(
    value = "ManufactureTime",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val manufactureTime: Time? = null,
  @XmlSerialName(
    value = "BestBeforeDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val bestBeforeDate: Date? = null,
  @XmlSerialName(
    value = "RegistrationID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val registrationID: Identifier? = null,
  @XmlSerialName(
    value = "SerialID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val serialID: Identifier? = null,
  @XmlSerialName(
    value = "AdditionalItemProperty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val additionalItemProperty: List<ItemProperty> = emptyList(),
  @XmlSerialName(
    value = "LotIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val lotIdentification: LotIdentification? = null,
)

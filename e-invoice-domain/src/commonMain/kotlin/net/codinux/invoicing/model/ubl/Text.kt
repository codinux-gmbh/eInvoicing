package net.codinux.invoicing.model.ubl

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Text(
  @XmlSerialName(
    value = "value",
    prefix = "ccts-cct",
    namespace = "urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: String? = null,
  @XmlSerialName(value = "languageID")
  @XmlElement(value = false)
  val languageID: String? = null,
  @XmlSerialName(value = "languageLocaleID")
  @XmlElement(value = false)
  val languageLocaleID: String? = null,
)

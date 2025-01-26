package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Text(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
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

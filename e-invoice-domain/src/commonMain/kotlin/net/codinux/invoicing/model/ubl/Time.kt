package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Time(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:UnqualifiedDataTypes-2",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: String? = null,
)

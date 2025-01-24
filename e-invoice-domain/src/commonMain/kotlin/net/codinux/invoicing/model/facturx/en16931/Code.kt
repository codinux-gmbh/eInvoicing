package net.codinux.invoicing.model.facturx.en16931

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Code(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: String? = null,
  @XmlSerialName(value = "listID")
  @XmlElement(value = false)
  val listID: String? = null,
  @XmlSerialName(value = "listVersionID")
  @XmlElement(value = false)
  val listVersionID: String? = null,
)

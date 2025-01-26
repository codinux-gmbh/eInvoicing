package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class ID(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: String? = null,
  @XmlSerialName(value = "schemeID")
  @XmlElement(value = false)
  val schemeID: String? = null,
  @XmlSerialName(value = "schemeName")
  @XmlElement(value = false)
  val schemeName: String? = null,
  @XmlSerialName(value = "schemeAgencyID")
  @XmlElement(value = false)
  val schemeAgencyID: String? = null,
  @XmlSerialName(value = "schemeAgencyName")
  @XmlElement(value = false)
  val schemeAgencyName: String? = null,
  @XmlSerialName(value = "schemeVersionID")
  @XmlElement(value = false)
  val schemeVersionID: String? = null,
  @XmlSerialName(value = "schemeDataURI")
  @XmlElement(value = false)
  val schemeDataURI: String? = null,
  @XmlSerialName(value = "schemeURI")
  @XmlElement(value = false)
  val schemeURI: String? = null,
)

package net.codinux.invoicing.model.cii.lenient

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
  @XmlSerialName(value = "listAgencyID")
  @XmlElement(value = false)
  val listAgencyID: String? = null,
  @XmlSerialName(value = "listAgencyName")
  @XmlElement(value = false)
  val listAgencyName: String? = null,
  @XmlSerialName(value = "listVersionID")
  @XmlElement(value = false)
  val listVersionID: String? = null,
  @XmlSerialName(value = "name")
  @XmlElement(value = false)
  val name: String? = null,
  @XmlSerialName(value = "listName")
  @XmlElement(value = false)
  val listName: String? = null,
  @XmlSerialName(value = "languageID")
  @XmlElement(value = false)
  val languageID: String? = null,
  @XmlSerialName(value = "listURI")
  @XmlElement(value = false)
  val listURI: String? = null,
  @XmlSerialName(value = "listSchemeURI")
  @XmlElement(value = false)
  val listSchemeURI: String? = null,
)

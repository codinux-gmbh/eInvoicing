package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class PackagingMarkingCode(
  @XmlSerialName(
    value = "value",
    prefix = "qdt",
    namespace = "urn:un:unece:uncefact:data:standard:QualifiedDataType:100",
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
  @XmlSerialName(value = "listVersionID")
  @XmlElement(value = false)
  val listVersionID: String? = null,
  @XmlSerialName(value = "listURI")
  @XmlElement(value = false)
  val listURI: String? = null,
)

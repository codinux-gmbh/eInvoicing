package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class FreightChargeTypeID(
  @XmlSerialName(
    value = "value",
    prefix = "qdt",
    namespace = "urn:un:unece:uncefact:data:standard:QualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: String? = null,
  @XmlSerialName(value = "schemeAgencyID")
  @XmlElement(value = false)
  val schemeAgencyID: String? = null,
)

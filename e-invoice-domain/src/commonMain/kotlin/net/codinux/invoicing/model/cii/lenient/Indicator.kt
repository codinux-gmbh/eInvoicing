package net.codinux.invoicing.model.cii.lenient

import kotlin.Boolean
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Indicator(
  @XmlSerialName(
    value = "Indicator",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
  )
  @XmlElement(value = true)
  val indicator: Boolean? = null,
)

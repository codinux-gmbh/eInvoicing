package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class DurationUnitMeasure(
  @XmlSerialName(
    value = "value",
    prefix = "qdt",
    namespace = "urn:un:unece:uncefact:data:standard:QualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "unitCode")
  @XmlElement(value = false)
  val unitCode: String? = null,
)

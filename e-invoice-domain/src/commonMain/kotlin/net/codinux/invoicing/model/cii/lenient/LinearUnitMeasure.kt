package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.serialization.CiiBigDecimalSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class LinearUnitMeasure(
  @XmlSerialName(
    value = "value",
    prefix = "qdt",
    namespace = "urn:un:unece:uncefact:data:standard:QualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = CiiBigDecimalSerializer::class)
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "unitCode")
  @XmlElement(value = false)
  val unitCode: String? = null,
)

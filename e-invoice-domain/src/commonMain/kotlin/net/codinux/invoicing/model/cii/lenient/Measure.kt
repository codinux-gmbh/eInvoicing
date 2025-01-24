package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.serialization.CiiBigDecimalSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Measure(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = CiiBigDecimalSerializer::class)
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "unitCode")
  @XmlElement(value = false)
  val unitCode: String? = null,
  @XmlSerialName(value = "unitCodeListVersionID")
  @XmlElement(value = false)
  val unitCodeListVersionID: String? = null,
)

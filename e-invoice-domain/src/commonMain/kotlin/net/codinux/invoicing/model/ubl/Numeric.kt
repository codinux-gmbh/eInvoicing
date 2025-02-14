package net.codinux.invoicing.model.ubl

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.serialization.CiiBigDecimalSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Numeric(
  @XmlSerialName(
    value = "value",
    prefix = "ccts-cct",
    namespace = "urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = CiiBigDecimalSerializer::class)
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "format")
  @XmlElement(value = false)
  val format: String? = null,
)

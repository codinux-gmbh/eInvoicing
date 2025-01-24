package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.serialization.CiiBigDecimalSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Percent(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = CiiBigDecimalSerializer::class)
  val `value`: BigDecimal? = null,
)

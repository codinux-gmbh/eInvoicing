package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.serialization.CiiBigDecimalSerializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Amount(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = CiiBigDecimalSerializer::class)
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "currencyID")
  @XmlElement(value = false)
  val currencyID: String? = null,
  @XmlSerialName(value = "currencyCodeListVersionID")
  @XmlElement(value = false)
  val currencyCodeListVersionID: String? = null,
)

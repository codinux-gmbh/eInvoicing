package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Amount(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "currencyID")
  @XmlElement(value = false)
  val currencyID: String? = null,
  @XmlSerialName(value = "currencyCodeListVersionID")
  @XmlElement(value = false)
  val currencyCodeListVersionID: String? = null,
)

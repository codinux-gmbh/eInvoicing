package net.codinux.invoicing.model.cii.lenient

import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Quantity(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  @XmlValue
  val `value`: BigDecimal? = null,
  @XmlSerialName(value = "unitCode")
  @XmlElement(value = false)
  val unitCode: String? = null,
  @XmlSerialName(value = "unitCodeListID")
  @XmlElement(value = false)
  val unitCodeListID: String? = null,
  @XmlSerialName(value = "unitCodeListAgencyID")
  @XmlElement(value = false)
  val unitCodeListAgencyID: String? = null,
  @XmlSerialName(value = "unitCodeListAgencyName")
  @XmlElement(value = false)
  val unitCodeListAgencyName: String? = null,
)

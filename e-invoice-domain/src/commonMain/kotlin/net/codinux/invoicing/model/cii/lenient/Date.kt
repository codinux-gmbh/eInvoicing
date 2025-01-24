package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.LocalDate
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Date(
  @XmlSerialName(
    value = "DateString",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  val dateString: DateString? = null,
  @XmlSerialName(
    value = "Date",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  val date: LocalDate? = null,
)

package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.LocalDateTime
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class DateTime(
  @XmlSerialName(
    value = "DateTimeString",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  val dateTimeString: DateTimeString? = null,
  @XmlSerialName(
    value = "DateTime",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  val dateTime: LocalDateTime? = null,
)

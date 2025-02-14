package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class Date(
  @XmlSerialName(
    value = "value",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  @XmlValue
//  val `value`: LocalDate? = null, // TODO: serialize LocalDate to ISO date string, then we can use LocalDate directly
  val isoDateString: String? = null, // actually non-null
)

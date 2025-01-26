package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ContactPerson(
  @XmlSerialName(
    value = "GivenName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val givenName: Text? = null,
  @XmlSerialName(
    value = "MiddleName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val middleName: Text? = null,
  @XmlSerialName(
    value = "FamilyName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val familyName: Text? = null,
)

package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeContact(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: ID? = null,
  @XmlSerialName(
    value = "PersonName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val personName: Text? = null,
  @XmlSerialName(
    value = "DepartmentName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val departmentName: Text? = null,
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: ContactTypeCode? = null,
  @XmlSerialName(
    value = "JobTitle",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val jobTitle: Text? = null,
  @XmlSerialName(
    value = "Responsibility",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val responsibility: Text? = null,
  @XmlSerialName(
    value = "PersonID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val personID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "TelephoneUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val telephoneUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "DirectTelephoneUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val directTelephoneUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "MobileTelephoneUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val mobileTelephoneUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "FaxUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val faxUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "EmailURIUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val emailURIUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "TelexUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val telexUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "VOIPUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val vOIPUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "InstantMessagingUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val instantMessagingUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "SpecifiedNote",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedNote: List<Note> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedContactPerson",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedContactPerson: ContactPerson? = null,
)

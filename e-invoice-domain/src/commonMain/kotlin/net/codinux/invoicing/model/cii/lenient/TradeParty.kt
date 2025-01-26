package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeParty(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: List<ID> = emptyList(),
  @XmlSerialName(
    value = "GlobalID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val globalID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "Name",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val name: Text? = null,
  @XmlSerialName(
    value = "RoleCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val roleCode: List<PartyRoleCode> = emptyList(),
  @XmlSerialName(
    value = "Description",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "RegisteredID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val registeredID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "Role",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val role: List<Text> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedLegalOrganization",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLegalOrganization: LegalOrganization? = null,
  @XmlSerialName(
    value = "DefinedTradeContact",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val definedTradeContact: List<TradeContact> = emptyList(),
  @XmlSerialName(
    value = "PostalTradeAddress",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val postalTradeAddress: TradeAddress? = null,
  @XmlSerialName(
    value = "URIUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val uRIUniversalCommunication: List<UniversalCommunication> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedLogisticsLocation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLogisticsLocation: List<LogisticsLocation> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedTaxRegistration",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTaxRegistration: List<TaxRegistration> = emptyList(),
  @XmlSerialName(
    value = "EndPointURIUniversalCommunication",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val endPointURIUniversalCommunication: UniversalCommunication? = null,
  @XmlSerialName(
    value = "LogoAssociatedSpecifiedBinaryFile",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val logoAssociatedSpecifiedBinaryFile: List<SpecifiedBinaryFile> = emptyList(),
)
